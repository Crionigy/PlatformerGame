package entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.*;

import main.Game;

public abstract class Entity {

    protected static final float gravity = 0.04f * Game.SCALE;
    protected static final float jumpingSpeed = -2.25f * Game.SCALE;
    protected static final float fallSpeedAfterCollision = 0.5f * Game.SCALE;

    protected float posX;
    protected float posY;
    protected int width;
    protected int height;
    protected boolean isAttacking = false;
    protected boolean inAir = false;
    protected EntityMovement movement;
    protected Rectangle2D.Float hitBox;

    protected float entitySpeed = 0f;
    protected float airSpeed = 0f;

    public Entity(float x, float y, int width, int height, float entitySpeed) {
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;
        this.entitySpeed = entitySpeed;
    }

    protected void drawHitBox(Graphics2D g) {
        // For debugging
        g.setColor(Color.PINK);
        g.setStroke(new BasicStroke(3));
        g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float( posX, posY, width, height);
    }

    public float calcEntityXSpeed() {
        float speed = 0;

        if(movement.isMovingLeft()) {
            speed -= entitySpeed;
        } 

        if(movement.isMovingRight()) {
            speed += entitySpeed;
        }

        return speed;
    }

    public void isGoingToJump() {
        if(movement.isJumping() &&  !inAir) {
           jump();
        }
    }

    public void jump() {
        inAir = true;
        airSpeed = jumpingSpeed;
    }

    public void stopJumping() {
        inAir = false;
        setAirSpeed(0f);
    }

    public void updatePosX(float xSpeed, int[][] lvlData) {
        float nextPosX = hitBox.x + xSpeed;
        if(CanMoveHere(nextPosX, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            setHitBoxX(nextPosX);
        } else {
            setHitBoxX(GetEntityXPosNextToWall(xSpeed));
        }
    }

    public void updatePosY(int[][] lvlData) {
        float nextPosY = hitBox.y + airSpeed;

        if(CanMoveHere(hitBox.x, nextPosY, hitBox.width, hitBox.height, lvlData)) {
            setHitBoxY(nextPosY);
            setAirSpeed(airSpeed + gravity);
            return;
        }

        setHitBoxY(GetEntityYPosUnderRoofOrAboveFloor(airSpeed));

        if(airSpeed > 0) {
            stopJumping();
            return;
        } 

        setAirSpeed(fallSpeedAfterCollision);
    }

    public float GetEntityXPosNextToWall(float xSpeed) {
        int currentTile = (int)(hitBox.x / Game.TILES_SIZE);
        int tilePos = currentTile * Game.TILES_SIZE;

        // Right side
        if(xSpeed > 0) {
            int xOffset = (int)(Game.TILES_SIZE - hitBox.width);
            return tilePos + xOffset -1;
        } 

        // Left side
        return tilePos;
    }

    public float GetEntityYPosUnderRoofOrAboveFloor(float airSpeed) {
        int currentTile = (int)(hitBox.y / Game.TILES_SIZE);
        int tilePos = currentTile * Game.TILES_SIZE;

        // Falling
        if(airSpeed > 0) {
            int yOffset = (int)(Game.TILES_SIZE - hitBox.height);
            return tilePos + yOffset -1;
        }

        // Jumping
        return tilePos;
    }

    // Check the pixels bellow left and right corners
    public boolean isEntityOnFloor(int[][] lvlData) {
        float hitBoxBottomY = hitBox.y + hitBox.height + 1;
        float bottomRightCornerX = hitBox.x + hitBox.width;

        if(!isSolid(hitBox.x, hitBoxBottomY, lvlData)) {
            if(!isSolid(bottomRightCornerX, hitBoxBottomY, lvlData)) {
                return false;
            }
        }

        return true;
    }

    public boolean isMoving() {
        return movement.isMovingRight() || movement.isMovingLeft() || movement.isJumping() || inAir ? true : false;
    }

    public void setHitBoxX(float x) {
        this.hitBox.x = x;
    }

    public void setHitBoxY(float y) {
        this.hitBox.y = y;
    }

    public void setAirSpeed(float airSpeed) {
        this.airSpeed = airSpeed;
    }

    public EntityMovement getMovement() {
        return movement;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
}
