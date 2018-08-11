package com.jdp30.gui.Elements;

import com.jdp30.gui.GUIElement;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 11/08/2018.
 */
public class ProgressBar extends GUIElement {

    private int maxValue = 0,value = 0;
    private Color primary, secondary;

    public ProgressBar(int x, int y, int width, int height, int maxValue, Color primary, Color secondary) {
        super(x, y, width, height);
        this.maxValue = maxValue;
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(secondary);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setColor(Color.white);
        if(value == 0)
            return;
        graphics.setColor(primary);
        graphics.fillRect(0,0,(getWidth() * (((float)value)/((float)maxValue))),getHeight());
        graphics.setColor(Color.white);
    }

    @Override
    public void update(GameContainer container) {

    }

    public void setValue(int value){
        this.value = value;
    }
}
