import java.util.*;

/**
 * Created by Pedro Castro on 05/03/2016.
 */
public class AStarSearch {

    PriorityQueue<Node> openList = new PriorityQueue<>();
    Map<Integer,Node> mapNode = new HashMap<>();

    int row,col,difficulty;

    int bestScore;

    public AStarSearch(Hopeless hope){

        this.row = hope.row;
        this.col = hope.col;
        this.difficulty = hope.difficulty;

        Node fNode = new Node(-1,0,hope.table,0,0,null);
        openList.add(fNode);
        mapNode.put(fNode.nodeID,fNode);

        while(!openList.isEmpty()) {
            Node headNode = new Node(openList.poll(),0);

            System.out.println("Node Level : " + headNode.level);
            System.out.println("Score : " + headNode.score);

            hope.table = new ArrayList<>(headNode.table);

            if(hope.gameOver())
                break;

            ArrayList<Point> validMoves = hope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();

            while (iter.hasNext()) {
                Point validMove = iter.next();
                //resetBoard
                hope.copyTable(headNode.table);

                int tempPoints = hope.makePlay(validMove, validMoves);

                int value = heuristicF(tempPoints + headNode.realScore, hope);

                //System.out.println("Value = " + value);

                Node nextNode = new Node(headNode.nodeID, headNode.level + 1, new ArrayList<>(hope.table), value, tempPoints + headNode.realScore, validMove);

                openList.add(nextNode);
                mapNode.put(nextNode.nodeID, nextNode);

                iter = validMoves.iterator();
            }
        }
    }

    ArrayList<Point> getAStarMoves(){
        ArrayList<Point> returningList = new ArrayList<>();
        Node currentNode = openList.peek();
        this.bestScore = currentNode.realScore;
        while(currentNode.parentNode != -1)
        {
            returningList.add(currentNode.move);
            currentNode = mapNode.get(currentNode.parentNode);
        }
        return reverse(returningList);
    }

    //reversing list
    public static <T> ArrayList<T> reverse(ArrayList<T> list) {
        int length = list.size();
        ArrayList<T> result = new ArrayList<T>(length);

        for (int i = length - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }


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

        int limit = HTable.getNextColor();


        int alone = 0;

        for(int i = 1; i < limit;i++){
            int removals = Collections.frequency(HTable.getTableRegions(), i);
            if(removals == 1)
                weight++;
            else if(removals != 0) {
                weight += Hopeless.getPoints(removals);
            }
        }

        return weight;
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
