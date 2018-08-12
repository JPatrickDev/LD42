package me.jack.ld42.Entity.Enemy.AI;

import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jack on 11/08/2018.
 */
public class FloatingAI extends EnemyAI {

    private int currentTargetX = 0, currentTargetY = 0;
    private int xVel,yVel;

    Random r = new Random();

    @Override
    public Point getNextMove(Level level, Entity entity) {
        if(xVel == 0)
            updateVel();
        return new Point(xVel,yVel);
    }

    public void updateVel(){
        xVel = (int) ((int) (r.nextFloat() * 1.5) + 0.5);
        yVel = (int) ((int) (r.nextFloat() * 1.5) + 0.5);

        if(r.nextBoolean()){
            xVel *= -1;
        }
        if(r.nextBoolean()){
            yVel *= -1;
        }

    }

    public boolean reachedTarget(Entity entity) {
        return Math.abs(entity.getX() - currentTargetX) <= 1 && Math.abs(entity.getY() - currentTargetY) <= 1;
    }

    public void onCollide(){
        xVel *= -1;
        xVel /= 1.5;
        yVel *= -1;
        yVel /= 1.5;
    }
}
