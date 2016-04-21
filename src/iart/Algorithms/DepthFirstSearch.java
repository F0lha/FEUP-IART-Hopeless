package iart.Algorithms;

import iart.Game.Hopeless;
import iart.Utilities.Point;

import java.util.*;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class DepthFirstSearch extends Algorithm implements Runnable{
    public List<Point> moves = new ArrayList<>();

    public DepthFirstSearch(Hopeless hope) {

        this.hope = hope;
    }
    public void run(){

        while(!hope.gameOver())
        {
            Point move = hope.getAllValidMoves().get(0);
            bestScore += hope.makePlay(move,new ArrayList<>());
            moves.add(move);
        }
        finished = true;
    }
}


