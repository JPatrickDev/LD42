package me.jack.ld42.Weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class Projectile {

    private float moveSpeed,damage;
    private Image image;
    private String name;
    private int life;
    public static SpriteSheet spriteSheet = null;

    public Projectile(String name, float moveSpeed, float damage, int life, int tX,int tY){
        this.name = name;
        this.moveSpeed = moveSpeed;
        this.damage = damage;
        this.life = life;
        if(spriteSheet == null){
            try {
                spriteSheet = new SpriteSheet("res/projectiles.png",16,16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        this.image = spriteSheet.getSprite(tX,tY);
    }

    public Image getImage() {
        return image;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public long getLifespan() {
        return life;
    }

    public float getDamage() {
        return damage;
    }
}
