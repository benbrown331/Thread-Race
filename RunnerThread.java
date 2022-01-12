/*
    Benjamin Brown
    */

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class RunnerThread implements Runnable {

    // Private variables for unique information of each runner.
    // Convert restProb to double

    // I saved the time values as double to prevent multiple threads
    // from trying to access the same time.
    private String name;
    private int length;
    private int restProb;
    private int speed;
    private int progress;
    private Instant start;
    private int total;

    public RunnerThread() {
    }

    public RunnerThread(String name, int length,
            int restProb, int speed, int progress) {
        this.name = name;
        this.length = length;
        this.restProb=restProb;
        this.speed = speed;
        this.progress = progress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void setLentgh(int length) {
        this.length = length;
    }

    public int getRestProb() {
        return restProb;
    }

    public void setRestProb(int restProb) {
        this.restProb = restProb;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {

        start = Instant.now();
        Random randNum = new Random();
        //number between 0 and 100
        int chance = randNum.nextInt(100);
        //each thread should loop until race is complete
        while (progress < length) {
            //for every movement the runner will rest
            try {
                Thread.sleep(length);
                chance = randNum.nextInt(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //if chance permits, runner will also move forward
            if (chance <= restProb) {
                progress += speed;
                chance = randNum.nextInt(100);
            }

        }

        // calculate the elapsed time
        total = Duration.between(start, Instant.now()).toMillisPart();

        // print race order to console as racers finish
        Console.println(name + ":\t finished in "
                + total + " milliseconds!");

        // insert race to database
        RaceDB.insertRace(name, total);
    }
}
