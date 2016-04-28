package iart;

import iart.graphics.MainMenu;
import iart.algorithms.*;
import iart.game.Hopeless;
import iart.utilities.Point;

import java.util.ArrayList;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class HopelessMain {

    public static void main(String[] args)
    {
        /*
        Hopeless hopeAStar2 = new Hopeless(5,5,4);

        Hopeless hopeDFS = new Hopeless(5,5,4);

        Hopeless hopeBBound = new Hopeless(5,5,4);

        Hopeless hopeBFS = new Hopeless(5,5,4);

        hopeDFS.table = new ArrayList<>(hopeAStar2.table);

        hopeBBound.table = new ArrayList<>(hopeAStar2.table);

        hopeAStar2.table = new ArrayList<>(hopeAStar2.table);

        hopeBFS.table = new ArrayList<>(hopeAStar2.table);

        hopeAStar2.print();

        runAstar(hopeAStar2);

        dfs(hopeDFS);

        greedy(hopeBBound);

        bfs(hopeBFS);
*/
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
        //Statistics.createStatistics(10000,300,5,5);

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
    static int runAstar(iart.game.Hopeless hope){

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);


        //A STAR
        IDDFS rip = new IDDFS(hope);

        Thread t = new Thread(rip);

        t.start();


        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Point> bestMoves = rip.getIDDFSMoves();

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
