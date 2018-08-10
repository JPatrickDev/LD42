package com.jdp30.gui.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

/**
 * Created by Jack on 01/05/2018.
 */
public class NinePatchBuilder {

    /**
    * Get an image of the given width and height from the given 9patch
    *
    * @param patch The 9Patch Image
    * @param patchW The width of each patch
    * @paran patchH The height of each patch
    * @param targetWidth The width of the new image
    * @param targetHeight The height of the new image
    * 
    * @return An Image of the target width and height
    *
    */
    public static Image getImage(Image patch, int patchW, int patchH, int targetWidth, int targetHeight) {
        long start = System.currentTimeMillis();
        Image[][] patches = new Image[3][3];
        for (int x = 0; x != 3; x++) {
            for (int y = 0; y != 3; y++) {
                patches[x][y] = patch.getSubImage(x * patchW, y * patchH, patchW, patchH);
            }
        }
        ImageBuffer buffer = new ImageBuffer(targetWidth, targetHeight);


        for (int x = 1; x != (targetWidth / patchW); x++) {
            for (int y = 1; y != (targetHeight / patchH); y++) {
                drawImage(patches[1][1], x * patchW, y * patchH, buffer);
            }
        }


        for (int x = 0; x <= (int) targetWidth / patchW; x++) {
            drawImage(patches[1][0], x * patchW, 0, buffer);
            drawImage(patches[1][2], x * patchW, targetHeight - patchH, buffer);
        }

        for (int y = 0; y <= (int) targetHeight / patchH; y++) {
            drawImage(patches[0][1], 0, y * patchH, buffer);
            drawImage(patches[2][1], targetWidth - patchW, y * patchH, buffer);
        }


        drawImage(patches[0][0], 0, 0, buffer);
        drawImage(patches[2][0], targetWidth - patchW, 0, buffer);

        drawImage(patches[0][2], 0, targetHeight - patchH, buffer);
        drawImage(patches[2][2], targetWidth - patchW, targetHeight - patchH, buffer);

        long diff = System.currentTimeMillis() - start;
        System.out.println("Time: " + diff);

        return buffer.getImage();
    }

    private static void drawImage(Image i, int x, int y, ImageBuffer buffer) {
        if (x >= buffer.getWidth() || y >= buffer.getHeight())
            return;
        try {
            for (int iX = 0; iX != i.getWidth(); iX++) {
                for (int iY = 0; iY != i.getHeight(); iY++) {
                    Color c = i.getColor(iX, iY);
                    buffer.setRGBA(x + iX, y + iY, c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
                }
            }
        } catch (Exception e) {
        }
    }


}
