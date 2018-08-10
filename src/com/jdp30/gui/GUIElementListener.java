package com.jdp30.gui;

/**
 * Created by Jack on 21/04/2018.
 */
public interface GUIElementListener {

    void mouseDown(int x, int y, int button, GUIElement element);

    void mouseUp(int x, int y, GUIElement element);

    void mouseEnter(int x, int y, GUIElement element);

    void mouseLeave(int x, int y, GUIElement element);

    void keyPressed(char c, int code);

    // void mouseDown(int x, int y, int button, GUIElement element);

}
