package com.jdp30.gui.Elements;

/**
 * Created by Jack on 04/05/2018.
 */
public class PasswordInput extends TextInput{
    public PasswordInput(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public String getDisplayText() {
        String current = getText();
        String out = "";
        for(int i = 0; i != current.length();i++){
            out += "-";
        }
        return out;
    }
}
