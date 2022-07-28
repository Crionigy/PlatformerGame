package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import static main.Game.PLAYER_HEIGHT;
import static main.Game.PLAYER_WIDTH;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements BaseState{

    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        int[][] levelData = levelManager.getCurrentLevel().getLevelData();
        player = new Player(200, 500, PLAYER_WIDTH, PLAYER_HEIGHT);
        player.loadLvlData(levelData);
        player.setInAir(!player.isEntityOnFloor(levelData) ? true : false);
    }

    public void windowLostFocus() {
        player.getMovement().stopMoving();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
       levelManager.update();
       player.update();
    }

    @Override
    public void draw(Graphics2D g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.getMovement().setMovingUp(true);
                break;
            case KeyEvent.VK_A:
                player.getMovement().setMovingLeft(true);
                break;
            case KeyEvent.VK_S:
                player.getMovement().setMovingDown(true);
                break;
            case KeyEvent.VK_D:
                player.getMovement().setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.getMovement().setJumping(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                GameState.state = GameState.MENU;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.getMovement().setMovingUp(false);
                break;
            case KeyEvent.VK_A:
                player.getMovement().setMovingLeft(false);
                break;
            case KeyEvent.VK_S:
                player.getMovement().setMovingDown(false);
                break;
            case KeyEvent.VK_D:
                player.getMovement().setMovingRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.getMovement().setJumping(false);
                break;
        }
    }
    
}
