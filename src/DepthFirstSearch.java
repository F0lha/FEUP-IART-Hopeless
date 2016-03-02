import java.util.*;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class DepthFirstSearch {
    List<Point> bestMoves;

    Hashtable<int[][],Integer> hashtable = new Hashtable<>();

    int bestScore = 0;

    int solutions = 0;

    Hopeless tempHope;

    ArrayList<Integer> finalResults = new ArrayList<Integer>();

    int row, col, difficulty;

    public DepthFirstSearch(Hopeless hope){

        this.row = hope.table.length;
        this.col = hope.table[0].length;
        this.difficulty = hope.difficulty;

        tempHope = new Hopeless(row,col,difficulty);

        List<Point> listPoints = new ArrayList<Point>();

        dfs(listPoints,0,hope.table);
    }

    void dfs(List<Point> listPoints,int points, int[][] table){


        tempHope.table =  deepCopyIntMatrix(table);

        List<Point> validMoves = tempHope.getAllValidMoves();

        for(Point validMove: validMoves)
        {
            //System.out.println("I = " + i);

            tempHope.table =  deepCopyIntMatrix(table);




            int tempPoints = tempHope.makePlay(validMove);

            if(hashtable.contains(tempHope.table)) {
                System.out.println("fuck");
                continue;
            }
            else hashtable.put(tempHope.table,tempPoints);

            List<Point> tempListPoints = (ArrayList)((ArrayList) listPoints).clone();

            tempListPoints.add(validMove);

            Integer newPoints = Integer.valueOf(points)+tempPoints;

            if(tempHope.gameOver())
            {
                solutions++;

                finalResults.add(Integer.valueOf(tempPoints+points));

                if(bestScore < (tempPoints+points))
                {
                    bestMoves = (ArrayList)((ArrayList) tempListPoints).clone();
                    bestScore = Integer.valueOf(tempPoints+points);
                }

                continue;
            }
            dfs(tempListPoints,newPoints,tempHope.table);

        }
    }
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
}


