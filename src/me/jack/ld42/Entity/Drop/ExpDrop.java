package me.jack.ld42.Entity.Drop;

import me.jack.ld42.Level.Level;

/**
 * Created by Jack on 11/08/2018.
 */
public class ExpDrop extends Drop {

    public ExpDrop(int x, int y) {
        super(x, y, 1, 0);
    }
    @Override
    public void use(Level level){
        level.player.addExp(r.nextInt(10) + 2);
    }
}
