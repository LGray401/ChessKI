package Helpers;

import java.util.concurrent.TimeUnit;

public class Stopwatch {
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;
    private String location;
    private long cumulativeElapsedTime;

    public Stopwatch(String location) {
        this.location = location;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.nanoTime();
        this.running = false;
        if (0 < startTime) {
            cumulativeElapsedTime += (stopTime - startTime);
        }

    }

    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }


    public void printElapsedTime(String message) {
        long elapsedTime = getElapsedTime();
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(elapsedTime);
        long nanoseconds = elapsedTime;
        System.out.printf(location + " %s: %d ms. %d ns.\n", message, milliseconds, nanoseconds);
    }

    public void cumulativeElapsedTime() {
        long seconds = TimeUnit.NANOSECONDS.toSeconds(cumulativeElapsedTime);
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(cumulativeElapsedTime);
        long nanosconds = cumulativeElapsedTime;
        System.out.printf(location + " cumulative elapsed time: %d sec. %d ms. %ns. \n", seconds, milliseconds);
    }
}
