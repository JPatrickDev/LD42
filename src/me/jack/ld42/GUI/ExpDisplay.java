package me.jack.ld42.GUI;

import com.jdp30.gui.GUIElement;
import me.jack.ld42.Entity.EntityPlayer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 12/08/2018.
 */
public class ExpDisplay extends GUIElement{
    private Color color;
    private EntityPlayer player;
    public ExpDisplay(int x, int y, int width, int height,Color color,EntityPlayer player) {
        super(x, y, width, height);
        this.color = color;
        this.player = player;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawString(player.getLevel() + "", getWidth()/2 - graphics.getFont().getWidth(player.getLevel() + "")/2, getHeight()/2 - graphics.getFont().getLineHeight()/2);
        graphics.setLineWidth(2f);
        graphics.drawArc(4, 4, getWidth() - 8, getHeight() - 8, 0, 360);
        graphics.setColor(color);
        graphics.drawArc(4, 4, getWidth() - 8, getHeight() - 8, 0, 360 * (player.getExp() / player.expForLevel(player.getLevel())));
        graphics.setColor(Color.white);
    }

    @Override
    public void update(GameContainer container) {

    }
}
