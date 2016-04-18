package iart.AStar;

import java.util.ArrayList;

import iart.Node;
import iart.Point;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarNode extends Node{

    static int nextNodeID = 0;

    int nodeID; //
    int parentNode;
    int realScore;
    Point move;
    int level;

    int score;

    ArrayList<Integer> table;

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
