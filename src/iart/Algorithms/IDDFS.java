package iart.algorithms;

import iart.game.Hopeless;
import iart.nodes.IDDFSNode;
import iart.utilities.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by NIAEFEUP on 21-04-2016.
 */
public class IDDFS extends Algorithm implements Runnable {

    private int maxDepth;
    private IDDFSNode lastNode;
    private  ArrayList<Integer> initialTable;

    static HashMap<Integer,IDDFSNode> nodeMap;

    public IDDFS(Hopeless hope){
        this.hope = hope;
        initialTable = new ArrayList<>(this.hope.table);
        maxDepth = 1;
        nodeMap = new HashMap<>();
    }

    public void run(){
        while (!isFinished())
        {
            nodeMap = new HashMap<>();
            hope.copyTable(initialTable);
            IDDFSNode initialNode = new IDDFSNode(null,hope.table,0,-1,0);
            nodeMap.put(initialNode.getNodeID(),initialNode);
            this.DepthFirstLimitedSearch(initialNode,1);
            maxDepth++;
        }

    }

    void  DepthFirstLimitedSearch(IDDFSNode currentNode, int depth){
        if(isFinished())
            return;


        this.hope.copyTable(currentNode.getTable());

        ArrayList<Point> moves = this.hope.getAllValidMoves();

        Iterator<Point> iter = moves.iterator();

        while (iter.hasNext()) {

            Point validMove = iter.next();

            //resetBoard
            hope.copyTable(currentNode.getTable());

            int playPoints = hope.makePlay(validMove, moves);

            IDDFSNode newNode = new IDDFSNode(validMove,hope.table,playPoints+currentNode.getScore(),currentNode.getNodeID(),currentNode.getLevel()+1);

            if(hope.gameOver())
            {
                lastNode = newNode;
                bestScore = newNode.getScore();
                finished = true;
                break;
            }

            if(depth < maxDepth){
                nodeMap.put(newNode.getNodeID(),newNode);
                DepthFirstLimitedSearch(newNode,depth+1);
            }

            iter = moves.iterator();
        }
    }

    public ArrayList<Point> getIDDFSMoves(){
        ArrayList<Point> returningList = new ArrayList<>();
        IDDFSNode currentNode = lastNode;
        while(currentNode.getParentID() != -1)
        {
            returningList.add(currentNode.getMove());
            currentNode = nodeMap.get(currentNode.getParentID());
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
}
