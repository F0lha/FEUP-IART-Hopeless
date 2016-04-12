package iart.BBound;

import iart.Point;

import java.util.ArrayList;


/**
 * Created by Pedro Castro on 07/04/2016.
 */
public class BBoundNode {

    static int nextNodeID = 0;

    int score;
    int level;
    int nodeID;

    Point move;

    int parentNode;


    ArrayList<Integer> table;

    public BBoundNode(int level, int score, ArrayList<Integer> table ,Point move, int parentNode) {

        this.level = level;
        this.score = score;
        this.move = move;
        this.parentNode = parentNode;
        this.nodeID = nextNodeID;
        this.table = table;
        nextNodeID++;
    }

    public BBoundNode(BBoundNode node, int levelSum)
    {
        this.parentNode = node.parentNode;
        this.score = node.score;
        this.move = node.move;
        this.level = node.level + levelSum;
        this.nodeID = node.nodeID;

        this.table = new ArrayList<>(node.table);
    }
}


