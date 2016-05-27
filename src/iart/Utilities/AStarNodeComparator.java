package iart.utilities;

import iart.algorithms.AStarSearch;
import iart.nodes.AStarNode;

import java.util.Comparator;

/**
 * Created by NIAEFEUP on 12-04-2016.
 */
public class AStarNodeComparator implements Comparator<AStarNode> {

    @Override
    public int compare(AStarNode x, AStarNode y)
    {
        if(x.score == y.score)
            if((x.realScore) == (y.realScore))
                if(x.level > y.level)
                    return 1;
                else return -1;
            else return (x.realScore < y.realScore) ? 1 : -1;
        else if((x.score) < (y.score))
            return 1;
        else return -1;
    }
}
