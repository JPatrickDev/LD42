package me.jack.ld42.Entity.Enemy.AI;

import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Level;

import java.awt.*;

/**
 * Created by Jack on 11/08/2018.
 */
public abstract class EnemyAI {

    public abstract Point getNextMove(Level level, Entity entity);
}
