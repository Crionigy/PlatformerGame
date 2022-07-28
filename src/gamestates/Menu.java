package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;

public class Menu extends State implements BaseState {

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {     
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString("MENU", Game.GAME_WIDTH / 2, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {     
    }

    @Override
    public void mousePressed(MouseEvent e) {     
    }

    @Override
    public void mouseReleased(MouseEvent e) {     
    }

    @Override
    public void mouseMoved(MouseEvent e) {     
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.state = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {     
    }
    
}
