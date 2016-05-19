package iart;

import iart.graphics.MainMenu;
import iart.algorithms.*;
import iart.game.Hopeless;
import iart.utilities.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class HopelessMain {

    public static void main(String[] args)
    {

        Hopeless hopeAStar2 = new Hopeless(10,20,4);

        Hopeless hopeAStar3 = new Hopeless(10,20,4);

        hopeAStar3.table = new ArrayList<>(hopeAStar2.table);

        hopeAStar2.print();

        runAstar(hopeAStar2,true);

        runAstar(hopeAStar3,false);

        //dfs(hopeDFS);

        //greedy(hopeBBound);

        //bfs(hopeBFS);

        /*
        int i = 0, b = 0, e=0, tries = 1000;
        while(tries > 0){
            tries--;
            Hopeless hopeAStar2 = new Hopeless(5,5,4);

            Hopeless hopeBFS = new Hopeless(5,5,4);

            hopeBFS.table = new ArrayList<>(hopeAStar2.table);

            int AStar = runAstar(hopeAStar2);
            int Other = bfs(hopeBFS);

            if(AStar < Other)
                i++;
            else if(AStar > Other)
                b++;
            else e++;
        }

        System.out.println("AStar < Other : " + i);
        System.out.println("AStar > Other : " + b);
        System.out.println("AStar = Other : " + e);
*/
         //Statistics.createStatistics(100,200000,10,10);

        //Hopeless hopeAStar2 = new Hopeless(10,20,4);
        //runAstar(hopeAStar2);
    }

    /*
    static int greedy(Hopeless hope){
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);

        //A STAR
        Greedy rip = new Greedy(hope);

        ArrayList<Point> bestMoves = rip.getGreedyPlays();

        hope.table = initialTable;

        int points = 0;

            for(Point move : bestMoves)
            {
                points += hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("Greedy Score = " + points);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Greedy Time of Computing = " + totalTime);
        return points;
    }

*/
    static int runAstar(iart.game.Hopeless hope, boolean safe){

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);


        //A STAR
        AStarSearch rip = new AStarSearch(hope,safe);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        Thread t = new Thread(rip);

        t.start();

        try {
            System.out.println("Press any key to accelerate");
            Thread thread = new Thread() {
                public void run() {
                    String input = null;
                    do {
                        try {
                            // wait until we have data to complete a readLine()
                            try {
                                while (!br.ready()  /*  ADD SHUTDOWN CHECK HERE */) {
                                    Thread.sleep(200);
                                }
                                input = br.readLine();
                                if(input.equals("acc")) {
                                    rip.setAccelarator(true);
                                    }
                                else{
                                    System.out.println("Size = " + rip.getOpenListSize());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (InterruptedException e) {
                            return;
                        }
                    } while (!this.isInterrupted());
                }

                ;
            };
            thread.start();
            t.join();
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Point> bestMoves = rip.getAStarMoves();

        hope.table = initialTable;

        int points = 0;

        if(rip.getBestScore()!= 0)
            for(Point move : bestMoves)
            {
                points += hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("A* Score = " + rip.getBestScore());

        System.out.println("A* Sum Of Points = " + points);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("A* Time of Computing = " + totalTime);

        return rip.getBestScore();
    }
/*
    static int dfs(HopelessMain hope){
        long startTime = System.currentTimeMillis();

        DepthFirstSearch rip = new DepthFirstSearch(hope);

        if(rip.bestScore!= 0)
            for(Point move : rip.moves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();

        System.out.println("Moves = " + rip.moves);
        System.out.println("Score = " + rip.bestScore);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);
        return rip.bestScore;
    }

    static int bfs(HopelessMain hope){
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);

        BreadthFirstSearch rip = new BreadthFirstSearch(hope);

        hope.table = initialTable;

        int points = 0;

        for(Point move : rip.getBFSPlays())
            {
                points += hope.makePlay(move,new ArrayList<>());
            }
        System.out.println("Final ----");
        hope.print();

        System.out.println("BFS Moves = " + rip.getBFSPlays());
        System.out.println("BFS Score = " + points);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);

        return points;
    }
    */
}
