package iart.Algorithms;

import iart.Game.Hopeless;
import iart.Nodes.Node;
import iart.Utilities.Point;

import java.util.*;

/**
 * Created by Pedro Castro on 07/04/2016.
 */
public class Greedy {

    int row,col,difficulty;

    int bestScore;

    ArrayList<Node> listOfPlays = new ArrayList<>();

    public Greedy(Hopeless hope){
        this.row = hope.getRow();
        this.col = hope.getCol();
        this.difficulty = hope.getDifficulty();

        ArrayList<Integer> bestTable = new ArrayList<>(hope.table);

        int score = 0;

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
            score += bestPlay;
            listOfPlays.add(new Node(bestMove,bestTable,score));

            hope.table = bestTable;
//            System.out.println("Best Play : " + bestPlay);
        }
    }

    public ArrayList<Point> getGreedyPlays(){
        ArrayList<Point> pointArray = new ArrayList<>();
        for(int i = 0; i < listOfPlays.size();i++) {
            pointArray.add(listOfPlays.get(i).getMove());
        }
        return pointArray;
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
