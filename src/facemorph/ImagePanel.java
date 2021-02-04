package facemorph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This panel displays an image inside a JFrame.
 *
 * @author arthu
 */
public class ImagePanel extends JPanel {

    private static final int DEFAULT_HEIGHT = 200;
    private static final int DEFAULT_WIDTH = 300;
    private int width = 0, height = 0;

    // Conversion between RGB and grayscale.
    private static final double CONV_BLUE = 0.0722;
    private static final double CONV_GREEN = 0.7152;
    private static final double CONV_RED = 0.2126;

    private int[][] pixelList;

    public ImagePanel() {
        setSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int line = 0; line < height; line++) {
            for (int col = 0; col < width; col++) {
                int gray = pixelList[line][col];
                g.setColor(new Color(gray, gray, gray));
                g.fillRect(col, line, 1, 1);
            }
        }
    }

    public void loadImage(File chosenFile) {
        try {

            BufferedImage img = ImageIO.read(chosenFile);
            width = img.getWidth();
            height = img.getHeight();
            pixelList = new int[height][];

            for (int line = 0; line < height; line++) {
                pixelList[line] = new int[width];
                for (int col = 0; col < width; col++) {
                    int rgb = 0;
                    try {
                        rgb = img.getRGB(col, line);
                        int grayScale = getGrayFromRGB(rgb);
                        pixelList[line][col] = grayScale;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ImagePanel: " + e);
                    }
                }
            }
            setSize();
            repaint();

        } catch (FileNotFoundException e) {
            System.out.println("ImagePanel: file " + chosenFile + " not found.");
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Convert an RGB value (represented by an integer) into a single grayscale
     * value.
     */
    private int getGrayFromRGB(int rgb) {
        Color c = new Color(rgb);
        int gray = (int) (CONV_RED * c.getRed() + CONV_GREEN * c.getGreen() + CONV_BLUE * c.getBlue());
        return gray;
    }

    /**
     * Set the size of the panel to that of the loaded image (if it exists).
     *
     */
    private void setSize() {
        if (width > 0) {
            setMinimumSize(new Dimension(width, height));
            setPreferredSize(new Dimension(width, height));
        } else {
            setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
            setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        }
    }
}
