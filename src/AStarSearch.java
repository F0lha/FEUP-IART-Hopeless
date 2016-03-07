import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarSearch {

    PriorityQueue<Node> AStarQueue;


    public int heuristicF(int points, Hopeless hope){
        int weight = points;

        HeuristicTable HTable = new HeuristicTable(hope.row * hope.col);

        for(int i= 0;i < hope.row;i++)
        {
            for(int j = 0; j < hope.col;j++) {

                int pointColour = hope.getColor(new Point(i,j));

                if(pointColour == 0)
                {
                    HTable.getTableRegions().set(i*hope.col+j,0);
                    HTable.getTableVisited().set(i*hope.col+j,1);
                }
                else{
                    recursiveRegions(i,j,hope,HTable,HTable.getNextColor());
                    HTable.addNextColor();
                }

            }

        }
        return 0;
    }

    void recursiveRegions(int i,int j,Hopeless hope,HeuristicTable HTable,int HTableColor){
        //if not visited
        if(HTable.getTableVisited().get(i*hope.col+j) != 1) {

            HTable.getTableRegions().set(i * hope.col + j, HTableColor);
            HTable.getTableVisited().set(i * hope.col + j, 1);

            //surrounding

            if (i > 0 && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i - 1, j))))
                recursiveRegions(i - 1, j, hope, HTable, HTableColor);
            if (j > 0 && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i, j - 1))))
                recursiveRegions(i, j - 1, hope, HTable, HTableColor);
            if (i < hope.row && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i + 1, j))))
                recursiveRegions(i + 1, j, hope, HTable, HTableColor);
            if (j < hope.col && (hope.getColor(new Point(i, j)) == hope.getColor(new Point(i, j + 1))))
                recursiveRegions(i, j + 1, hope, HTable, HTableColor);
        }
    }
}
