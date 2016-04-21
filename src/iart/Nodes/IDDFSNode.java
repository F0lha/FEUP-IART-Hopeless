package iart.nodes;

import iart.utilities.Point;

import java.util.ArrayList;

/**
 * Created by NIAEFEUP on 21-04-2016.
 */
public class IDDFSNode extends Node {

    public static int nextNodeID = 0;

    int parentID;
    int nodeID;
    int level;

    ArrayList<Integer> sonsID;

    public IDDFSNode(Point move, ArrayList<Integer> table, int score, int parentID, int level){
        super(move,table,score);
        this.parentID = parentID;
        this.level = level;
        nodeID = nextNodeID;
        nextNodeID++;
        sonsID = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public int getNodeID() {
        return nodeID;
    }

    public int getParentID() {
        return parentID;
    }

    public void addSon(int sonID)
    {
        sonsID.add(sonID);
    }

    public ArrayList<Integer> getSonsID() {
        return sonsID;
    }
}
