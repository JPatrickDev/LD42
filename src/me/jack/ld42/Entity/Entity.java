package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Entity {
    private int x, y,width,height;
    private Image image;

    public int moveSpeed = 4;

    public static SpriteSheet spriteSheet = null;
    private float angle = 0f;
    public boolean retired = false;
    private float health = 0,maxHealth = 0;
    private boolean isDead = false;
    public Entity(int x, int y, Image image) {
        createSpritesheet();
        this.x = x;
        this.y = y;
        this.image = image;
        if (image != null) {
            this.image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
    }

    public Entity(int x, int y, int tX, int tY) {
        this(x, y, null);
        this.image = spriteSheet.getSprite(tX, tY);
        this.image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void render(Graphics g) {
        g.rotate(x + this.image.getWidth() / 2, y + this.image.getHeight() / 2, this.angle);
        g.drawImage(this.image, x, y);
        g.drawRect(x, y,getWidth(), getWidth());
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


    public boolean move(int x, int y, Level level) {
        if (!level.canMove(new Rectangle(this.x + x, this.y + y, getWidth(), getHeight()),this))
            return false;
        this.y += y;
        this.x += x;
        return true;
    }

    public void lookAt(int tX, int tY) {
        int rX = tX - getX();
        int rY = tY - getY();
        float angle = (float) Math.atan2(rY, rX);
        this.setAngle((float) Math.toDegrees(angle) + 90);
    }

    public void update(Level level) {
        if(isDead())
            return;
        if(health <= 0)
            setDead(true);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public Point getInsideChunk(){
        int x = (Math.floorDiv((int) getX(), (Level.CHUNK_SIZE * Level.TILE_SIZE)));
        int y = Math.floorDiv((int) getY(), (Level.CHUNK_SIZE * Level.TILE_SIZE));
        return new Point(x, y);
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setMaxHealth(float health) {
        this.maxHealth = health;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public float getHealth() {
        return health;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void onTouchEntity(Entity touched){}

    public float getMaxHealth() {
        return maxHealth;
    }
}
