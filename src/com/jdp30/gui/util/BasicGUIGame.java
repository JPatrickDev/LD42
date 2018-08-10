package com.jdp30.gui.util;

import com.jdp30.gui.GUIArea;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Jack on 01/05/2018.
 */
public abstract class BasicGUIGame extends BasicGame {


    private ArrayList<GUIArea> areas = new ArrayList<>();

    public BasicGUIGame(String title) {
        super(title);
    }

    public void addGUIArea(GUIArea area) {
        areas.add(area);
    }

    @Override
    public void keyPressed(int key, char c) {
        for (GUIArea area : areas) {
            area.keyPressed(c,key);
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        for (GUIArea towersGUIArea : areas) {
            towersGUIArea.mouseDown(x - towersGUIArea.getX(), y - towersGUIArea.getY(), button);
        }
    }


    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        for (GUIArea towersGUIArea : areas) {
            towersGUIArea.mouseUp(x - towersGUIArea.getX(), y - towersGUIArea.getY());
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (GUIArea area : areas) {
            area.render(graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (GUIArea area : areas) {
            area.update(gameContainer);
        }
    }

}
