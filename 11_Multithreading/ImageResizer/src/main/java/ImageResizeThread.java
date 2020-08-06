import core.ResizeConfig;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImageResizeThread extends Thread {
    private final ResizeConfig resizeConfig;
    private final ConcurrentLinkedQueue<File> concurrentLinkedQueue;

    public ImageResizeThread(ResizeConfig resizeConfig, ConcurrentLinkedQueue<File> concurrentLinkedQueue) {
        this.resizeConfig = resizeConfig;
        this.concurrentLinkedQueue = concurrentLinkedQueue;
    }

    @Override
    public void run() {
        try {
                while (!concurrentLinkedQueue.isEmpty()) {
                    File file = concurrentLinkedQueue.poll();
                    BufferedImage image = ImageIO.read(file);
                    System.out.println("RESIZE: " + file.getName());

                    int maxSizeOldImage = Math.max(image.getHeight(), image.getWidth());
                    int newMaxSizeImage = maxSizeOldImage * ((int) Math.round(1 - resizeConfig.getCOMPRESSION() * 0.01));

                    BufferedImage newImage = Scalr.resize(image, newMaxSizeImage);
                    File newFile = new File(resizeConfig.getDIR_FOR_WRITE().getPath() + "/" + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
