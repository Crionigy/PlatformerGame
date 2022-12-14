package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class LoadSave {
    
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String BTN_ATLAS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        BufferedImage img =  null;

        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return img;
    }

    public static int[][] getLevelData() {
        int[][] lvlData = new int[EnvValues.HEIGHT_TILES][EnvValues.WIDTH_TILES];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }

        return lvlData;
    }
}
