package iart.nodes;

import java.util.ArrayList;

import iart.utilities.Point;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarNode extends Node{

    static int nextNodeID = 0;

    public int nodeID;
    public int parentNode;
    public int realScore;
    public int level;

    public int score;

    /**
     * A* Node constructor.
     * @param parentNode id of previous node
     * @param level level of node
     * @param table table of current node
     * @param score score (with heuristic) of current node
     * @param realScore score of node
     * @param move point of play made
     */
    public AStarNode(int parentNode, int level, ArrayList<Integer> table, int score, int realScore, Point move) {
        super(move,table,score);
        this.parentNode = parentNode;
        this.score = score;
        this.realScore = realScore;
        this.level = level;
        nodeID = nextNodeID;
        nextNodeID++;
    }

    /**
     * Reset nextID counter.
     */
    static public void resetCounter(){
        nextNodeID = 0;
    }
}
