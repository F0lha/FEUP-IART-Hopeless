package iart.Nodes;

import iart.Utilities.Point;

import java.util.ArrayList;

/**
 * Created by up201305337 on 18-04-2016.
 */
public class Node {

    Point move;

    int score;

    ArrayList<Integer> table;

    public Node(Point move, ArrayList<Integer> table, int score){
        this.move = move;
        this.table = new ArrayList<>(table);
        this.score = score;
    }

    public Point getMove() {
        return move;
    }

    public ArrayList<Integer> getTable() {
        return table;
    }

    public int getScore() {
        return score;
    }
}
