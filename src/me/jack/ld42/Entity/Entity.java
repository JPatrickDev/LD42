package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Entity {
    private int x, y;
    private Image image;

    protected int moveSpeed = 3;

    public static SpriteSheet spriteSheet = null;

    public Entity(int x, int y, Image image) {
        createSpritesheet();
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Entity(int x, int y, int tX, int tY) {
        this(x, y, null);
        this.image = spriteSheet.getSprite(tX, tY);
    }

    public void render(Graphics g) {
        g.drawImage(this.image, x, y);
        g.drawRect(x, y, Level.TILE_SIZE, Level.TILE_SIZE);
    }

    private void createSpritesheet() {
        if (Entity.spriteSheet == null) {
            try {
                spriteSheet = new SpriteSheet(new Image("res/entities.png"), Level.TILE_SIZE, Level.TILE_SIZE);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void move(int x,int y){
        //TODO: Collision
        this.y += y;
        this.x += x;
    }
    public void update(Level level){}
}
