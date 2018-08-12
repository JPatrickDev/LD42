package me.jack.ld42.Entity.Enemy;

import me.jack.ld42.Entity.Enemy.AI.WanderingAI;
import me.jack.ld42.Entity.EntityProjectile;
import me.jack.ld42.Level.Level;
import me.jack.ld42.Weapon.BasicProjectile;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jack on 11/08/2018.
 */
public class EasyEnemy extends BaseEnemy {

    public EasyEnemy(int x, int y) {
        super(x, y, 1, 0);
        this.setMaxHealth(10f);
        this.setHealth(10f);
        this.ai = new WanderingAI();
    }

    @Override
    public void update(Level level) {
        super.update(level);
        Point next = this.ai.getNextMove(level,this);
        move(next.x,next.y,level);
        if(new Random().nextInt(25) == 0 && Math.abs(getX() - level.player.getX()) <= 128 && Math.abs(getY() - level.player.getY()) <= 128){
            lookAt(level.player.getX(),level.player.getY());
            level.addProjectile(new EntityProjectile(getX(),getY(),new BasicProjectile(),level.player.getX(),level.player.getY(),this));
        }
    }

    @Override
    public float getExpPoints() {
        return 5f;
    }
}
