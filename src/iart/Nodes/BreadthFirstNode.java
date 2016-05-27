package iart.nodes;

import iart.utilities.Point;

import java.util.ArrayList;

/**
 * Created by up201305337 on 19-04-2016.
 */
public class BreadthFirstNode extends Node {

    ArrayList<Point> listOfMoves;

    /**
     * Breadth First Search Node constructor.
     * @param move point of play made
     * @param table table of current node
     * @param score score of current node
     * @param listOfMoves list of moves made to get to current node
     */
    public BreadthFirstNode(Point move, ArrayList<Integer> table, int score, ArrayList<Point> listOfMoves){

        super(move,table,score);
        this.listOfMoves = new ArrayList<>(listOfMoves);
        if(!move.equals(new Point(-1,-1)))
            this.listOfMoves.add(move);
    }

    /**
     * Get list of moves until current node
     * @return list of points
     */
    public ArrayList<Point> getListOfMoves() {
        return listOfMoves;
    }
}
