package iart.algorithms;

import iart.game.Hopeless;
import iart.nodes.Node;
import iart.utilities.Point;

import java.util.*;

/**
 * Created by Pedro Castro on 07/04/2016.
 */
public class Greedy extends Algorithm implements Runnable{

    ArrayList<Node> listOfPlays = new ArrayList<>();

    public Greedy(Hopeless hope) {

        this.hope = hope;
    }
    public void run(){

        ArrayList<Integer> bestTable = new ArrayList<>(hope.table);

        while(!hope.gameOver()) {


            ArrayList<Integer> currentTable = new ArrayList<>(hope.table);

//            if(mapNode.containsKey(headNode.parentNode))
//                System.out.println("Last Play : " + (headNode.score - mapNode.get(headNode.parentNode).score) + " at level :" + headNode.level);

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();



            int bestPlay = 0;
            Point bestMove = new Point(-1,-1);


            while (iter.hasNext()) {
                Point validMove = iter.next();
                //resetBoard
                hope.copyTable(currentTable);


                int playPoints = hope.makePlay(validMove, validMoves);

                if(bestPlay < playPoints) {
                    bestPlay = playPoints;
                    bestTable = new ArrayList<>(hope.table);
                    bestMove = validMove;
                }

                //reset table

                hope.table = new ArrayList<>(currentTable);

                iter = validMoves.iterator();
            }
            bestScore += bestPlay;
            listOfPlays.add(new Node(bestMove,bestTable,bestScore));

            hope.table = bestTable;
//            System.out.println("Best Play : " + bestPlay);
        }
        finished = true;
    }

    public ArrayList<Point> getGreedyPlays(){
        ArrayList<Point> pointArray = new ArrayList<>();
        for(int i = 0; i < listOfPlays.size();i++) {
            pointArray.add(listOfPlays.get(i).getMove());
        }
        return pointArray;
    }
}
