package me.jack.ld42.Entity.Enemy;

import me.jack.ld42.Entity.Entity;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class BaseEnemy extends Entity{
    public BaseEnemy(int x, int y, Image image) {
        super(x, y, image);
    }

    public BaseEnemy(int x, int y, int tX, int tY) {
        super(x, y, tX, tY);
    }
}
