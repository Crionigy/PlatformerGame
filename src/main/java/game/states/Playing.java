package game.states;

import entities.Player;
import enums.GameStateEnum;
import levels.LevelManager;
import game.Game;
import utilz.EnvValues;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing implements State {

    protected Game game;
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        this.game = game;
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        int[][] levelData = levelManager.getCurrentLevel().getLevelData();
        player = new Player(200, 500, EnvValues.PLAYER_WIDTH, EnvValues.PLAYER_HEIGHT);
        player.loadLvlData(levelData);
        player.setInAir(player.isEntityOnFloor(levelData));
    }

    public void windowLostFocus() {
        player.getMovement().stopMoving();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.getMovement().setMovingUp(true);
            case KeyEvent.VK_A -> player.getMovement().setMovingLeft(true);
            case KeyEvent.VK_S -> player.getMovement().setMovingDown(true);
            case KeyEvent.VK_D -> player.getMovement().setMovingRight(true);
            case KeyEvent.VK_SPACE -> player.getMovement().setJumping(true);
            case KeyEvent.VK_BACK_SPACE -> GameStateEnum.state = GameStateEnum.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.getMovement().setMovingUp(false);
            case KeyEvent.VK_A -> player.getMovement().setMovingLeft(false);
            case KeyEvent.VK_S -> player.getMovement().setMovingDown(false);
            case KeyEvent.VK_D -> player.getMovement().setMovingRight(false);
            case KeyEvent.VK_SPACE -> player.getMovement().setJumping(false);
        }
    }
    
}
