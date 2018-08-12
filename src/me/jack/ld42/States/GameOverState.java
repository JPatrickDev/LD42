package me.jack.ld42.States;

import com.jdp30.gui.Elements.ProgressBar;
import com.jdp30.gui.Elements.TextArea;
import com.jdp30.gui.Elements.TextButton;
import com.jdp30.gui.GUIArea;
import com.jdp30.gui.GUIElement;
import com.jdp30.gui.GUIElementListener;
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
public class GameOverState extends BasicGameState implements GUIElementListener {

    private GUIArea area;
    int mode;
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        mode = -1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        area = new GUIArea(0,0,gameContainer.getWidth(),gameContainer.getHeight());
        area.addElement(new TextArea("Game Over!",0,0,area.getWidth(),50,Color.black,Color.white));
        area.addElement(new TextButton("Play Again",50,170,gameContainer.getWidth()-100,50,Color.gray,Color.white).setListener(this));
        area.addElement(new TextButton("Back To Main Menu",50,230,gameContainer.getWidth()-100,50,Color.gray,Color.white).setListener(this));
        area.addElement(new TextButton("Quit",50,290,gameContainer.getWidth()-100,50,Color.gray,Color.white).setListener(this));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        area.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(mode == 1)
            stateBasedGame.enterState(0);
        if(mode == 2)
            stateBasedGame.enterState(2);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        area.mouseDown(x,y,button);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        area.mouseUp(x,y);
    }

    @Override
    public void mouseDown(int x, int y, int button, GUIElement element) {
        String text = ((TextButton)element).getText();
        if(text.equalsIgnoreCase("Quit")){
            System.exit(0);
        }else if(text.equalsIgnoreCase("Play Again")){
            this.mode = 1;
        }else if(text.equalsIgnoreCase("Back To Main Menu")){
            this.mode = 2;
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
}

