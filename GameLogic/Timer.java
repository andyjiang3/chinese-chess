package GameLogic;

public class Timer {

    private long startTime;
    private long stopTime;
    private boolean stillRunning;

    public Timer() {
        this.startTime = 0;
        this.stopTime = 0;
        this.stillRunning = false;
    }

    public void start() {
        startTime = System.nanoTime();   //In nanoseconds, more precision than currentTimeMillis(),
        stillRunning = true;
    }

    public void stop() {
        stopTime = System.nanoTime();
        stillRunning = false;
    }

    //in milliseconds
    public long getTime() {

        if (stillRunning) {
            return (System.nanoTime() - startTime) / 1000000;  //convert to milliseconds
        } else {
            return (stopTime  - startTime) / 1000000;
        }
    }
















}
