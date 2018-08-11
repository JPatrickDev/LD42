package me.jack.ld42.Entity;

import me.jack.ld42.Level.Level;
import me.jack.ld42.Weapon.Projectile;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by Jack on 11/08/2018.
 */
public class EntityProjectile extends Entity {

    private Projectile projectile;
    private Point vel;
    private long spawned;
    private Entity owner;

    public EntityProjectile(int x, int y, Projectile projectile, int targetX, int targetY,Entity owner) {
        super(x, y, projectile.getImage());
        setHealth(1f);
        this.owner = owner;
        this.spawned = System.currentTimeMillis();
        this.projectile = projectile;
        float xSpeed = targetX - getX();
        float ySpeed = targetY - getY();
        float factor = (float) (projectile.getMoveSpeed() / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));

        //entity.lookAt(currentTargetX,currentTargetY);
        vel = new Point((int) (xSpeed * factor), (int) (ySpeed * factor));
    }

    @Override
    public void update(Level level) {
        super.update(level);
        move(vel.x, 0, level);
        move(0, vel.y, level);

        if(System.currentTimeMillis() - spawned >= projectile.getLifespan()){
            this.setDead(true,level);
        }

    }


    @Override
    public void onTouchEntity(Entity touched) {
        super.onTouchEntity(touched);
        touched.setHealth(touched.getHealth() - projectile.getDamage());
    }

    public Entity getOwner() {
        return owner;
    }
}
