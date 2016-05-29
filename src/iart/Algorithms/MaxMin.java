package iart.algorithms;


import iart.game.Hopeless;
import iart.nodes.Node;
import iart.utilities.HeuristicTable;
import iart.utilities.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MaxMin extends  Algorithm implements Runnable{

    ArrayList<Point> listOfMoves = new ArrayList<>();

    public MaxMin(Hopeless hope){
        this.hope = hope;
    }

    public void run(){

        while (!hope.gameOver())
        {
            int bestPlay = 0;
            int bestPlayPoints = 0;
            Point bestMove = new Point(-1,-1);
            ArrayList<Integer> bestTable = new ArrayList<>();

            ArrayList<Integer> currentTable = new ArrayList<>(hope.getTable());

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();

            while(iter.hasNext())
            {
                Point move = iter.next();

                hope.setTable(currentTable);

                int points = hope.makePlay(move,validMoves);

                if(heuristicFunction(points,bestScore,hope) > bestPlay)
                {
                    bestPlay = heuristicFunction(points,bestScore,hope);
                    bestMove = move;
                    bestPlayPoints = points;
                    bestTable = new ArrayList<>(hope.getTable());
                }

                iter = validMoves.iterator();
            }

            listOfMoves.add(bestMove);
            bestScore += bestPlayPoints;

            hope.setTable(bestTable);
        }

        finished = true;
    }

    /**
     * Calculates f*(n) given g(n) and the current table
     * @param points points made in current play
     * @param realPoints points made before current play
     * @param hope Hopeless game object
     * @return f*(n)
     */
    public int heuristicFunction(int points,int realPoints, Hopeless hope) {

        int tablePoints = 0;

        HeuristicTable HTable = new HeuristicTable(hope.getRow() * hope.getCol());

        List<Integer> list = new ArrayList<>(Collections.nCopies(hope.getDifficulty(), 0));

        for (int i = 0; i < hope.getRow(); i++) {
            for (int j = 0; j < hope.getCol(); j++) {
                int pointColour = hope.getColor(new Point(i, j));

                if (pointColour != 0)
                    list.set(pointColour - 1, list.get(pointColour - 1));

                if (pointColour == 0) {
                    HTable.getTableRegions().set(i * hope.getCol() + j, 0);
                    HTable.getTableVisited().set(i * hope.getCol() + j, 1);
                } else {
                    recursiveRegions(i, j, hope, HTable, HTable.getNextColor());
                    HTable.addNextColor();
                }
            }
        }

        int limit = HTable.getNextColor();


        for (int i = 1; i < limit; i++) {
            int removals = Collections.frequency(HTable.getTableRegions(), i);
            //if (removals == 1)
            // tablePoints++;
            if (removals != 1) {
                tablePoints += Hopeless.getPoints(removals);
            }
        }

        return (points + tablePoints + realPoints);
    }

    /**
     * Maps the block that contains point i,j with a unique color.
     * @param i col coordinate
     * @param j row coordinate
     * @param hope Hopeless game Object
     * @param HTable utility table
     * @param HTableColor next unique color
     */
    void recursiveRegions(int i, int j, Hopeless hope, HeuristicTable HTable, int HTableColor){
        //if not visited
        if(HTable.getTableVisited().get(i*hope.getCol()+j) != 1) {

            HTable.getTableRegions().set(i * hope.getCol() + j, HTableColor);
            HTable.getTableVisited().set(i * hope.getCol() + j, 1);

            //surrounding

            if (i > 0 && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i - 1, j))))
                recursiveRegions(i - 1, j, hope, HTable, HTableColor);
            if (j > 0 && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i, j - 1))))
                recursiveRegions(i, j - 1, hope, HTable, HTableColor);
            if (i < hope.getRow() && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i + 1, j))))
                recursiveRegions(i + 1, j, hope, HTable, HTableColor);
            if (j < hope.getCol() && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i, j + 1))))
                recursiveRegions(i, j + 1, hope, HTable, HTableColor);
        }
    }

    /**
     * Get best MaxMin plays in order
     * @return list of points
     */
    public ArrayList<Point> getBestMaxMinPlays(){
        return listOfMoves;
    }

}
