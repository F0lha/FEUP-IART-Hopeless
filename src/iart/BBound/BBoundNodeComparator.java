package iart.BBound;

import java.util.Comparator;

/**
 * Created by NIAEFEUP on 12-04-2016.
 */
public class BBoundNodeComparator implements Comparator<BBoundNode>{
    @Override
    public int compare(BBoundNode x, BBoundNode y)
    {
        if(x.score == y.score)
            return (x.level > y.level) ? 1 : -1;
        else if((x.score) < (y.score))
            return 1;
        else return -1;
    }
}
