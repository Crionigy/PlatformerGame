package entities;

import static main.Game.SCALE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utilz.AnimationsEnum;
import utilz.LoadSave;

public class Player extends Entity{

    private BufferedImage[][] animations;

    private int animationTick;
    private int animationIndex;
    private int animationSpeed = 15;

    private AnimationsEnum playerAction = AnimationsEnum.IDLE;

    private int[][] lvlData;

    private float xDrawOffSet = 21 * SCALE;
    private float yDrawOffSet = 4 * SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height, 2.0f);
        movement = new EntityMovement();
        loadAnimations();
        initHitBox(x, y, 20 * SCALE, 27 * SCALE);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++) {
            for(int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }
    }

    private void setAnimation() {
        AnimationsEnum currentAnimation = playerAction;

        if(movement.isIdle()) {
            playerAction = AnimationsEnum.IDLE;
        } else {
            playerAction = AnimationsEnum.RUNNING;
        }

        if(inAir) {
            if(airSpeed > 0) {
                playerAction =  AnimationsEnum.JUMP;
            } else {
                playerAction =  AnimationsEnum.FALLING;
            }
        }

        if(isAttacking()) {
            playerAction = AnimationsEnum.ATK_1;
        }

        if(currentAnimation != playerAction) {
            resetTick();
        }
    }

    private void updatePos() {
        if(!isMoving()) {
            movement.setIdle(true);
            return;
        }

        float xSpeed = calcEntityXSpeed();
        movement.setIdle(false);

        isGoingToJump();

        if(!inAir) {
            inAir = !isEntityOnFloor(lvlData) ? true : false;
        }
        
        if(inAir) {
            updatePosY(lvlData);
        }

        updatePosX(xSpeed, lvlData);
    }

    private void resetTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updateTick() {
        animationTick++;

        if(animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= playerAction.amtOfSprites) {
                animationIndex = 0;
                this.setAttacking(false);
            }
        }
        
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void update() {
        updatePos();
        updateTick();
        setAnimation();
    }

    public void render(Graphics2D g) {
        g.drawImage(animations[playerAction.id][animationIndex], (int) (hitBox.x - xDrawOffSet), (int) (hitBox.y - yDrawOffSet), width, height, null);
        //drawHitBox(g);
    }    
}
