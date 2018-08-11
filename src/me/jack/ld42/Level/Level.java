package me.jack.ld42.Level;

import me.jack.ld42.Camera;
import me.jack.ld42.Entity.Entity;
import me.jack.ld42.Entity.EntityPlayer;
import org.lwjgl.util.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jack on 10/08/2018.
 */
public class Level {


    public static final int TILE_SIZE = 32, CHUNK_SIZE = 8;

    private HashMap<String,Chunk> chunks = new HashMap<String,Chunk>();
    private HashMap<String,Chunk> retiredChunks = new HashMap<String,Chunk>();
    public ArrayList<Entity> entities = new ArrayList<Entity>();


    private EntityPlayer player;

    private int cX = 0, cY = 0;
    private int mouseLookingAtX, mouseLookingAtY;

    private Circle bounds = null;
    private ImageBuffer boundsBuffer = null;

    public Level() {
        for (int x = -3; x != 3; x++) {
            for (int y = -3; y != 3; y++) {
                chunks.put(hashPos(x,y),new Chunk(x, y));
            }
        }
        this.player = new EntityPlayer(0, 0);
        bounds = new Circle(0, 0, (Level.CHUNK_SIZE * Level.TILE_SIZE) * 1, 100);
    }

    float i = 1.0f;

    public void render(Graphics g) {
        calculateCamera();
        g.translate(-cX, -cY);
        for (Chunk c : chunks.values()) {
            c.render(g);
        }
        for (Entity e : entities) {
            e.render(g);
        }
        player.render(g);
        g.resetTransform();
        if (bounds != null) {
            //   g.setColor(new Color(128,128,128,128));
            //g.fill(bounds);
            // g.setColor(Color.white);
            generateBoundsImage();
            Image i = boundsBuffer.getImage();
            g.drawImage(i, 0, 0);
            try {
                i.destroy();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        bounds = new Circle(0, 0, Level.CHUNK_SIZE * Level.TILE_SIZE * 1 * i);
        i += 0.1f;

    }

    private void calculateCamera() {
        int xDiff = (int) (player.getX() - 240);
        int yDiff = (int) (player.getY() - 240);
        cX = xDiff;
        cY = yDiff;
    }

    public void update(Input input) {
        this.mouseLookingAtX = input.getMouseX() + cX;
        this.mouseLookingAtY = input.getMouseY() + cY;
        for (Chunk c : chunks.values()) {
            c.update(this);
        }
        for (Entity e : entities) {
            e.update(this);
        }

        player.update(this);

        chunkGen();
        retireChunks();


        Iterator it = chunks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Chunk c = (Chunk) pair.getValue();
            if (c.retired) {
                it.remove();
            }
        }
        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            Entity e = entityIterator.next();
            if (e.retired)
                entityIterator.remove();
        }

        System.out.println("Active: " + chunks.size() + " Retired: " + retiredChunks.size());

    }


    public int getMouseLookingAtX() {
        return mouseLookingAtX;
    }

    public int getMouseLookingAtY() {
        return mouseLookingAtY;
    }

    private void generateBoundsImage() {
        if (bounds == null) return;
        boundsBuffer = new ImageBuffer(480, 480);
        for (int x = 0; x != 480; x++) {
            for (int y = 0; y != 480; y++) {
                int wX = x + cX;
                int wY = y + cY;
                if (bounds.contains(wX, wY)) {
                    boundsBuffer.setRGBA(x, y, 0, 0, 0, 0);
                } else {
                    boundsBuffer.setRGBA(x, y, 255, 0, 0, 100);
                }
            }
        }
    }

    public boolean canMove(Rectangle newHitbox) {
        if (!bounds.contains(newHitbox.getX(), newHitbox.getY()) || !bounds.contains(newHitbox.getX() + newHitbox.getWidth(), newHitbox.getY()) || !bounds.contains(newHitbox.getX(), newHitbox.getY() + newHitbox.getHeight()) || !bounds.contains(newHitbox.getX() + newHitbox.getWidth(), newHitbox.getY() + newHitbox.getHeight())) {
            return false;
        }
        return true;
    }

    public static String hashPos(int x, int y) {
        return "#" + (x + ":" + y).hashCode();
    }

    public Chunk getChunkAt(int x, int y) {
        String hash = hashPos(x, y);
        if (!chunks.containsKey(hash)) return null;
        return chunks.get(hash);
    }

    public void chunkGen() {
        java.awt.Point currentChunk = player.getInsideChunk();
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                Point p = new Point(currentChunk.x + x, currentChunk.y + y);
                String chunkPos = hashPos(p.getX(), p.getY());
                if (!chunks.containsKey(chunkPos)) {
                    if (retiredChunks.containsKey(chunkPos)) {
                        Chunk c = retiredChunks.get(chunkPos);
                        c.load(this);
                        retiredChunks.remove(c.getHashPos());
                        chunks.put(chunkPos, c);
                        continue;
                    }
                    Chunk c = new Chunk(p.getX(),p.getY());
                    chunks.put(chunkPos, c);
                }
            }

        }
    }

    private void retireChunks(){
        for(Chunk c : this.chunks.values()){
            if (Math.abs(c.getChunkX() - player.getInsideChunk().x) <= 1 && Math.abs(c.getChunkY() - player.getInsideChunk().y) <= 1)
                continue;
            c.retire(this);
            retiredChunks.put(c.getHashPos(),c);
        }
    }
}
