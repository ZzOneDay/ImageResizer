package core;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResizeConfig {
    private final File DIR_FOR_READ;
    private final File DIR_FOR_WRITE;
    private final int COMPRESSION;

    public ResizeConfig(File DIR_FOR_READ, File DIR_FOR_WRITE, int COMPRESSION) {
        this.DIR_FOR_READ = DIR_FOR_READ;
        this.DIR_FOR_WRITE = DIR_FOR_WRITE;
        this.COMPRESSION = COMPRESSION;
    }

    public File getDIR_FOR_READ() {
        return DIR_FOR_READ;
    }

    public File getDIR_FOR_WRITE() {
        return DIR_FOR_WRITE;
    }

    public int getCOMPRESSION() {
        return COMPRESSION;
    }

    public ConcurrentLinkedQueue<File> getConcurrentLinkedQueue() {
        File[] files = DIR_FOR_READ.listFiles();
        return new ConcurrentLinkedQueue(Arrays.asList(files));
    }
}
