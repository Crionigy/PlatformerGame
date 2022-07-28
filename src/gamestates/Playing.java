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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
