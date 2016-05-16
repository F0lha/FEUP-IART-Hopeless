package iart;

import iart.algorithms.*;
import iart.game.Hopeless;

import java.util.*;

/**
 * Created by Pedro Castro on 20/04/2016.
 */
public class Statistics {

    static void createStatistics(int sampleSize, int timeout, int tableRows, int tableCols){

        int i = 0;

        Hopeless AStar;

        Hopeless AStarSafe = new Hopeless(tableRows,tableCols,4);

        //Hopeless BFS = new Hopeless(tableRows,tableCols,4);

        //Hopeless DFS = new Hopeless(tableRows,tableCols,4);

        //Hopeless greedy = new Hopeless(tableRows,tableCols,4);

        //Hopeless iddfs = new Hopeless(tableRows,tableCols,4);

        ArrayList<ArrayList<Integer>> statistics = new ArrayList<>(2);

        while (i < sampleSize) {
            ArrayList<Integer> current = new ArrayList<>();

            AStar = new Hopeless(tableRows, tableCols, 4);

            //copying tables
            AStarSafe.copyTable(AStar.table);
            //BFS.copyTable(AStar.table);
            //DFS.copyTable(AStar.table);
            //greedy.copyTable(AStar.table);
            //iddfs.copyTable(AStar.table);

            //AStar Run
            try {
                AStarSearch AStartThread = new AStarSearch(AStar, false);
                Thread t = new Thread(AStartThread);
                t.start();
                t.join(timeout);
                if (AStartThread.isFinished()) {
                    current.add(0,AStartThread.getBestScore());
                } else {
                    System.out.println("Error AStar Safe");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //AStar Safe Run
            try {
                AStarSearch AStartThreadSafe = new AStarSearch(AStarSafe, true);
                Thread t = new Thread(AStartThreadSafe);
                t.start();
                t.join(timeout);
                if (AStartThreadSafe.isFinished()) {
                    current.add(1,AStartThreadSafe.getBestScore());
                } else {
                    System.out.println("Error AStar Improved");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
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
            //IDDFS Run
            try {
                IDDFS IDDFSThread = new IDDFS(iddfs);
                Thread t = new Thread(IDDFSThread);
                t.start();
                t.join(timeout);
                if(IDDFSThread.isFinished())
                {
                    current.add(IDDFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error IDDFS");
                    current.add(-1);
                    t.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        */
            statistics.add(current);
            i++;
        }
        printStatistics(statistics);
    }

    static void printStatistics(ArrayList<ArrayList<Integer>> statistics){

        List<Integer> listOfStats = new ArrayList<>(Collections.nCopies(5, 0));

        for(ArrayList<Integer> tableStats : statistics)
        {
            int bestIndex = getBestScoreIndex(tableStats);
            listOfStats.set(bestIndex,listOfStats.get(bestIndex)+1);
        }

        System.out.println("AStar Safe best : " + listOfStats.get(0));
        System.out.println("AStar Experimental best : " + listOfStats.get(1));
        //System.out.println("BFS best : " + listOfStats.get(1));
        //System.out.println("DFS best : " + listOfStats.get(2));
        //System.out.println("Greedy best : " + listOfStats.get(3));
        //System.out.println("IDDFS best : " + listOfStats.get(4));
    }

    static int getBestScoreIndex(ArrayList<Integer> tableStats){
        int index = 0, max = 0, maxIndex = 0;
        for(int stat : tableStats){
            System.out.println("Index = " + index);
            System.out.println("Score = " + stat);
            if(stat > max){
                max = stat;
                maxIndex = index;
            }
            index++;
        }
        return maxIndex;
    }
}
