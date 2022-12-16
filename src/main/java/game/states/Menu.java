package game.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import enums.GameStateEnum;
import game.Game;
import ui.MenuButton;
import utilz.EnvValues;
import utilz.LoadSave;
import utilz.validators.IsIn;

public class Menu implements State, IsIn {

    protected Game game;
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;

    public Menu(Game game) {
        this.game = game;
        loadButtons();
        loadBackground();
    }

    @Override
    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        for(MenuButton menuButton: buttons) {
            menuButton.update();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for(MenuButton menuButton: buttons) {
            menuButton.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {     
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton menuButton: buttons) {
            if(isIn(e, menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton menuButton: buttons) {
            if(isIn(e, menuButton)) {
                if(menuButton.isMousePressed()) {
                    menuButton.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton menuButton: buttons) {
            if(isIn(e, menuButton)) {
                menuButton.setMouseOver(true);
                break;
            }
            menuButton.setMouseOver(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameStateEnum.state = GameStateEnum.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {     
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(EnvValues.GAME_WIDTH / 2, (int) (150 * EnvValues.SCALE), 0, GameStateEnum.PLAYING);
        buttons[1] = new MenuButton(EnvValues.GAME_WIDTH / 2, (int) (220 * EnvValues.SCALE), 1, GameStateEnum.OPTIONS);
        buttons[2] = new MenuButton(EnvValues.GAME_WIDTH / 2, (int) (290 * EnvValues.SCALE), 2, GameStateEnum.QUIT);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * EnvValues.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * EnvValues.SCALE);
        menuX = (EnvValues.GAME_WIDTH / 2) - (menuWidth / 2);
        menuY = (int)(45 * EnvValues.SCALE);
    }

    private void resetButtons() {
        for(MenuButton menuButton: buttons) {
            menuButton.resetMouseKeys();
        }
    }
}
