package Pixelation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Pixelation {
    private final File IMAGE_FILE;

    private final int PIXEL_SIZE;

    private final BufferedImage SOURCE;

    private BufferedImage pixelImage;

    public Pixelation(int pixelSize, String imagePath) {
        try {
            IMAGE_FILE = new File(imagePath);

            SOURCE = ImageIO.read(IMAGE_FILE);

            exceptionCheck(pixelSize);

            pixelImage = new BufferedImage(SOURCE.getWidth(), SOURCE.getHeight(), SOURCE.getType());

        } catch (IOException e) {
            throw new InvalidParameterException("The file could not be opened.");
        }

        PIXEL_SIZE = pixelSize;

        imageToPixels();

        saveImage();

        System.out.println("Art is ready!");
    }

    private void exceptionCheck(int pixelSize){
        if(SOURCE.getWidth() == 0 ||  ((SOURCE.getWidth() & (SOURCE.getWidth() - 1)) != 0))
            throw new InvalidParameterException("Image width is not a power of two.");

        if(SOURCE.getHeight() == 0 ||  ((SOURCE.getHeight() & (SOURCE.getHeight() - 1)) != 0))
            throw new InvalidParameterException("Image height is not a power of two.");

        if(pixelSize == 0 ||  ((pixelSize & (pixelSize - 1)) != 0))
            throw new InvalidParameterException("Pixel side is not a power of two.");

        if(pixelSize > SOURCE.getWidth())
            throw new InvalidParameterException("Pixel side longer than image length.");

        if(pixelSize > SOURCE.getHeight())
            throw new InvalidParameterException("Pixel side longer than image height.");
    }

    private void imageToPixels() {
        for (int x = 0; x < SOURCE.getWidth(); x += PIXEL_SIZE) {
            for (int y = 0; y < SOURCE.getHeight(); y += PIXEL_SIZE) {
                Integer[] middleRedColor = new Integer[1];
                Integer[] middleBlueColor = new Integer[1];
                Integer[] middleGreenColor = new Integer[1];

                PixelColors.getMiddleColors(SOURCE, PIXEL_SIZE, x, y,
                        middleRedColor, middleBlueColor, middleGreenColor);

                Color newColor = new Color(middleRedColor[0], middleGreenColor[0], middleBlueColor[0]);

                setPixels(x, y, newColor.getRGB());
            }
        }
    }

    private void setPixels(int x, int y, int color) {
        for (int i = x; i < x + PIXEL_SIZE; i++)
            for (int j = y; j < y + PIXEL_SIZE; j++) {
                pixelImage.setRGB(i, j, color);
            }
    }

    private void saveImage() {
        try {
            File pixelFile = new File("pixelImage.jpg");
            ImageIO.write(pixelImage, "jpg", pixelFile);
        } catch (IOException e) {
            throw new InvalidParameterException("The file could not be saved.");
        }
    }
}