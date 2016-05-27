package iart.algorithms;

import iart.nodes.AStarNode;
import iart.utilities.AStarNodeComparator;
import iart.utilities.HeuristicTable;
import iart.game.Hopeless;
import iart.utilities.Point;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.regex.Matcher;

public class AStarSearch extends Algorithm implements Runnable{
    Comparator<AStarNode> comparator;
    PriorityQueue<AStarNode> openList;
    Map<Integer, AStarNode> mapNode = new HashMap<>();

    private boolean goalstate;

    /**
     * A* Search Contructor
     * @param hope Hopeless Game Object
     * @param goalstate true then h(n) = 0, n being a goalstate
     */
    public AStarSearch(Hopeless hope, boolean goalstate) {
        this.goalstate = goalstate;

        this.hope = hope;

        comparator = new AStarNodeComparator();

        openList = new PriorityQueue<>(comparator);

        AStarNode.resetCounter();
    }

    /**
     * Runnable A* Search Algorithm
     */
    public void run(){
        AStarNode fNode = new AStarNode(-1,0,hope.getTable(),0,0,null);
        openList.add(fNode);
        mapNode.put(fNode.nodeID,fNode);

        while(!openList.isEmpty()) {
            AStarNode headNode = openList.peek();
            hope.setTable(new ArrayList<>(headNode.getTable()));

            if(hope.gameOver()) {
                finished = true;
                break;
            }
            openList.poll();

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();
            while (iter.hasNext()) {
                Point validMove = iter.next();

                //resetBoard
                hope.setTable(headNode.getTable());

                int tempPoints = hope.makePlay(validMove, validMoves);
                int value;
                if(hope.gameOver())
                    if(goalstate)
                        value = tempPoints + headNode.realScore;
                    else value = heuristicOverAchiever(tempPoints,headNode.realScore, hope);
                else value = heuristicOverAchiever(tempPoints,headNode.realScore, hope);

                AStarNode nextNode = new AStarNode(headNode.nodeID, headNode.level + 1, hope.getTable(), value, tempPoints + headNode.realScore, validMove);

                openList.add(nextNode);
                mapNode.put(nextNode.nodeID, nextNode);

                iter = validMoves.iterator();
            }
        }
        System.out.println("Size = " + openList.size());
        this.bestScore = openList.peek().realScore;
    }

    /**
     * Returns the size of OPEN
     * @return size of openList
     */
    public int getOpenListSize(){
        return openList.size();
    }

    /**
     * Returns the moves to be played in order. Must be run after the end of run().
     * @return list of moves
     */
    public ArrayList<Point> getAStarMoves(){
        ArrayList<Point> returningList = new ArrayList<>();
        AStarNode currentNode = openList.peek();
        while(currentNode.parentNode != -1)
        {
            returningList.add(currentNode.getMove());
            currentNode = mapNode.get(currentNode.parentNode);
        }
        return reverse(returningList);
    }

    /**
     * Reversing list utility function.
     * @param list list to be reversed
     * @param <T> type of list
     * @return reversed list
     */
    public static <T> ArrayList<T> reverse(ArrayList<T> list) {
        int length = list.size();
        ArrayList<T> result = new ArrayList<T>(length);

        for (int i = length - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public int heuristicF(int points,int realPoints, Hopeless hope, int size) {

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
     * Heuristic Funtions
     * @param movePoints last move score
     * @param realPoints sum of points previously made (without last move)
     * @param hope Hopeless game object
     * @return f*(n) = h*(n) + g(n)
     */
    public int heuristicOverAchiever(int movePoints,int realPoints, Hopeless hope) {
        int sum = 0;

          for (int j = 0; j < hope.getDifficulty(); j++)
              sum += Hopeless.getPoints((Collections.frequency(hope.getTable(), j + 1)));

        return sum + (movePoints + realPoints);
    }

    /**
     * Returns the current score of the incumbent node
     * @return score of the incumbent node
     */
    public int getTopScore(){
        return openList.peek().score;
    }

    /**
     * Returns the current level of the incumbent node
     * @return level of the incumbent node
     */
    public int getCurrentLevel(){
        return this.openList.peek().level;
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
