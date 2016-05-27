package iart.algorithms;

import iart.game.Hopeless;
import iart.utilities.Point;

import java.util.*;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class DepthFirstSearch extends Algorithm implements Runnable{
    public List<Point> moves = new ArrayList<>();


    /**
     * Depth First Search constructor
     * @param hope Hopeless Game Object
     */
    public DepthFirstSearch(Hopeless hope) {
        this.hope = hope;
    }

    /**
     * Runnable Depth First Search Algorithm
     */
    public void run(){

        while(!hope.gameOver())
        {
            Point move = hope.getAllValidMoves().get(0);
            bestScore += hope.makePlay(move,new ArrayList<>());
            moves.add(move);
        }
        finished = true;
    }

    /**
     * Get best DFS plays in order
     * @return list of points
     */
    public List<Point> getDFSBestPlays(){
        return moves;
    }
}


