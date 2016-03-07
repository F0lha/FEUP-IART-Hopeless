import java.util.ArrayList;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class Node implements Comparable{

    static int nextNodeID = 0;

    int nodeID; //
    int parentNode;

    int score;

    ArrayList<Integer> table;

    public Node(int parentNode, ArrayList<Integer> table, int score){

        this.parentNode = parentNode;
        this.score = score;
        nodeID = nextNodeID;
        nextNodeID++;

        this.table = new ArrayList<>(table);
    }

    public int compareTo(Object node){
        return 1;
    }
}
