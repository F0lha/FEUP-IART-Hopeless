package iart;

import iart.AStar.AStarSearch;
import iart.BBound.BBoundSearch;

import java.util.ArrayList;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class main {

    public static void main(String[] args)
    {
        Hopeless hopeAStar = new Hopeless(20,20,4);

        Hopeless hopeDFS = new Hopeless(20,20,4);

        Hopeless hopeBBound = new Hopeless(20,20,4);

        hopeDFS.table = new ArrayList<>(hopeAStar.table);

        hopeBBound.table = new ArrayList<>(hopeAStar.table);

        hopeAStar.print();

        runAstar(hopeAStar);

        dfs(hopeDFS);

        bbound(hopeBBound);
    }

    static void bbound(Hopeless hope){
        long startTime = System.currentTimeMillis();


        //A STAR
        BBoundSearch rip = new BBoundSearch(hope);

        ArrayList<Point> bestMoves = rip.getBBoundMoves();

        if(rip.getBestScore()!= 0)
            for(Point move : bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("Score = " + rip.getBestScore());

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);
    }


    static void runAstar(Hopeless hope){

        long startTime = System.currentTimeMillis();


        //A STAR
        AStarSearch rip = new AStarSearch(hope);

        ArrayList<Point> bestMoves = rip.getAStarMoves();

        if(rip.getBestScore()!= 0)
            for(Point move : bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("Score = " + rip.getBestScore());

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);
    }

    static void dfs(Hopeless hope){
        long startTime = System.currentTimeMillis();

        DepthFirstSearch rip = new DepthFirstSearch(hope);

        if(rip.bestScore!= 0)
            for(Point move : rip.bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();

        System.out.println("Moves = " + rip.bestMoves);
        System.out.println("Score = " + rip.bestScore);
        System.out.println("Solutions = " + rip.solutions);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);
    }
}
