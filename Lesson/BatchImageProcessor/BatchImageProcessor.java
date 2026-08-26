package Lesson.BatchImageProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BatchImageProcessor {

    public static BufferedImage makeGray(BufferedImage image) {
        BufferedImage grayImage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 255;
                int green = (rgb >> 8) & 255;
                int blue = rgb & 255;

                int average = (red + green + blue) / 3;

                int grayRgb = (average << 16)
                            | (average << 8)
                            | average;

                grayImage.setRGB(x, y, grayRgb);
            }
        }

        return grayImage;
    }

    public static BufferedImage makeInversion(BufferedImage image) {
        BufferedImage invertedImage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 255;
                int green = (rgb >> 8) & 255;
                int blue = rgb & 255;

                int invertedRed = 255 - red;
                int invertedGreen = 255 - green;
                int invertedBlue = 255 - blue;

                int invertedRgb = (invertedRed << 16)
                                 | (invertedGreen << 8)
                                 | invertedBlue;

                invertedImage.setRGB(x, y, invertedRgb);
            }
        }

        return invertedImage;
    }

    public static void processImages() {
        File inputFolder = new File("Lesson/BatchImageProcessor/images");
        File outputFolder = new File("Lesson/BatchImageProcessor/output");

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }

        File[] files = inputFolder.listFiles();

        if (files == null) {
            System.out.println("No images found.");
            return;
        }

        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }

            try {
                BufferedImage image = ImageIO.read(file);

                if (image == null) {
                    continue;
                }

                BufferedImage grayImage = makeGray(image);
                BufferedImage invertedImage = makeInversion(image);

                String fileName = file.getName();
                String extension = getExtension(fileName);

                File grayFile = new File(
                    outputFolder,
                    "gray-" + fileName
                );

                File invertedFile = new File(
                    outputFolder,
                    "inverted-" + fileName
                );

                ImageIO.write(grayImage, extension, grayFile);
                ImageIO.write(invertedImage, extension, invertedFile);

                System.out.println("Processed: " + fileName);

            } catch (IOException e) {
                System.out.println("Could not process: " + file.getName());
            }
        }
    }

    public static String getExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');

        if (dot == -1) {
            return "png";
        }

        return fileName.substring(dot + 1);
    }

    public static void main(String[] args) {
        processImages();
    }
}