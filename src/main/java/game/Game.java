package game;

import enums.GameStateEnum;
import game.states.Menu;
import game.states.Playing;
import utilz.EnvValues;

import java.awt.Graphics2D;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private GameMetrics gMetrics;
    private Playing playing;
    private Menu menu;
    
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gMetrics = new GameMetrics(EnvValues.FPS, EnvValues.UPS, EnvValues.NANO_SECOND);
        gamePanel.requestFocus();

        startGameLoop();
    }

    public void update() {
        switch (GameStateEnum.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS, QUIT -> System.exit(0);
            default -> {
            }
        }
    }

    public void render(Graphics2D g) {
        switch (GameStateEnum.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
            default -> {
            }
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
        if(GameStateEnum.state == GameStateEnum.PLAYING) {
            playing.getPlayer().getMovement().stopMoving();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
