package me.jack.ld42.Entity.Enemy;

import me.jack.ld42.Entity.Drop.BasicHealthDrop;
import me.jack.ld42.Entity.Enemy.AI.EnemyAI;
import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class BaseEnemy extends Entity{
    public EnemyAI ai;
    public BaseEnemy(int x, int y, Image image) {
        super(x, y, image);
    }

    public BaseEnemy(int x, int y, int tX, int tY) {
        super(x, y, tX, tY);
    }


    @Override
    public void onDeath(Level level) {
        super.onDeath(level);
        level.toAdd.add(new BasicHealthDrop(getX(),getY()));
    }
}
