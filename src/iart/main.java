package iart;

import iart.Algorithms.AStarSearch;
import iart.Algorithms.BreadthFirstSearch;
import iart.Algorithms.DepthFirstSearch;
import iart.Algorithms.Greedy;
import iart.Game.Hopeless;
import iart.Utilities.Point;

import java.util.ArrayList;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class main {

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
        int i = 0;
        while(true){
            i++;
            Hopeless hopeAStar2 = new Hopeless(7,5,4);

            Hopeless hopeBFS = new Hopeless(7,5,4);

            hopeBFS.table = new ArrayList<>(hopeAStar2.table);

            int AStar = runAstar(hopeAStar2);
            int BFS = bfs(hopeBFS);

            if(AStar < BFS)
                break;
        }

        System.out.println("Times = " + i);
    }

    static void greedy(Hopeless hope){
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
    }


    static int runAstar(Hopeless hope){

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);


        //A STAR
        AStarSearch rip = new AStarSearch(hope);

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

    static void dfs(Hopeless hope){
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
    }

    static int bfs(Hopeless hope){
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
}
