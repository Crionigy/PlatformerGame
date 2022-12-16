package game;

public class GameMetrics {
    
    // FPS 
    private int frames = 0;
    private double deltaFrames = 0;
    private double timePerFrame;

    // UPS
    private int updates = 0;
    private double deltaUpdates = 0;
    private double timePerUpdate;

    // Timers
    private long currentTime;
    private long previousTime;
    private long lastCheck;

    public GameMetrics(int fps, int ups, double nanoSecond) {
        this.timePerFrame = nanoSecond / fps;
        this.timePerUpdate = nanoSecond / ups;
    }

    // Calcs and updates
    public void calcDeltaUpdates() {
        this.deltaUpdates += (this.currentTime -  this.previousTime) / this.timePerUpdate;
    }

    public void calcDeltaFrames() {
        this.deltaFrames += (this.currentTime -  this.previousTime) / this.timePerFrame;
    }

    public void updatePreviousTime() {
        this.previousTime = this.currentTime;
    }

    public void incrUpdates() {
        this.updates++;
    }

    public void incrFrames() {
        this.frames++;
    }

    public void decrDeltaUpdates() {
        this.deltaUpdates--;
    }

    public void decrDeltaFrames() {
        this.deltaFrames--;
    }

    // GETTERS & SETTERS
    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public double getDeltaFrames() {
        return deltaFrames;
    }

    public void setDeltaFrames(double deltaFrames) {
        this.deltaFrames = deltaFrames;
    }

    public double getTimePerFrame() {
        return timePerFrame;
    }

    public void setTimePerFrame(double timePerFrame) {
        this.timePerFrame = timePerFrame;
    }

    public int getUpdates() {
        return updates;
    }

    public void setUpdates(int updates) {
        this.updates = updates;
    }

    public double getDeltaUpdates() {
        return deltaUpdates;
    }

    public void setDeltaUpdates(double deltaUpdates) {
        this.deltaUpdates = deltaUpdates;
    }

    public double getTimePerUpdate() {
        return timePerUpdate;
    }

    public void setTimePerUpdate(double timePerUpdate) {
        this.timePerUpdate = timePerUpdate;
    }

    public long getPreviousTime() {
        return previousTime;
    }

    public void setPreviousTime(long previousTime) {
        this.previousTime = previousTime;
    }

    public long getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(long lastCheck) {
        this.lastCheck = lastCheck;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

}
