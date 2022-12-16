package game.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface State {

    void update();

    void draw(Graphics2D g);

    void mouseClicked(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseMoved(MouseEvent e);

    void keyPressed(KeyEvent e);
    
    void keyReleased(KeyEvent e);
}
