package me.jack.ld42.Entity.Enemy.AI;

import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jack on 11/08/2018.
 */
public class WanderingAI extends EnemyAI {

    private int currentTargetX = 0, currentTargetY = 0;

    @Override
    public Point getNextMove(Level level, Entity entity) {
        Random r = new Random();
        if (currentTargetX == 0 || reachedTarget(entity) || r.nextInt(20) == 0) {

            currentTargetX = entity.getX() + (r.nextInt(500) - 250);
            currentTargetY = entity.getY() + (r.nextInt(500) - 250);
        }
        float xSpeed = currentTargetX - entity.getX();
        float ySpeed = currentTargetY - entity.getY();
        float factor = (float) (entity.moveSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));

        //entity.lookAt(currentTargetX,currentTargetY);
        return new Point((int) (xSpeed * factor), (int) (ySpeed * factor));
    }

    public boolean reachedTarget(Entity entity) {
        return Math.abs(entity.getX() - currentTargetX) <= 1 && Math.abs(entity.getY() - currentTargetY) <= 1;
    }

}
