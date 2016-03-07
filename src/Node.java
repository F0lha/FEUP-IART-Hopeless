import java.util.ArrayList;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class Node implements Comparable<Node>{

    static int nextNodeID = 0;

    int nodeID; //
    int parentNode;
    int realScore;
    Point move;

    int score;

    ArrayList<Integer> table;

    public Node(int parentNode, ArrayList<Integer> table, int score,int realScore, Point move){

        this.parentNode = parentNode;
        this.score = score;
        this.realScore = realScore;
        this.move = move;
        nodeID = nextNodeID;
        nextNodeID++;

        this.table = new ArrayList<>(table);
    }

    public int compareTo(Node node){
        if(score == node.score)
            return (parentNode > node.parentNode) ? 1 : -1;
        else if(score < node.score)
            return 1;
        else return -1;
    }
}
