package iart.algorithms;

import iart.game.Hopeless;

/**
 * Created by Pedro Castro on 21/04/2016.
 */
public class Algorithm {
    int bestScore = 0;

    Hopeless hope;

    boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    public int getBestScore(){
        return bestScore;
    }
}
