package utilz;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;
import static main.Game.TILES_SIZE;

public class HelpMethods {
    
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {

        if(!isSolid(x, y, lvlData)){
            if(!isSolid(x+width, y+height, lvlData)) {
                if(!isSolid(x+width, y, lvlData)) {
                    if(!isSolid(x, y+height, lvlData)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean isSolid(float x, float y, int[][] lvlData) {
        if(x < 0 || x >= GAME_WIDTH) {
            return true;
        }
        if(y < 0 || y >= GAME_HEIGHT) {
            return true;
        }

        float xIndex =  x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if(value >= 48 || value < 0 || value != 11) {
            return true;
        } else {
            return false;
        }
    }

}
