package entities;

import main.Game;

public class EntityMovement {

    private boolean isMovingUp = false;
    private boolean isMovingLeft = false;
    private boolean isMovingDown = false;
    private boolean isMovingRight = false;
    private boolean isIdle = true;
    private boolean isJumping = false;

    public void stopMoving() {
        this.isMovingUp = false;
        this.isMovingLeft = false;
        this.isMovingDown = false;
        this.isMovingRight = false;
        this.isIdle = true;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public void setMovingUp(boolean isMovingUp) {
        this.isMovingUp = isMovingUp;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDown(boolean isMovingDown) {
        this.isMovingDown = isMovingDown;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean isIdle) {
        this.isIdle = isIdle;
    }
}
