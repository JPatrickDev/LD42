package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import me.jack.ld42.Weapon.BasicMissile;
import me.jack.ld42.Weapon.Projectile;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jack on 11/08/2018.
 */
public class EntityPlayer extends Entity {


    private float exp;
    private int level;
    private int chargeLevel;

    public EntityPlayer(int x, int y) {
        super(x, y, 0, 0);
        setMaxHealth(200f);
        setHealth(200f);

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
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            useCharge(level);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && System.currentTimeMillis() - lastShot >= level.parent.getSelected().getProjectile().getFireRate()) {
            level.addProjectile(new EntityProjectile(getX(), getY(), level.parent.getSelected().getProjectile(), level.getMouseLookingAtX(), level.getMouseLookingAtY(), this));
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

    public void charge() {
        this.chargeLevel += 5;
    }

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void useCharge(Level level){
        if(chargeLevel < 500){
            return;
        }
        chargeLevel = 0;
        level.reverseBorder(5000);
    }
}
