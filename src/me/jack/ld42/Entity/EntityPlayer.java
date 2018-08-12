package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import me.jack.ld42.Weapon.BasicProjectile;
import me.jack.ld42.Weapon.Projectile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 11/08/2018.
 */
public class EntityPlayer extends Entity {

    private Projectile currentProjectile;
    private float exp;
    private int level;

    public EntityPlayer(int x, int y) {
        super(x, y, 0, 0);
        setMaxHealth(100f);
        setHealth(100f);

        this.currentProjectile = new BasicProjectile();
    }

    private long lastShot;

    @Override
    public void update(Level level) {
        super.update(level);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            move(0, -moveSpeed, level);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            move(0, moveSpeed, level);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            move(-moveSpeed, 0, level);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            move(moveSpeed, 0, level);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && System.currentTimeMillis() - lastShot >= currentProjectile.getFireRate()) {
            level.addProjectile(new EntityProjectile(getX(), getY(), currentProjectile, level.getMouseLookingAtX(), level.getMouseLookingAtY(), this));
            lastShot = System.currentTimeMillis();
        }
        lookAt(level.getMouseLookingAtX(), level.getMouseLookingAtY());
    }

    public float expForLevel(int level) {
        return (float) Math.pow((level + 1) / 2, 2) + 20;
    }


    public void addExp(float expPoints) {
        this.exp += expPoints;
        if (exp >= expForLevel(level)) {
            level += 1;
            exp = exp - expForLevel(level - 1);
        }
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
