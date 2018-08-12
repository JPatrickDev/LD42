package me.jack.ld42.States;

import com.jdp30.gui.Elements.TextArea;
import com.jdp30.gui.Elements.TextButton;
import com.jdp30.gui.GUIArea;
import com.jdp30.gui.GUIElement;
import com.jdp30.gui.GUIElementListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by Jack on 10/08/2018.
 */
public class AboutState extends BasicGameState{

    private Image image;
    int mode;
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        mode = -1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        image = new Image("res/about.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(image,0,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(mode == 1)
            stateBasedGame.enterState(2);

    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        mode = 1;
    }
}

