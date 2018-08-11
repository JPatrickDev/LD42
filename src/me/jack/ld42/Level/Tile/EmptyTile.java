package me.jack.ld42.Level.Tile;

import java.util.Random;

/**
 * Created by Jack on 10/08/2018.
 */
public class EmptyTile extends Tile {
    public EmptyTile(int x, int y) {
        super(x, y, false, 0, 0);
        Random r = new Random();
        if(r.nextInt(3) == 0){
            this.image = this.spriteSheet.getSprite(1,0);
        }else{
            if(r.nextInt(3) == 0){
                this.image = this.spriteSheet.getSprite(0,1);
            }else{
                if(r.nextInt(3) == 0){
                    this.image = this.spriteSheet.getSprite(1,1);
                }
            }
        }
    }
}
