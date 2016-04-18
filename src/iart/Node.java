package iart;

import java.util.ArrayList;

/**
 * Created by up201305337 on 18-04-2016.
 */
public class Node {

    Point move;

    ArrayList<Integer> table;

    public Node(Point move, ArrayList<Integer> table){
        this.move = move;
        this.table = new ArrayList<>(table);
    }

    public Point getMove() {
        return move;
    }

    public ArrayList<Integer> getTable() {
        return table;
    }

}
