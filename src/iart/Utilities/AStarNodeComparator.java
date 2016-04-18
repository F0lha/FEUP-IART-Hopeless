package iart.Utilities;

import iart.Nodes.AStarNode;

import java.util.Comparator;

/**
 * Created by NIAEFEUP on 12-04-2016.
 */
public class AStarNodeComparator implements Comparator<AStarNode> {

    @Override
    public int compare(AStarNode x, AStarNode y)
    {
        if(x.score == y.score)
            return (x.level > y.level) ? 1 : -1;
        else if((x.score) < (y.score))
            return 1;
        else return -1;
    }
}
