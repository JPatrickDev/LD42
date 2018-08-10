package me.jack.ld42;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by Jack on 10/08/2018.
 */
public class Main {

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new LD42Game("LD42 - Theme: Running out of space"));
        agc.setDisplayMode(480,480,false);
        agc.setTargetFrameRate(60);
        agc.start();
    }

}
