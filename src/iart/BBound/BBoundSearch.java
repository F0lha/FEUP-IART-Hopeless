package iart.BBound;

import iart.AStar.AStarNode;
import iart.Hopeless;
import iart.Point;

import java.util.*;

/**
 * Created by Pedro Castro on 07/04/2016.
 */
public class BBoundSearch {

    PriorityQueue<BBoundNode> openList = new PriorityQueue<>();

    Map<Integer, BBoundNode> mapNode = new HashMap<>();

    int row,col,difficulty;

    int bestScore;

    public BBoundSearch(Hopeless hope){
        this.row = hope.getRow();
        this.col = hope.getCol();
        this.difficulty = hope.getDifficulty();

        BBoundNode fNode = new BBoundNode(0,0,hope.table,null,-1);
        openList.add(fNode);
        mapNode.put(fNode.nodeID,fNode);

        while(!openList.isEmpty()) {

            BBoundNode headNode = new BBoundNode(openList.poll(),0);

            hope.table = new ArrayList<>(headNode.table);


            if(mapNode.containsKey(headNode.parentNode))
                System.out.println("Last Play : " + (headNode.score - mapNode.get(headNode.parentNode).score) + " at level :" + headNode.level);

            if(hope.gameOver())
                break;

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();

            int bestPlay = 0;

            while (iter.hasNext()) {
                Point validMove = iter.next();
                //resetBoard
                hope.copyTable(headNode.table);

                int points = hope.makePlay(validMove, validMoves);

                if(bestPlay < points)
                    bestPlay = points;

                //new table

                BBoundNode nextNode = new BBoundNode(headNode.level + 1, points + headNode.score, new ArrayList<>(hope.table), validMove,headNode.nodeID);

                openList.add(nextNode);
                mapNode.put(nextNode.nodeID, nextNode);

                iter = validMoves.iterator();
            }

            System.out.println("Best Play : " + bestPlay);
        }
    }

    public ArrayList<Point> getBBoundMoves(){
        ArrayList<Point> returningList = new ArrayList<>();
        BBoundNode currentNode = openList.peek();
        this.bestScore = currentNode.score;
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

    public int getBestScore() {
        return bestScore;
    }
}
