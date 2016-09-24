package iart.nodes;

import iart.utilities.Point;

import java.util.ArrayList;

/**
 * Created by up201305337 on 18-04-2016.
 */
public class Node {

    Point move;

    int score;

    ArrayList<Integer> table;

    /**
     * Node constructor.
     * @param move point of play made
     * @param table table of current node
     * @param score score of current node
     */
    public Node(Point move, ArrayList<Integer> table, int score){
        this.move = move;
        this.table = new ArrayList<>(table);
        this.score = score;
    }

    /**
     * Return last move made.
     * @return point of move
     */
    public Point getMove() {
        return move;
    }

    /**
     * Get table of current node
     * @return table of current node
     */
    public ArrayList<Integer> getTable() {
        return table;
    }

    /**
     * Get current score.
     * @return score
     */
    public int getScore() {
        return score;
    }
}
