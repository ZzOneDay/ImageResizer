import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

public class ImageResizeThread extends Thread {
    private LinkedList<File> files;
    private String dstFolder = Main.dstFolder;
    private int compressionPercentage = Main.compressionPercentage;

    ImageResizeThread(LinkedList<File> files) {
        this.files = files;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int maxSizeOldImage = Math.max(image.getHeight(), image.getWidth());
                int newMaxSizeImage = maxSizeOldImage * ((int) Math.round(1-compressionPercentage*0.01));

                BufferedImage newImage = Scalr.resize(image, newMaxSizeImage);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
