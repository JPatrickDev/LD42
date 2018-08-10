package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 11/08/2018.
 */
public class EntityPlayer extends Entity {

    public EntityPlayer(int x, int y) {
        super(x, y, 0, 0);
    }

    @Override
    public void update(Level level) {
        super.update(level);
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            move(0,-moveSpeed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            move(0,moveSpeed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            move(-moveSpeed,0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            move(moveSpeed,0);
        }
    }


}
