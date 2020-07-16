package core;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private static int countImage;

    public static LinkedList<LinkedList<File>> getSeparatedFiles (File []files, int countPart) {
        LinkedList<LinkedList<File>> listFiles = new LinkedList<>();
        countImage = getCountJPGFiles(files);
        if (countImage < countPart) {
            countPart = countImage;
        }
        for (int i = 0; i < countPart; i++) {
            listFiles.add(new LinkedList<>());
        }

        int i = 0;
        for (File file : files) {
            if (i == listFiles.size()) {
                i = 0;
            }
            listFiles.get(i).add(file);
            i++;
        }

        return listFiles;
    }

    private static boolean controlFile(File file, String format) {
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(file.getPath());
        return matcher.find();
    }

    public static int getCountImage() {
        return countImage;
    }

    private static int getCountJPGFiles(File []files) {
        int countImage = 0;
        for (File file : files) {
            if (controlFile(file,".jpg")) {
                countImage++;
            }
        }
        System.out.println(countImage);
        return countImage;
    }
}
