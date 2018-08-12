package me.jack.ld42.States;

import com.jdp30.gui.Elements.ProgressBar;
import com.jdp30.gui.Elements.TextArea;
import com.jdp30.gui.GUIArea;
import com.jdp30.gui.GUIElement;
import com.jdp30.gui.GUIElementListener;
import me.jack.ld42.GUI.ExpDisplay;
import me.jack.ld42.GUI.WeaponSlot;
import me.jack.ld42.Level.Level;
import me.jack.ld42.Weapon.BasicMissile;
import me.jack.ld42.Weapon.BasicProjectile;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;


/**
 * Created by Jack on 10/08/2018.
 */
public class InGameState extends BasicGameState implements GUIElementListener {

    public Level level;
    private GUIArea hud;
    private Image hudBg;
    ProgressBar healthBar, chargeBar;
    ArrayList<WeaponSlot> slots = new ArrayList<WeaponSlot>();

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        hudBg = new Image("res/hud.png");
        this.level = new Level(this);
        hud = new GUIArea(0, 480 - 125, 480, 125);
        hud.addElement(new TextArea("Health", 4, 4, 103, 13));
        healthBar = new ProgressBar(4, 19, 103, 13, (int) level.player.getMaxHealth(), Color.red, Color.red.darker());
        hud.addElement(healthBar);
        hud.addElement(new TextArea("Exp", 4, 36, 103, 14));
        ExpDisplay exp = new ExpDisplay(22, 56, 60, 60, Color.cyan, level.player);
        hud.addElement(exp);
        hud.addElement(new TextArea("Charge", 250, 95, 226, 11));
        chargeBar = new ProgressBar(250, 108, 226, 13, 500, Color.yellow, Color.yellow.darker());
        hud.addElement(chargeBar);


        WeaponSlot slotOne = new WeaponSlot(251, 5, new BasicProjectile(), 0, this);
        slotOne.setListener(this);
        hud.addElement(slotOne);
        slotOne.setSelected(true);
        WeaponSlot slotTwo = new WeaponSlot(296, 5, new BasicMissile(), 10, this);
        slotTwo.setListener(this);
        hud.addElement(slotTwo);

        slots.add(slotOne);
        slots.add(slotTwo);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
        graphics.drawImage(hudBg,hud.getX(),hud.getY());
        hud.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        hud.update(gameContainer);
        level.update(gameContainer.getInput());
        healthBar.setValue((int) level.player.getHealth());
        chargeBar.setValue((int) level.player.getChargeLevel());
        if (level.player.isDead())
            stateBasedGame.enterState(1);
    }

    @Override
    public void mouseDown(int x, int y, int button, GUIElement element) {
        System.out.println("Selected");
        if (element instanceof WeaponSlot) {
            setSelected((WeaponSlot) element);
        }
    }

    @Override
    public void mouseUp(int x, int y, GUIElement element) {

    }

    @Override
    public void mouseEnter(int x, int y, GUIElement element) {

    }

    @Override
    public void mouseLeave(int x, int y, GUIElement element) {

    }

    @Override
    public void keyPressed(char c, int code) {

    }


    @Override
    public void keyPressed(int key, char c) {
        hud.keyPressed(c, key);

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        hud.mouseDown(x - hud.getX(), y - hud.getY(), button);
    }


    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        hud.mouseUp(x - hud.getX(), y - hud.getY());
    }

    public WeaponSlot getSelected() {
        for (WeaponSlot slot : slots) {
            if (slot.isSelected())
                return slot;
        }
        return null;
    }

    public void setSelected(WeaponSlot s) {
        if (s.isLocked())
            return;
        for (WeaponSlot slot : slots) {
            slot.setSelected(false);
        }
        s.setSelected(true);
    }
}

