package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;
import utilz.EnvValues;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(EnvValues.GAME_WIDTH, EnvValues.GAME_HEIGHT);
        setPreferredSize(size);

        System.out.println("Size: " + EnvValues.GAME_WIDTH + " x " + EnvValues.GAME_HEIGHT);
    }
    public void updateGame() {
        
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render((Graphics2D) g);
    }

    public Game getGame() {
        return game;
    }
}
