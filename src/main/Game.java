package main;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.Graphics2D;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private GameMetrics gMetrics;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final Double NANO_SECOND = 1000000000.0;

    private Playing playing;
    private Menu menu;

    public static final int TILES_DEFAULT_SIZE = 32;
    public static final int PLAYER_DEFAULT_WIDTH = 64;
    public static final int PLAYER_DEFAULT_HEIGHT = 40;
    public static final int WIDTH_TILES = 26; 
    public static final int HEIGHT_TILES = 14;
    public static final float SCALE = 2f;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int PLAYER_WIDTH = (int) (PLAYER_DEFAULT_WIDTH * SCALE);
    public static final int PLAYER_HEIGHT = (int) (PLAYER_DEFAULT_HEIGHT * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * WIDTH_TILES;
    public static final int GAME_HEIGHT = TILES_SIZE * HEIGHT_TILES;
    
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gMetrics = new GameMetrics(FPS_SET, UPS_SET, NANO_SECOND);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch(GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics2D g) {
        switch(GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;   
        }
    }

    @Override
    public void run() {       
        gMetrics.setPreviousTime(System.nanoTime());
        gMetrics.setLastCheck(System.currentTimeMillis());

        while(true) {
            gMetrics.setCurrentTime(System.nanoTime());

            gMetrics.calcDeltaFrames();
            gMetrics.calcDeltaUpdates();
            gMetrics.updatePreviousTime();

            if(gMetrics.getDeltaUpdates() >= 1) {
                update();
                gMetrics.incrUpdates();
                gMetrics.decrDeltaUpdates();
            }

            if(gMetrics.getDeltaFrames() >= 1) {
                gamePanel.repaint();
                gMetrics.incrFrames();
                gMetrics.decrDeltaFrames();
            }

            if(System.currentTimeMillis() - gMetrics.getLastCheck() >= 1000) {
                gMetrics.setLastCheck(System.currentTimeMillis());
                System.out.println("FPS: " + gMetrics.getFrames() + " | UPS: " + gMetrics.getUpdates());
                gMetrics.setFrames(0);
                gMetrics.setUpdates(0);
            }
        }

    }

    public void windowLostFocus() {
        if(GameState.state == GameState.PLAYING) {
            playing.getPlayer().getMovement().stopMoving();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
