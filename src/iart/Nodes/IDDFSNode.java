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

    /**
     * IDDFS  Node constructor.
     * @param move point of play made
     * @param table table of current node
     * @param score score of current node
     * @param parentID id of previous node
     * @param level level of current node
     */
    public IDDFSNode(Point move, ArrayList<Integer> table, int score, int parentID, int level){
        super(move,table,score);
        this.parentID = parentID;
        this.level = level;
        nodeID = nextNodeID;
        nextNodeID++;
        sonsID = new ArrayList<>();
    }

    /**
     * Get Level of node.
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get id of current node
     * @return id of current node
     */
    public int getNodeID() {
        return nodeID;
    }

    /**
     * Get id of parent node
     * @return id of parent node
     */
    public int getParentID() {
        return parentID;
    }

    /**
     * Add son to son list
     * @param sonID son's id
     */
    public void addSon(int sonID)
    {
        sonsID.add(sonID);
    }

    /**
     * Get list of sons
     * @return list of id's
     */
    public ArrayList<Integer> getSonsID() {
        return sonsID;
    }
}
