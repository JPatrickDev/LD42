package me.jack.ld42;

import me.jack.ld42.States.GameOverState;
import me.jack.ld42.States.InGameState;
import me.jack.ld42.States.MainMenuState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 10/08/2018.
 */
public class LD42Game extends StateBasedGame{
    public LD42Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenuState());
        addState(new InGameState());
        addState(new GameOverState());
    }
}
