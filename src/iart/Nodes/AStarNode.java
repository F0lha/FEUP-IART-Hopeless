package iart.Nodes;

import java.util.ArrayList;

import iart.Utilities.Point;

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

    public AStarNode(AStarNode node, int levelSum) {
        super(node.move,node.table,node.getScore());
        this.parentNode = node.parentNode;
        this.score = node.score;
        this.realScore = node.realScore;
        this.level = node.level + levelSum;
        this.nodeID = node.nodeID;
    }
}
