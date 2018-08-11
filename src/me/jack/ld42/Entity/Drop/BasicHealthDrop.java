package me.jack.ld42.Entity.Drop;

import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 11/08/2018.
 */
public class BasicHealthDrop extends Drop {

    public BasicHealthDrop(int x, int y) {
        super(x, y, 0, 0);
    }
    @Override
    public void use(Level level){
        level.player.setHealth(level.player.getHealth() + 10);
    }
}
