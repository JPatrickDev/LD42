package me.jack.ld42.Level;

import me.jack.ld42.Camera;
import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Entity.EntityPlayer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Jack on 10/08/2018.
 */
public class Level {


    public static final int TILE_SIZE = 32,CHUNK_SIZE = 8;

    private ArrayList<Chunk> chunks = new ArrayList<Chunk>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();


    private EntityPlayer player;

    private int cX = 0,cY = 0;
    public Level(){
        for(int x = -3; x != 3; x++){
            for(int y = -3; y != 3; y++){
                chunks.add(new Chunk(x,y));
            }
        }
       this.player = new EntityPlayer(0,0);
    }

    public void render(Graphics g){
        calculateCamera();
        g.translate(-cX,-cY);
        for(Chunk c : chunks){
            c.render(g);
        }
        for(Entity e : entities){
            e.render(g);
        }
        player.render(g);

    }

    private void calculateCamera(){
        int xDiff = (int) (player.getX() - 240);
        int yDiff = (int) (player.getY() - 240);
        cX = xDiff;
        cY = yDiff;
    }

    public void update(){
        for(Chunk c : chunks){
            c.update(this);
        }
        for(Entity e : entities){
            e.update(this);
        }

        player.update(this);
    }



}
