package utilz;

import java.util.HashMap;
import java.util.Map;

public enum AnimationsEnum {
    IDLE(0, 5),
    RUNNING(1, 6),
    JUMP(2, 3),
    FALLING(3, 1),
    GROUND(4, 2),
    HIT(5, 4),
    ATK_1(6, 3),
    JUMP_ATK_1(7, 3),
    JUMP_ATK_2(8, 3);

    public final int id;

    public final int amtOfSprites;

    private static final Map<Integer, AnimationsEnum> BY_ID = new HashMap<>();
    private static final Map<Integer, AnimationsEnum> BY_AMT_OF_SPRITES = new HashMap<>();

    static {
        for (AnimationsEnum e: values()) {
            BY_ID.put(e.id, e);
            BY_AMT_OF_SPRITES.put(e.amtOfSprites, e);
        }
    }

    private AnimationsEnum(int id, int amtOfSprites) {
        this.id = id;
        this.amtOfSprites = amtOfSprites;
    }

    public static AnimationsEnum valueOfId(int id) {
        return BY_ID.get(id);
    }

    public static AnimationsEnum valueOfSprites(int amtOfSprites) {
        return BY_AMT_OF_SPRITES.get(amtOfSprites);
    }
}
