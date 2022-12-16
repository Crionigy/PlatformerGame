package levels;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import game.Game;

import utilz.EnvValues;
import utilz.LoadSave;

public class LevelManager {
    private BufferedImage[] levelSprite;
    private final Level leveOne;
    
    public LevelManager(Game game) {
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

        for (int j = 0; j < EnvValues.HEIGHT_TILES; j++) {
            for (int i = 0; i < EnvValues.WIDTH_TILES; i++) {
                int index = leveOne.getSpriteIndex(i, j);
                int spriteX = i * EnvValues.TILES_SIZE;
                int spriteY = j * EnvValues.TILES_SIZE;
                g.drawImage(levelSprite[index], spriteX, spriteY, EnvValues.TILES_SIZE, EnvValues.TILES_SIZE, null);
            }
        }
    }

    public Level getCurrentLevel() {
        return leveOne;
    }

}

