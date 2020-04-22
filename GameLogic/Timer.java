package GameLogic;

/**
 *  * Timer used to keep track of time elapsed and update timer GUI.
 *  *
 *  * @author Andy Jiang
 */
public class Timer {

    private long startTime;
    private long stopTime;
    private boolean stillRunning;

    /**
     * Constructor that initiate the start and stop time.
     */
    public Timer() {
        this.startTime = 0;
        this.stopTime = 0;
        this.stillRunning = false;
    }

    /**
     * Start the timer.
     */
    public void start() {
        startTime = System.nanoTime();   //In nanoseconds, more precision than currentTimeMillis(),
        stillRunning = true;
    }

    /**
     * Stop the timer.
     */
    public void stop() {
        stopTime = System.nanoTime();
        stillRunning = false;
    }

    /**
     * Get status of timer
     * @return whether the timer is running or not
     */
    public boolean isStillRunning() {
        return this.stillRunning;
    }

    /**
     * Get time elapsed in milliseconds
     * @return the time elapsed in milliseconds.
     */
    public long getTime() {

        if (stillRunning) {
            return (System.nanoTime() - startTime) / 1000000;  //convert to milliseconds
        } else {
            return (stopTime - startTime) / 1000000;
        }
    }


}
