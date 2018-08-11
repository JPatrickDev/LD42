package me.jack.ld42.Level;

import me.jack.ld42.Entity.Enemy.EasyEnemy;
import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Level.Tile.EmptyTile;
import me.jack.ld42.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Jack on 10/08/2018.
 */
public class Chunk {
    private Tile[][] tiles;
    private int chunkX, chunkY;
    private String hashedPos;
    private ArrayList<Entity> stored = new ArrayList<Entity>();
    protected boolean retired = false;

    public Chunk(int cX, int cY) {
        this.tiles = new Tile[Level.CHUNK_SIZE][Level.CHUNK_SIZE];
        this.chunkX = cX;
        this.chunkY = cY;
        for (int x = 0; x != tiles.length; x++) {
            for (int y = 0; y != tiles[0].length; y++) {
                tiles[x][y] = new EmptyTile(x, y);
            }
        }
        this.hashedPos = "#" + (cX + ":" + cY).hashCode();
    }

    public void render(Graphics g) {
        g.translate(getChunkX() * Level.CHUNK_SIZE * Level.TILE_SIZE,getChunkY() * Level.CHUNK_SIZE* Level.TILE_SIZE);
        for (int x = 0; x != tiles.length; x++) {
            for (int y = 0; y != tiles[0].length; y++) {
                tiles[x][y].render(g);
            }
        }
        g.setColor(Color.red);
      //  g.drawRect(0,0,Level.TILE_SIZE * Level.CHUNK_SIZE,Level.TILE_SIZE * Level.CHUNK_SIZE);
        g.setColor(Color.white);
        g.translate(-getChunkX() * Level.CHUNK_SIZE * Level.TILE_SIZE,-getChunkY() * Level.CHUNK_SIZE * Level.TILE_SIZE);
    }

    public void onCreate(Level level){

    }
    public void retire(Level level){
        Point chunk = new Point(getChunkX(),getChunkY());
        for(Entity e : level.entities){
            if(e.getInsideChunk().equals(chunk)){
                stored.add(e);
                e.retired = true;
            }
        }
        this.retired = true;
    }

    public void load(Level level){
        this.retired = false;
        for(Entity e : stored){
            e.retired = false;
            level.entities.add(e);
        }
        stored.clear();
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public void update(Level level){
        //TODO: Update each tile.
    }
    public String getHashPos() {
        return hashedPos;
    }
}
