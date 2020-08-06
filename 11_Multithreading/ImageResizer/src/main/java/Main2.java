import core.ResizeConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main2 {
    public static void main(String[] args) {
        //test
        Configurator configurator = Configurator.getConfigurator();
        configurator.build();
        ResizeConfig resizeConfig = configurator.getResizeConfig();

        int needThreads = Math.min(resizeConfig.getConcurrentLinkedQueue().size(), 4);
        ArrayList<ImageResizeThread> imageResizeThreads = new ArrayList<>(needThreads);
        ConcurrentLinkedQueue<File> files = resizeConfig.getConcurrentLinkedQueue();

        for (int i = 0; i < needThreads; i++) {
            ImageResizeThread imageResizeThread = new ImageResizeThread(resizeConfig, files);
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
    }
}
