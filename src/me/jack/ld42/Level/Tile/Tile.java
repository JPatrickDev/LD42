package me.jack.ld42.Level.Tile;

import me.jack.ld42.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 10/08/2018.
 */
public abstract class Tile{

    private int x,y;
    private boolean solid;
    private Image image;

    public static SpriteSheet spriteSheet = null;

    public Tile(int x, int y, boolean solid,Image image) {
        createSpritesheet();
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.image = image;
    }

    public Tile(int x,int y, boolean solid, int tX,int tY){
        this(x,y,solid,null);
        this.image = spriteSheet.getSprite(tX,tY);
    }

    public void render(Graphics g){
        g.drawImage(this.image,x * Level.TILE_SIZE,y * Level.TILE_SIZE);
       // g.drawRect(x * Level.TILE_SIZE,y * Level.TILE_SIZE,Level.TILE_SIZE,Level.TILE_SIZE);
    }

    private void createSpritesheet(){
        if(Tile.spriteSheet == null){
            try {
                spriteSheet = new SpriteSheet(new Image("res/tiles.png"), Level.TILE_SIZE,Level.TILE_SIZE);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
}
