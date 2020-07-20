import core.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    final private static int CORE_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Result result = null;

        try {
            result = getResultOfStartProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean readyToWork = Result.checkResult(result);

        if (readyToWork) {
            File dirForRead = new File(result.getDIR_FOT_READ());

            long start = System.currentTimeMillis();
            File[] files = dirForRead.listFiles();
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue(Arrays.asList(files));
            ArrayList<ImageResizeThread> imageResizeThreads = new ArrayList<>(CORE_COUNT);

            for (int i = 0; i < CORE_COUNT; i++) {
                ImageResizeThread imageResizeThread = new ImageResizeThread(result, concurrentLinkedQueue, start);
                imageResizeThreads.add(imageResizeThread);
                imageResizeThread.start();
            }


            for (ImageResizeThread thread : imageResizeThreads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {System.out.println("Ошибка ввода данных. Операция не выполнена");}
        System.out.println("Завершение работы программы");
    }




    private static Result getResultOfStartProgram() throws IOException {
        String dirForRead;
        String dirForWrite;
        int compression = 0;
        System.out.println("\tЗапуск программы:");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Укажите какую папку с изображениями которые необходимо уменьшить");
            dirForRead = bufferedReader.readLine();

            System.out.println("Укажите какую папку, куда сохронять новые изображения");
            dirForWrite = bufferedReader.readLine();
            System.out.println("Укажите параметр сжатия, например: 30% (По умолчанию 0%)");
            String percentageSting = bufferedReader.readLine().replaceAll("[^0-9]", "").trim();
            if (percentageSting.length()!= 0) {
                compression = Integer.parseInt(percentageSting);
            }
        } catch (IOException e) {
            throw new IOException();
        }
         finally {
            bufferedReader.close();
        }
        return new Result(dirForRead,dirForWrite,compression);
    }
}


