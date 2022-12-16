package ui;

import enums.GameStateEnum;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private final int xPos;
    private final int yPos;
    private final int rowIndex;
    private final int xPosOffset;
    private final GameStateEnum state;
    private int index;
    private BufferedImage[] img;
    private boolean mouseOver;
    private boolean mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameStateEnum state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        this.index = 0;
        this.xPosOffset = UIValues.BTN_SCALED_WIDTH / 2;

        loadImg();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xPosOffset, yPos, UIValues.BTN_SCALED_WIDTH, UIValues.BTN_SCALED_HEIGHT);
    }

    private void loadImg() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.BTN_ATLAS);
        img = new BufferedImage[3];

        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * UIValues.BTN_DEFAULT_WIDTH, rowIndex * UIValues.BTN_DEFAULT_HEIGHT, UIValues.BTN_DEFAULT_WIDTH, UIValues.BTN_DEFAULT_HEIGHT);
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(img[index], xPos - xPosOffset, yPos, UIValues.BTN_SCALED_WIDTH, UIValues.BTN_SCALED_HEIGHT, null);
    }

    public void update() {
        index = 0;

        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public  void applyGameState() {
        GameStateEnum.state = state;
    }

    public void resetMouseKeys() {
        mouseOver = false;
        mousePressed = false;
    }
}
