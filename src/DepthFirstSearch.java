import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class DepthFirstSearch {
    List<Point> bestMoves;

    Map<ArrayList<Integer>,Integer> hashtable = new Hashtable<>();

    int bestScore = 0;

    int solutions = 0;

    boolean running = true;

    Hopeless tempHope;

    ArrayList<Integer> finalResults = new ArrayList<>();

    int row, col, difficulty;

    public DepthFirstSearch(Hopeless hope){

        this.row = hope.row;
        this.col = hope.col;
        this.difficulty = hope.difficulty;

        tempHope = new Hopeless(row,col,difficulty);

        List<Point> listPoints = new ArrayList<>();

        hashtable.put(hope.table,0);
        dfs(listPoints,0,hope.table);
    }

    void dfs(List<Point> listPoints, int points, ArrayList<Integer> table){
        if(running && (hashtable.get(table) <= points)) {

            tempHope.table = new ArrayList<>(table);

            List<Point> validMoves = tempHope.getAllValidMoves();

            Iterator<Point> iter = validMoves.iterator();

            while (iter.hasNext()) {
                Point validMove = iter.next();

                tempHope.table = new ArrayList<>(table);

                int tempPoints = tempHope.makePlay(validMove, validMoves);

                iter = validMoves.iterator();

                List<Point> tempListPoints = (ArrayList) ((ArrayList) listPoints).clone();

                tempListPoints.add(validMove);

                Integer newPoints = Integer.valueOf(points) + tempPoints;

                if(hashtable.containsKey(tempHope.table)) {
                    if(hashtable.get(tempHope.table) > newPoints) {
                        //System.out.println("delete");
                        continue;
                    }
                    else hashtable.put(tempHope.table,newPoints);
                    //System.out.println("not delete");
                }
                else hashtable.put(tempHope.table,newPoints);

                if (tempHope.gameOver()) {
                    solutions++;


                    finalResults.add(Integer.valueOf(tempPoints + points));

                    if (bestScore < (tempPoints + points)) {
                        bestMoves = (ArrayList) ((ArrayList) tempListPoints).clone();
                        bestScore = Integer.valueOf(tempPoints + points);
                    }

                    if (solutions >= row*col*10000)
                        running = false;

                    continue;
                }
                dfs(tempListPoints, newPoints, tempHope.table);

            }
        }
    }
}


