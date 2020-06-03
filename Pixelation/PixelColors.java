package Pixelation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PixelColors {
    enum PixelColor{
        RED,
        GREEN,
        BLUE
    }

    private static List<Integer> redColorsList = new ArrayList<>();
    private static List<Integer> blueColorsList = new ArrayList<Integer>();
    private static List<Integer> greenColorsList = new ArrayList<Integer>();

    public static void getMiddleColors(BufferedImage source, int pixelSize, int x, int y,
                                       Integer[] middleRed, Integer[] middleBlue, Integer[] middleGreen) {
        setColorsList(source, pixelSize, x, y);

        middleRed[0] = getMiddleColor(pixelSize, PixelColor.RED);
        middleBlue[0] = getMiddleColor(pixelSize, PixelColor.BLUE);
        middleGreen[0] = getMiddleColor(pixelSize, PixelColor.GREEN);
    }

    private static void setColorsList(BufferedImage source, int pixelSize, int x, int y) {
        redColorsList.clear();
        blueColorsList.clear();
        greenColorsList.clear();

        for (int i = x; i < x + pixelSize; i++)
            for (int j = y; j < y + pixelSize; j++) {
                Color sourceColor = new Color(source.getRGB(i, j));
                redColorsList.add(sourceColor.getRed());
                blueColorsList.add(sourceColor.getBlue());
                greenColorsList.add(sourceColor.getGreen());
            }
    }

    private static int getMiddleColor(int pixelSize, PixelColor color) {
        int sumColors = 0;
        int middleColor = 0;
        switch (color){
            case RED :
                for (int colorNumber : redColorsList) sumColors += colorNumber;
                middleColor = sumColors/(int)Math.pow(pixelSize, 2);
                break;

            case BLUE:
                for (int colorNumber : blueColorsList) sumColors += colorNumber;
                middleColor = sumColors/(int)Math.pow(pixelSize, 2);
                break;

            case GREEN:
                for (int colorNumber : greenColorsList) sumColors += colorNumber;
                middleColor = sumColors/(int)Math.pow(pixelSize, 2);
                break;
        }
        return middleColor;
    }
}
