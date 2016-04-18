package iart.Greedy;

import iart.Point;

import java.util.ArrayList;

/**
 * Created by up201305337 on 18-04-2016.
 */
public class GreedyNode {

    public Point move;
    public ArrayList<Integer> table;

    public GreedyNode(Point move, ArrayList<Integer> table){
        this.move = move;
        this.table = new ArrayList<>(table);
    }
}
