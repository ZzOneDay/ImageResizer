package core;

import java.io.File;

public class Result {
    private final String DIR_FOT_READ;
    private final String DIR_FOR_WRITE;
    private final int COMPRESSION;

    public Result(String DIR_FOT_READ, String DIR_FOR_WRITE, int COMPRESSION) {
        this.DIR_FOT_READ = DIR_FOT_READ;
        this.DIR_FOR_WRITE = DIR_FOR_WRITE;
        this.COMPRESSION = COMPRESSION;
    }

    public String getDIR_FOT_READ() {
        return DIR_FOT_READ;
    }

    public String getDIR_FOR_WRITE() {
        return DIR_FOR_WRITE;
    }

    public int getCOMPRESSION() {
        return COMPRESSION;
    }

    public static boolean checkResult(Result result) {
        if (!new File(result.getDIR_FOT_READ()).canRead()) {
            return false;
        }

        if (result.getCOMPRESSION() < 0 || result.getCOMPRESSION() > 100) {
            return false;
        }

        if (!new File(result.DIR_FOR_WRITE).canRead()) {
            System.out.println("\tСоздание папки: " + result.DIR_FOR_WRITE);
            new File(result.DIR_FOR_WRITE).mkdirs();
        }

        return true;
    }
}
