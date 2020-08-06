import core.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

//    final private static int CORE_COUNT = Runtime.getRuntime().availableProcessors();
//
//    public static void main(String[] args) {
//        ResizeConfig resizeConfig = null;
//
//        try {
//            resizeConfig = startProgram();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        boolean readyToWork = resizeConfig.requestResultConfig();
//
//        if (readyToWork) {
//            File dirForRead = new File(resizeConfig.getDIR_FOR_READ());
//
//            long start = System.currentTimeMillis();
//            File[] files = dirForRead.listFiles();
//            ConcurrentLinkedQueue<File> concurrentLinkedQueue = new ConcurrentLinkedQueue(Arrays.asList(files));
//
//            int needThreads = Math.min(concurrentLinkedQueue.size(), CORE_COUNT);
//            ArrayList<ImageResizeThread> imageResizeThreads = new ArrayList<>(needThreads);
//
//            for (int i = 0; i < needThreads; i++) {
//                ImageResizeThread imageResizeThread = new ImageResizeThread(resizeConfig, concurrentLinkedQueue, start);
//                imageResizeThreads.add(imageResizeThread);
//                imageResizeThread.start();
//            }
//
//
//            for (ImageResizeThread thread : imageResizeThreads) {
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            System.out.println("Ошибка ввода данных. Операция не выполнена");
//        }
//        System.out.println("Завершение работы программы");
//    }
//
//
//    private static ResizeConfig startProgram() throws IOException {
//        String dirForRead;
//        String dirForWrite;
//        int compression;
//        System.out.println("\tЗапуск программы:");
//        dirForRead = getDirectoryPath("Укажите какую папку с изображениями которые необходимо уменьшить");
//        dirForWrite = getDirectoryPath("Укажите какую папку, куда сохронять новые изображения");
//        compression = getCompressionValue("Укажите параметр сжатия, например: 30% (По умолчанию 0%)");
//        return new ResizeConfig(dirForRead, dirForWrite, compression);
//    }
//
//
//    private static String getDirectoryPath(String printInConsole) throws IOException {
//        System.out.println(printInConsole);
//        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
//            return bufferedReader.readLine().trim();
//        }
//    }
//
//    private static int getCompressionValue(String printInConsole) throws IOException {
//        System.out.println(printInConsole);
//        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
//            String value = bufferedReader.readLine().replaceAll("[^0-9]", "").trim();
//            if (value.length() == 0) {
//                return 0;
//            }
//            return Integer.parseInt(value);
//        }
//    }

}



