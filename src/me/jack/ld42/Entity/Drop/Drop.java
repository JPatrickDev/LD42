package me.jack.ld42.Entity.Drop;

import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.Random;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Drop extends Entity {

    private static SpriteSheet spriteSheet = null;
    public static final Random r = new Random();
    private float xVel,yVel;
    public Drop(int x, int y, int tX, int tY) {
        super(x, y, tX, tY);
        xVel = r.nextFloat() * 10 - 5;
        yVel = r.nextFloat() * 10 - 5;
        setMaxHealth(1);
        setHealth(1);
        if (spriteSheet == null) {
            try {
                spriteSheet = new SpriteSheet(new Image("res/drops.png"), 16, 16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        this.image = spriteSheet.getSprite(tX,tY);
    }

    @Override
    public void update(Level level) {
        super.update(level);
        move((int) xVel,0,level);
        move(0, (int) yVel,level);
        xVel /= 1.1;
        yVel /= 1.1;
    }


    public abstract void use(Level level);
}
