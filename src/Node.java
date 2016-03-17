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
    int level;

    int score;

    ArrayList<Integer> table;

    public Node(int parentNode, int level, ArrayList<Integer> table, int score,int realScore, Point move){

        this.parentNode = parentNode;
        this.score = score;
        this.realScore = realScore;
        this.move = move;
        this.level = level;
        nodeID = nextNodeID;
        nextNodeID++;

        this.table = new ArrayList<>(table);
    }

    public Node(Node node, int levelSum)
    {
        this.parentNode = node.parentNode;
        this.score = node.score;
        this.realScore = node.realScore;
        this.move = node.move;
        this.level = node.level + levelSum;
        this.nodeID = node.nodeID;

        this.table = new ArrayList<>(node.table);
    }

    public int compareTo(Node node){
        if(score == node.score)
            return (level > node.level) ? 1 : -1;
        else if(score < node.score)
            return 1;
        else return -1;
    }
}
