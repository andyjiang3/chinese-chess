package GameLogic;

public class Timer {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean stillRunning = false;

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
