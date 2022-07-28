package levels;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import main.Game;
import utilz.LoadSave;

import static main.Game.WIDTH_TILES;
import static main.Game.HEIGHT_TILES;
import static main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level leveOne;
    
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();

        leveOne =  new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics2D g) {

        for (int j = 0; j < HEIGHT_TILES; j++) {
            for (int i = 0; i < WIDTH_TILES; i++) {
                int index = leveOne.getSpriteIndex(i, j);
                int spriteX = i * TILES_SIZE;
                int spriteY = j * TILES_SIZE;
                g.drawImage(levelSprite[index], spriteX, spriteY, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return leveOne;
    }

}

