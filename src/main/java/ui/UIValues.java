package ui;

import io.github.cdimascio.dotenv.Dotenv;
import utilz.EnvValues;

public class UIValues {
    private static final Dotenv dotenv = Dotenv.load();
    public static final int BTN_DEFAULT_WIDTH = Integer.parseInt(dotenv.get("BTN_DEFAULT_WIDTH"));
    public static final int BTN_DEFAULT_HEIGHT = Integer.parseInt(dotenv.get("BTN_DEFAULT_HEIGHT"));
    public static final int BTN_SCALED_WIDTH = (int) (BTN_DEFAULT_WIDTH * EnvValues.SCALE);
    public static final int BTN_SCALED_HEIGHT = (int) (BTN_DEFAULT_HEIGHT * EnvValues.SCALE);
    public UIValues() { }
}
