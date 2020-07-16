package core;

import java.io.File;
public class Sort {

    public static void sortFiles (File[] files) {
        int low = 0;
        int high = files.length - 1;
        quickSort(files, low, high);
    }


    private static void quickSort(File[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        int middle = low + (high - low) / 2;
        long target = array[middle].length();

        int i = low, j = high;
        while (i <= j) {
            while (array[i].length() < target) {
                i++;
            }

            while (array[j].length() > target) {
                j--;
            }

            if (i <= j) {//меняем местами
                File temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    private void printArrasFiles(File[] files) {
        for (File file : files) {
            System.out.println(file.getName() + file.length());
        }
    }
}
