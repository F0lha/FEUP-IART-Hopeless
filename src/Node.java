import java.util.ArrayList;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class Node {

    static int nextNodeID = 0;

    int nodeID; //
    int parentNode;

    ArrayList<Integer> table;

    public Node(int parentNode, ArrayList<Integer> table){

        this.parentNode = parentNode;
        nodeID = nextNodeID;
        nextNodeID++;

        this.table = new ArrayList<>(table);
    }
}
