package iart.Algorithms;

import iart.Game.Hopeless;
import iart.Nodes.BreadthFirstNode;
import iart.Nodes.Node;
import iart.Utilities.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by up201305337 on 19-04-2016.
 */
public class BreadthFirstSearch implements Runnable{

    ArrayList<BreadthFirstNode> currentNodes = new ArrayList<>();

    Hopeless hope;

    boolean finished = false;

    public BreadthFirstSearch(Hopeless hope) {

        this.hope = hope;

    }
    public void run(){
        currentNodes.add(new BreadthFirstNode(new Point(-1,-1),hope.table,0,new ArrayList<>()));
        outerLoop:
        while(!hope.gameOver()) {

            ArrayList<BreadthFirstNode> newNodes = new ArrayList<>();

            for (BreadthFirstNode node : currentNodes) {
                ArrayList<Integer> currentTable = new ArrayList<>(node.getTable());

                //resetBoard
                hope.copyTable(currentTable);

                ArrayList<Point> validMoves = hope.getAllValidMoves();

                Iterator<Point> iter = validMoves.iterator();

                while (iter.hasNext()) {

                    Point validMove = iter.next();
                    //resetBoard
                    hope.copyTable(currentTable);

                    int playPoints = hope.makePlay(validMove, validMoves);

                    BreadthFirstNode newNode = new BreadthFirstNode(validMove, hope.table, node.getScore() + playPoints, node.getListOfMoves());

                    newNodes.add(newNode);

                    if (hope.gameOver()) {
                        currentNodes = new ArrayList<>(newNodes);
                        break outerLoop;
                    }

                    hope.table = new ArrayList<>(currentTable);

                    iter = validMoves.iterator();
                }
            }
            currentNodes = new ArrayList<>(newNodes);
        }
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public ArrayList<Point> getBFSPlays() {
        return currentNodes.get(currentNodes.size() - 1).getListOfMoves();
    }

    public int getBestScore(){return currentNodes.get(currentNodes.size() - 1).getScore(); }
}
