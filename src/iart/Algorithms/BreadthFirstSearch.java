package iart.algorithms;

import iart.game.Hopeless;
import iart.nodes.BreadthFirstNode;
import iart.utilities.Point;

import java.util.ArrayList;
import java.util.Iterator;


public class BreadthFirstSearch extends Algorithm implements Runnable{

    ArrayList<BreadthFirstNode> currentNodes = new ArrayList<>();

    private boolean bruteForce;

    BreadthFirstNode bestNode;

    /**
     * Breadth First Search constructor
     * @param hope Hopeless Game Object
     */
    public BreadthFirstSearch(Hopeless hope, boolean bruteForce) {
        this.hope = hope;
        this.bruteForce = bruteForce;
        this.bestScore = 0;
    }

    /**
     * Runnable Breadth First Search Algorithm
     */
    public void run(){
        currentNodes.add(new BreadthFirstNode(new Point(-1,-1),hope.getTable(),0,new ArrayList<>()));
        outerLoop:
        while((!bruteForce && !hope.gameOver()) || (!currentNodes.isEmpty())) {

            ArrayList<BreadthFirstNode> newNodes = new ArrayList<>();

            for (BreadthFirstNode node : currentNodes) {
                ArrayList<Integer> currentTable = new ArrayList<>(node.getTable());

                //resetBoard
                hope.setTable(currentTable);

                if(hope.gameOver())
                    continue ;

                ArrayList<Point> validMoves = hope.getAllValidMoves();

                Iterator<Point> iter = validMoves.iterator();

                while (iter.hasNext()) {

                    Point validMove = iter.next();
                    //resetBoard
                    hope.setTable(currentTable);

                    int playPoints = hope.makePlay(validMove, validMoves);

                    BreadthFirstNode newNode = new BreadthFirstNode(validMove, hope.getTable(), node.getScore() + playPoints, node.getListOfMoves());

                    newNodes.add(newNode);

                    if(!bruteForce)
                    {
                        if (hope.gameOver()) {
                            currentNodes = new ArrayList<>(newNodes);
                            break outerLoop;
                        }
                    }
                    else if(bruteForce && newNode.getScore()>bestScore) {
                        bestNode = newNode;
                        bestScore = newNode.getScore();
                    }


                    hope.setTable(currentTable);

                    iter = validMoves.iterator();
                }
            }
            currentNodes = new ArrayList<>(newNodes);
        }
        finished = true;

        if(!bruteForce)
            bestScore = currentNodes.get(currentNodes.size() - 1).getScore();
    }

    /**
     * Get best BFS plays in order
     * @return list of points
     */
    public ArrayList<Point> getBFSPlays() {
        if(!bruteForce)
            return currentNodes.get(currentNodes.size() - 1).getListOfMoves();
        else return bestNode.getListOfMoves();
    }
}
