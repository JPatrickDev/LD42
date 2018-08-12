package me.jack.ld42.States;

import com.jdp30.gui.Elements.ProgressBar;
import com.jdp30.gui.Elements.TextArea;
import com.jdp30.gui.GUIArea;
import me.jack.ld42.GUI.ExpDisplay;
import me.jack.ld42.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by Jack on 10/08/2018.
 */
public class InGameState extends BasicGameState {

    private Level level;
    private GUIArea hud;
    ProgressBar healthBar;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.level = new Level();
        hud = new GUIArea(0, 480 - 125, 480, 125);
        hud.addElement(new TextArea("Health", 0, 0, 100, 25));
        healthBar = new ProgressBar(0, 25, 100, 5, (int) level.player.getMaxHealth(), Color.red, Color.red.darker());
        hud.addElement(healthBar);
        hud.addElement(new TextArea("Exp", 0, 30, 100, 25));
        ExpDisplay exp  =new ExpDisplay(20, 50, 60, 60, Color.cyan, level.player);
        hud.addElement(exp);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
        hud.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        level.update(gameContainer.getInput());
        healthBar.setValue((int) level.player.getHealth());
        if (level.player.isDead())
            stateBasedGame.enterState(1);
    }

}

