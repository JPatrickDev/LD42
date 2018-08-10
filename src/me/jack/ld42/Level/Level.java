package me.jack.ld42.Level;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Jack on 10/08/2018.
 */
public class Level {


    public static final int TILE_SIZE = 32,CHUNK_SIZE = 8;

    private ArrayList<Chunk> chunks = new ArrayList<Chunk>();

    public Level(){
        for(int x = -3; x != 3; x++){
            for(int y = -3; y != 3; y++){
                chunks.add(new Chunk(x,y));
            }
        }
    }

    public void render(Graphics g){
        for(Chunk c : chunks){
            g.resetTransform();
            g.translate(c.getChunkX() * Level.TILE_SIZE * Level.CHUNK_SIZE,c.getChunkY() * Level.TILE_SIZE * Level.CHUNK_SIZE);
            //g.translate(-xOff,-yOff);
            c.render(g);
        }
    }



}
