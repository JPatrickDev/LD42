package me.jack.ld42.Entity.Enemy;

import me.jack.ld42.Entity.Drop.BasicHealthDrop;
import me.jack.ld42.Entity.Enemy.AI.FloatingAI;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jack on 12/08/2018.
 */
public class EntityAsteroid extends BaseEnemy{
    public static Image refImage = null;
    private FloatingAI ai;
    public EntityAsteroid(int x, int y, int width) {
        super(x, y, null);
        setMaxHealth(100);
        setHealth(100);
        if(this.refImage == null){
            try {
                refImage = new Image("res/asteroid.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        this.image = refImage.getScaledCopy(width,width);
        this.width  = image.getWidth();
        this.height = image.getHeight();
        this.ai = new FloatingAI();

    }

    @Override
    public void update(Level level) {
        super.update(level);
        Point next = this.ai.getNextMove(level, this);
        move(0, next.y, level);
        move(next.x, 0, level);
    }

    @Override
    public void onDeath(Level level) {
        super.onDeath(level);
        if(getWidth() > 40){
            ArrayList<Rectangle> newAsteroids = split(level);
            for(Rectangle r : newAsteroids){
                EntityAsteroid newA = new EntityAsteroid(getX() + r.x,getY() + r.y,r.width);
                level.toAdd.add(newA);
            }
        }else{
            level.toAdd.add(new BasicHealthDrop(getX(),getY()));
        }
    }

    @Override
    public float getExpPoints() {
        return 20;
    }

    public ArrayList<Rectangle> split(Level level){
        int currentArea = getWidth() * getHeight();
        int i = r.nextInt(3) + 4;
        ArrayList<Rectangle> rects = new ArrayList<>();
        for(int a = 0; a != i; a++){
            int area = -1;
            int width = -1;
            if(a == i - 1){
                area = currentArea;
                width = (int) Math.sqrt(currentArea);
            }else{
                width = ((int)Math.sqrt(currentArea/5));
                if(width < 8)
                    width = 8;
                area = width * width;
            }
            currentArea -= area;
            rects.add(new Rectangle(width,height));
        }
        Rectangle fitIn = new Rectangle(getWidth(),getHeight());
        ArrayList<Rectangle> finalList = new ArrayList<>();
        for(Rectangle r : rects){
            Rectangle pos = fitRect(fitIn,r,finalList);
            if(pos != null)
                finalList.add(pos);
        }
        if(finalList.size() <= 3){
          //  return split(level);
        }
        return finalList;
    }


    public Rectangle fitRect(Rectangle toFitIn, Rectangle r, ArrayList<Rectangle> existingRects){
        if(!toFitIn.contains(r)){
            return null;
        }
        boolean done = false;
        int x = 0, y = 0;
        while(!done){
            boolean found = true;
            r.setLocation(x,y);
            if(toFitIn.contains(r)){
                for(Rectangle rect : existingRects){
                    if(rect.intersects(r))
                        found = false;
                }
            }else{
                found = false;
            }
            if(found){
                return r;
            }else{
                x++;
                if(x == toFitIn.width){
                    x = 0;
                    y++;
                    if(y == toFitIn.height){
                        return null;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void collide() {
        ai.onCollide();
    }
}
