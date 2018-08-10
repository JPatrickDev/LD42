package com.jdp30.gui.Elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 21/04/2018.
 */
public class TextButton extends TextArea {

    public TextButton(String text, int x, int y, int width, int height, Color background, Color textC) {
        super(text, x, y, width, height, background, textC);
    }

    public TextButton(String text, int x, int y, int width, int height) {
        super(text, x, y, width, height);
    }

    public TextButton(String text, int x, int y, Image bg) {
        super(text, x, y, bg);
    }

}
