package iart.AStar;

import iart.Hopeless;
import iart.Point;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarSearch {

    Comparator<AStarNode> comparator = new AStarNodeComparator();
    PriorityQueue<AStarNode> openList = new PriorityQueue<>(comparator);
    Map<Integer, AStarNode> mapNode = new HashMap<>();

    int row,col,difficulty;

    int bestScore;

    public AStarSearch(Hopeless hope) {

        this.row = hope.getRow();
        this.col = hope.getCol();
        this.difficulty = hope.getDifficulty();

        AStarNode fNode = new AStarNode(-1,0,hope.table,0,0,null);
        openList.add(fNode);
        mapNode.put(fNode.nodeID,fNode);

        while(!openList.isEmpty()) {
            AStarNode headNode = openList.peek();
            //if(mapNode.containsKey(headNode.parentNode))
              //  System.out.println("Last Play : " + (headNode.realScore - mapNode.get(headNode.parentNode).realScore) + " at level :" + headNode.level);

            hope.table = new ArrayList<>(headNode.table);

            System.out.println("Level: " + headNode.level);

            if(hope.gameOver())
                break;

            openList.poll();

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();

            while (iter.hasNext()) {
                Point validMove = iter.next();

                //resetBoard
                hope.copyTable(headNode.table);

                int tempPoints = hope.makePlay(validMove, validMoves);

                int value = heuristicF(tempPoints, hope, headNode.level);

                //System.out.println("Value = " + value);

                AStarNode nextNode = new AStarNode(headNode.nodeID, headNode.level + 1, hope.table, value, tempPoints + headNode.realScore, validMove);

                openList.add(nextNode);
                mapNode.put(nextNode.nodeID, nextNode);

                iter = validMoves.iterator();
            }
            //System.out.println("Best Play : " + bestPlay);
        }
    }

    public ArrayList<Point> getAStarMoves(){
        ArrayList<Point> returningList = new ArrayList<>();
        AStarNode currentNode = openList.peek();
        this.bestScore = currentNode.realScore;
        while(currentNode.parentNode != -1)
        {
            returningList.add(currentNode.move);
            currentNode = mapNode.get(currentNode.parentNode);
        }
        return reverse(returningList);
    }

    //reversing list
    public static <T> ArrayList<T> reverse(ArrayList<T> list) {
        int length = list.size();
        ArrayList<T> result = new ArrayList<T>(length);

        for (int i = length - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public int getBestScore(){
        return bestScore;
    }


    public int heuristicF(int points, Hopeless hope, int level){
        int tablePoints = points;

        HeuristicTable HTable = new HeuristicTable(hope.getRow() * hope.getCol());

        List<Integer> list = new ArrayList<>(Collections.nCopies(hope.getDifficulty(), 0));

        for(int i= 0;i < hope.getRow();i++)
        {
            for(int j = 0; j < hope.getCol();j++) {

                int pointColour = hope.getColor(new Point(i,j));

                if(pointColour != 0)
                    list.set(pointColour-1,list.get(pointColour-1));

                if(pointColour == 0)
                {
                    HTable.getTableRegions().set(i*hope.getCol()+j,0);
                    HTable.getTableVisited().set(i*hope.getCol()+j,1);
                }
                else{
                    recursiveRegions(i,j,hope,HTable,HTable.getNextColor());
                    HTable.addNextColor();
                }

            }

        }

        int limit = HTable.getNextColor();


        for(int i = 1; i < limit;i++){
            int removals = Collections.frequency(HTable.getTableRegions(), i);
            if(removals == 1)
                tablePoints++;
            else if(removals != 0) {
                tablePoints += Hopeless.getPoints(removals);
            }
        }

        int size = hope.getCol() * hope.getRow();
        double factorExp = 10.835 * Math.pow(Math.E, 5.835 * size / 1000);
        double factorLin = 0.195 * size + 3.589;
        double factorPol = -3.178/100000 * Math.pow(size, 2) + 0.208 * size + 2.742;

        double expectedLevel = factorExp; // Expected maximum level depth
        double depthFactor = level / expectedLevel;

        int weight =  (int) (Integer.MAX_VALUE / ((tablePoints + points) * Math.pow((1 + depthFactor), expectedLevel / 4)));
        return weight;
    }

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
}
