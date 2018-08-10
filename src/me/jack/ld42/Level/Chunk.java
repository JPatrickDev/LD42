package me.jack.ld42.Level;

import me.jack.ld42.Level.Tile.EmptyTile;
import me.jack.ld42.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;



/**
 * Created by Jack on 10/08/2018.
 */
public class Chunk {
    private Tile[][] tiles;
    private int chunkX, chunkY;

    public Chunk(int cX, int cY) {
        this.tiles = new Tile[Level.CHUNK_SIZE][Level.CHUNK_SIZE];
        this.chunkX = cX;
        this.chunkY = cY;
        for (int x = 0; x != tiles.length; x++) {
            for (int y = 0; y != tiles[0].length; y++) {
                tiles[x][y] = new EmptyTile(x, y);
            }
        }
    }

    public void render(Graphics g) {

        for (int x = 0; x != tiles.length; x++) {
            for (int y = 0; y != tiles[0].length; y++) {
                tiles[x][y].render(g);
            }
        }
        g.setColor(Color.red);
        g.drawRect(0,0,Level.TILE_SIZE * Level.CHUNK_SIZE,Level.TILE_SIZE * Level.CHUNK_SIZE);
        g.setColor(Color.white);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }
}
