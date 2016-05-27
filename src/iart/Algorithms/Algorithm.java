package iart.algorithms;

import iart.game.Hopeless;


public class Algorithm {
    int bestScore = 0;

    Hopeless hope;

    boolean finished = false;

    /**
     * Returns state of the running algortihm
     * @return state
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Returns Best Score from the Best Moves calculated
     * @return Best Score
     */
    public int getBestScore(){
        return bestScore;
    }
}
