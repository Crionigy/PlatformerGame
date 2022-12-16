package utilz;

import io.github.cdimascio.dotenv.Dotenv;

public final class EnvValues {
    private static final Dotenv dotenv = Dotenv.load();
    public static final boolean DEBUGGING = Boolean.parseBoolean(dotenv.get("IS_DEBUGGING"));
    public static final int FPS = Integer.parseInt(dotenv.get("TARGET_FPS"));
    public static final int UPS = Integer.parseInt(dotenv.get("TARGET_UPS"));
    public static final Double NANO_SECOND = Double.parseDouble(dotenv.get("NANO_SECOND"));
    public static final int TILES_BASE_SIZE = Integer.parseInt(dotenv.get("TILES_BASE_SIZE"));
    public static final int TILES_BASE_WIDTH = Integer.parseInt(dotenv.get("TILES_BASE_WIDTH"));
    public static final int TILES_BASE_HEIGHT = Integer.parseInt(dotenv.get("TILES_BASE_HEIGHT"));
    public static final int WIDTH_TILES = Integer.parseInt(dotenv.get("WIDTH_TILES"));
    public static final int HEIGHT_TILES = Integer.parseInt(dotenv.get("HEIGHT_TILES"));
    public static final int PLAYER_BASE_WIDTH = Integer.parseInt(dotenv.get("PLAYER_BASE_WIDTH"));
    public static final int PLAYER_BASE_HEIGHT = Integer.parseInt(dotenv.get("PLAYER_BASE_HEIGHT"));
    public static final float SCALE = Float.parseFloat(dotenv.get("GAME_SCALE"));
    public static final int TILES_SIZE = (int) (TILES_BASE_SIZE * SCALE);
    public static final int PLAYER_WIDTH = (int) (PLAYER_BASE_WIDTH * SCALE);
    public static final int PLAYER_HEIGHT = (int) (TILES_BASE_HEIGHT * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * WIDTH_TILES;
    public static final int GAME_HEIGHT = TILES_SIZE * HEIGHT_TILES;
    public EnvValues() { }
}
