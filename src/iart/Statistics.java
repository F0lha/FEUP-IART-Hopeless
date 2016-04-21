package iart;

import iart.Algorithms.AStarSearch;
import iart.Algorithms.BreadthFirstSearch;
import iart.Algorithms.DepthFirstSearch;
import iart.Algorithms.Greedy;
import iart.Game.Hopeless;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pedro Castro on 20/04/2016.
 */
public class Statistics {

    static void createStatistics(int sampleSize, int timeout, int tableRows, int tableCols){

        int i = 0;

        Hopeless AStar = new Hopeless(tableRows,tableCols,4);

        Hopeless BFS = new Hopeless(tableRows,tableCols,4);

        Hopeless DFS = new Hopeless(tableRows,tableCols,4);

        Hopeless greedy = new Hopeless(tableRows,tableCols,4);

        ArrayList<ArrayList<Integer>> statistics = new ArrayList<>();

        while (i < sampleSize)
        {
            ArrayList<Integer> current = new ArrayList<>();

            AStar = new Hopeless(tableRows,tableCols,4);

            //copying tables
            BFS.copyTable(AStar.table);
            DFS.copyTable(AStar.table);
            greedy.copyTable(AStar.table);

            //AStar Run
            try {
                AStarSearch AStartThread = new AStarSearch(AStar);
                Thread t = new Thread(AStartThread);
                t.start();
                t.join(timeout);
                if(AStartThread.isFinished())
                {
                    current.add(AStartThread.getBestScore());
                }
                else
                {
                    System.out.println("Error AStar");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //BFS Run
            try {
                BreadthFirstSearch BFSThread = new BreadthFirstSearch(BFS);
                Thread t = new Thread(BFSThread);
                t.start();
                t.join(timeout);
                if(BFSThread.isFinished())
                {
                    current.add(BFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error BFS");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //DFS Run
            try {
                DepthFirstSearch DFSThread = new DepthFirstSearch(DFS);
                Thread t = new Thread(DFSThread);
                t.start();
                t.join(timeout);
                if(DFSThread.isFinished())
                {
                    current.add(DFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error DFS");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Greedy Run
            try {
                Greedy GreedyThread = new Greedy(greedy);
                Thread t = new Thread(GreedyThread);
                t.start();
                t.join(timeout);
                if(GreedyThread.isFinished())
                {
                    current.add(GreedyThread.getBestScore());
                }
                else
                {
                    System.out.println("Error Greedy");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            statistics.add(current);
            i++;
        }
        printStatistics(statistics);
    }

    static void printStatistics(ArrayList<ArrayList<Integer>> statistics){

        List<Integer> listOfStats = new ArrayList<>(Collections.nCopies(4, 0));

        for(ArrayList<Integer> tableStats : statistics)
        {
            int bestIndex = getBestScoreIndex(tableStats);
            listOfStats.set(bestIndex,listOfStats.get(bestIndex)+1);
        }

        System.out.println("AStar best : " + listOfStats.get(0));
        System.out.println("BFS best : " + listOfStats.get(1));
        System.out.println("DFS best : " + listOfStats.get(2));
        System.out.println("Greedy best : " + listOfStats.get(3));
    }

    static int getBestScoreIndex(ArrayList<Integer> tableStats){
        int index = 0, max = 0;
        for(int stat : tableStats){
            if(stat > max){
                max = stat;
                index = tableStats.indexOf(stat);
            }
        }
        return index;
    }
}
