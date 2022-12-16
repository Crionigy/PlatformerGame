package utilz;
public class Validators {
    
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {

        if(isSolid(x, y, lvlData)){
            if(isSolid(x + width, y + height, lvlData)) {
                if(isSolid(x + width, y, lvlData)) {
                    return isSolid(x, y + height, lvlData);
                }
            }
        }

        return false;
    }

    public static boolean isSolid(float x, float y, int[][] lvlData) {
        if(x < 0 || x >= EnvValues.GAME_WIDTH) {
            return false;
        }
        if(y < 0 || y >= EnvValues.GAME_HEIGHT) {
            return false;
        }

        float xIndex =  x / EnvValues.TILES_SIZE;
        float yIndex = y / EnvValues.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        return value == 11;
    }

}
