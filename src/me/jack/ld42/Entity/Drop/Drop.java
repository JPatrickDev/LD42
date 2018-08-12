package me.jack.ld42.Entity.Drop;

import me.jack.ld42.Entity.Enemy.AI.FloatingAI;
import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Drop extends Entity {

    private static SpriteSheet spriteSheet = null;
    public static final Random r = new Random();
    private float xVel,yVel;
    private FloatingAI ai;
    public Drop(int x, int y, int tX, int tY) {
        super(x, y, tX, tY);
        ai = new FloatingAI();
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
        Point next = ai.getNextMove(level,this);
        move((int) next.x,0,level);
        move(0, (int) next.y,level);
    }


    public abstract void use(Level level);
}
