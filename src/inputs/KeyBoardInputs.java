package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().getMovement().setMovingUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().getMovement().setMovingLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().getMovement().setMovingDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().getMovement().setMovingRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().getMovement().setJumping(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().getMovement().setMovingUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().getMovement().setMovingLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().getMovement().setMovingDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().getMovement().setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().getMovement().setJumping(true);
                break;
        }
        
    }
}
