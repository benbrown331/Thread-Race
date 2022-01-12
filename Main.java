/*
    Benjamin Brown
    */

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        /*
         * Program should create multiple threads
         * and race them against each other.
         * 
         * First, gather user input about racer info.
         * Then call and join each hread, and output the results to
         * the user and mySQL
         */

        Console.println("Let's Race! \n");

        String race = Console.getRequiredString("Enter the name of your race: ");

        int numRunners = Console.getInt("\nHow many contestants are in"
                + " your race (between 2 and 10)? ", 1, 11);

        int length = Console.getInt("How many yards are in"
                + " your race (between 1 and 150 yards)? ", 0, 151);

        // ArrayList to create name of each runner.
        ArrayList<String> names = new ArrayList<String>();
        String name;
        int speed;
        int restProb;

        // RunnerThread list to eventually create threads
        ArrayList<RunnerThread> runnerThread = new ArrayList<RunnerThread>();

        // Get the unique info for each racer
        for (int i = 0; i < numRunners; i++) {
            // create name variable
            name = race + "_" + (i + 1);
            names.add(name);

            Console.println("Enter information needed for contestant " + (i + 1) + " :");

            // Speed between 1-100
            speed = Console.getInt("\n\tSpeed (yards per move between 1 and 100 )? ",
                    0, 101);

            // Probability between 1 and 100
            restProb = Console.getInt("\n\tPercent likelihood they will rest"
                    + " between moves" + "(between 1 and 100 )? ", 1, 100);
            Console.println();

            // use infromation to create new thread instance
            int progress = 0;
            runnerThread.add(new RunnerThread(name, length,
                    restProb, speed, progress));
        }

        Console.println("\nLet's start the race with the " + numRunners
                + " contestants\n");

        Console.println("Ready, get set, ...Go! \n\n");

        // Create ArrayList of Threads call the RunnerThreads
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < numRunners; i++) {
            Console.println("Starting runner " + names.get(i)
                    + " on thread " + "Thread-" + i + "\n");

            // create new thread, start it, and add to arrayList
            Thread newThread = new Thread(runnerThread.get(i));
            newThread.start();
            threadList.add(newThread);

        }

        // join threads together to allow
        // all threads to complete at same time
        for (Thread joinThreads : threadList) {
            try {
                joinThreads.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // print results of all races
        Console.println("Final results of ALL races: \n");
        RaceDB.printRace();
    }
}