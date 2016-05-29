package iart.algorithms;

import iart.game.Hopeless;
import iart.utilities.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IDDFS extends Algorithm implements Runnable {

    private int maxDepth;
    private  ArrayList<Integer> initialTable;
    private ArrayList<Point> bestMoves = new ArrayList<>();

    /**
     * Iterative Deepening Depth First Search constructor
     * @param hope Hopeless Game Object
     */
    public IDDFS(Hopeless hope){
        this.hope = hope;
        initialTable = new ArrayList<>(hope.getTable());
        maxDepth = 1;
    }
    /**
     * Runnable IDDFS Search Algorithm
     */
    public void run(){

        ArrayList<Point> moveList;
        while (!isFinished())
        {
            hope.setTable(initialTable);
            moveList = new ArrayList<>();
            this.DepthFirstLimitedSearch(initialTable,0, moveList,0);
            maxDepth++;
        }

    }
    /**
     * Runnable Iterative Deepening Depth First Search Algorithm
     */
    void  DepthFirstLimitedSearch(ArrayList<Integer> currentTable, int depth, ArrayList<Point> moveList,int points){
        if(isFinished())
            return;

        hope.setTable(currentTable);

        ArrayList<Point> moves = hope.getAllValidMoves();

        Iterator<Point> iter = moves.iterator();

        while (iter.hasNext()) {

            Point validMove = iter.next();

            //resetBoard
            hope.setTable(currentTable);

            int playPoints = hope.makePlay(validMove, moves);

            ArrayList<Point> newMoveList = new ArrayList<>(moveList);

            newMoveList.add(validMove);

            if(hope.gameOver())
            {
                bestScore = points + playPoints;
                bestMoves = newMoveList;
                finished = true;
                break;
            }

            int newDepth = depth + 1;

            if(newDepth <= maxDepth)
                DepthFirstLimitedSearch(new ArrayList<>(hope.getTable()),newDepth, newMoveList,points+playPoints);
            iter = moves.iterator();
        }
    }
    /**
     * Get best IDDFS plays in order
     * @return list of points
     */
    public ArrayList<Point> getIDDFSMoves(){
        return bestMoves;
    }
}
