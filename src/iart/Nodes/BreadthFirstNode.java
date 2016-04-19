package iart.Nodes;

import iart.Utilities.Point;

import java.util.ArrayList;

/**
 * Created by up201305337 on 19-04-2016.
 */
public class BreadthFirstNode extends Node {

    ArrayList<Point> listOfMoves;

    public BreadthFirstNode(Point move, ArrayList<Integer> table, int score, ArrayList<Point> listOfMoves){

        super(move,table,score);
        this.listOfMoves = new ArrayList<>(listOfMoves);
        if(!move.equals(new Point(-1,-1)))
            this.listOfMoves.add(move);
    }

    public ArrayList<Point> getListOfMoves() {
        return listOfMoves;
    }
}
