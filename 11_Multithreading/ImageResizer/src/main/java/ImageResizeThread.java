import core.Result;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImageResizeThread extends Thread {
    Result result;
    ConcurrentLinkedQueue concurrentLinkedQueue;
    long start;

    public ImageResizeThread(Result result, ConcurrentLinkedQueue concurrentLinkedQueue, long start) {
        this.result = result;
        this.concurrentLinkedQueue = concurrentLinkedQueue;
        this.start = start;
    }

    @Override
    public void run() {
        try {
                Iterator<File> files = concurrentLinkedQueue.iterator();
                while (files.hasNext()) {
                    File file = files.next();
                    BufferedImage image = ImageIO.read(file);

                    int maxSizeOldImage = Math.max(image.getHeight(), image.getWidth());
                    int newMaxSizeImage = maxSizeOldImage * ((int) Math.round(1 - result.getCOMPRESSION() * 0.01));

                    BufferedImage newImage = Scalr.resize(image, newMaxSizeImage);
                    File newFile = new File(result.getDIR_FOR_WRITE() + "/" + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
