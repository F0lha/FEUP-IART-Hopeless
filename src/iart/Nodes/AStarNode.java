package iart.nodes;

import java.util.ArrayList;

import iart.utilities.Point;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarNode extends Node{

    public static int nextNodeID = 0;

    public int nodeID; //
    public int parentNode;
    public int realScore;
    public int level;

    public int score;

    public AStarNode(int parentNode, int level, ArrayList<Integer> table, int score, int realScore, Point move) {
        super(move,table,score);
        this.parentNode = parentNode;
        this.score = score;
        this.realScore = realScore;
        this.level = level;
        nodeID = nextNodeID;
        nextNodeID++;
    }
    static public void resetCounter(){
        nextNodeID = 0;
    }
}
