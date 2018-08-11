package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Entity {
    private int x, y;
    private Image image;

    protected int moveSpeed = 3;

    public static SpriteSheet spriteSheet = null;
    private float angle = 0f;

    public Entity(int x, int y, Image image) {
        createSpritesheet();
        this.x = x;
        this.y = y;
        this.image = image;
        if (image != null)
            this.image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
    }

    public Entity(int x, int y, int tX, int tY) {
        this(x, y, null);
        this.image = spriteSheet.getSprite(tX, tY);
        this.image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
    }

    public void render(Graphics g) {
        g.rotate(x + this.image.getWidth() / 2, y + this.image.getHeight() / 2, this.angle);
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


    public void move(int x, int y, Level level) {
        //TODO: Collision
        if (!level.canMove(new Rectangle(this.x + x, this.y + y, Level.TILE_SIZE, Level.TILE_SIZE)))
            return;
        this.y += y;
        this.x += x;
    }

    public void lookAt(int tX, int tY) {
        int rX = tX - getX();
        int rY = tY - getY();
        float angle = (float) Math.atan2(rY, rX);
        this.setAngle((float) Math.toDegrees(angle) + 90);
    }

    public void update(Level level) {
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }
}
