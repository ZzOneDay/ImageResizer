import core.*;
import java.io.*;
import java.util.LinkedList;

public class Main {

    final private static int coreCount = Runtime.getRuntime().availableProcessors();
    private static String srcFolder;
    static String dstFolder;
    static int compressionPercentage;
    private static boolean needToCreateNewFolder = false;

    public static void main(String[] args) {
        if (resultOfStartProgram() == 0) {
            File srcDir = new File(srcFolder);
            if (needToCreateNewFolder) {
                System.out.println("\tСоздание папки: " + dstFolder);
                new File(dstFolder).mkdirs();
            }

            long start = System.currentTimeMillis();
            File[] files = srcDir.listFiles();

            if (files != null) {
                Sort.sortFiles(files);
            }

            LinkedList<LinkedList<File>> separatedFiles = Separator.getSeparatedFiles(files, coreCount);
            System.out.println("\tКоличество потоков: " + separatedFiles.size());
            for (LinkedList fileList : separatedFiles) {
                new ImageResizeThread(fileList).start();
            }

            int countFiles = Separator.getCountImage();
            int countNewFiles = 0;
            while (countFiles > countNewFiles) {
                countNewFiles = new File(dstFolder).list().length;
                System.out.print("\rПрогресс: " + countNewFiles + " / " + countFiles);
            }
            if (countFiles == new File(dstFolder).list().length) {
                System.out.println("\nВыполнено, потрачено: " + (System.currentTimeMillis() - start) + " ms");
            }
        } else {
            System.out.println("Аварийное выключение программы");
        }
        System.out.println("Завершение работы программы");
    }

    private static int resultOfStartProgram() {
        System.out.println("\tЗапуск программы:");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Укажите какую папку с изображениями которые необходимо уменьшить");
            srcFolder = bufferedReader.readLine();
            if (!new File(srcFolder).canWrite()) {
                throw new IOException();
            }

            System.out.println("Укажите какую папку, куда сохронять новые изображения");
            dstFolder = bufferedReader.readLine();
            if (!new File(dstFolder).canWrite()) {
                needToCreateNewFolder = true;
            }

            System.out.println("Укажите параметр сжатия, например: 30%");
            String percentageSting = bufferedReader.readLine().replaceAll("[^0-9]", "").trim();
            compressionPercentage = Integer.parseInt(percentageSting);
            if (compressionPercentage > 100) {
                throw new IOException();
            }

        } catch (IOException e) {
            System.out.println("Ошибка ввода исходных данных");
            return -1;
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}


