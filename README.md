#Hopeless - Board Game 

[Hopeless](https://github.com/F0lha/IART/tree/master/src/iart) is a board game based on [this one](http://greenfelt.net/hopeless).
It was developed for the AI curricular unit. The aim of our project is to give all the necessary moves to get the maximum score possible to the board given. To calculate them we used various search algorithms, such as A*. It is also possible to compare the algorithms in terms of prossesing time, memorie used and score so you can avaluate which is best. We made an easy to use interface so it helps you play and see the moves made by the different algoritms.
To play it all you have to do import this project to an IDE that runs Java (for example, IntelliJ), just press run and follow the instructions given.

# Table of Contents
1. [Game Overview](#Game)
2. [How to Use](#HowtoUse)
3. [Preview](#Preview)
4. [Algorithms Used](#algorithms)
5. [Notes](#Notes)

##<a name="Game"></a>Game Overview

The aim of the game is to clear away as many blocks as possible. 
If a block as one or more neighbor with the same color, by clicking on it they all disapper. The blocks on top of them will fall, like in Tetris game and if any column is cleared, the blocks will shift right. 
The game is over when there are no more moves left.
The score is calculated based in this formula: 

score = (2(n − 1))^2 , em que n = nº of squares 

##<a name="HowtoUse"></a>How to Use

It is easy to use once you click run a window will open, in your left you can see all the algorithms developed and also an option so you can play the game by yourself not the AI (User - first option).
You can also define the size of the board in the options height and width, reset the current board and also generate a new one. 
With the option statistics you generate an excel file that compares algorithms.

##<a name="Preview">Preview
Here is an example of the [window]() that pops up.

##<a name="algorithms">Algorithms Used
* Depth First Search ([DFS](https://en.wikipedia.org/wiki/Depth-first_search))
For this poject this algorithm isn't valid because the results don't have any quality because any move can lead to a final state
not occuring any backtracking.

* Breadth First Search ([BFS](https://en.wikipedia.org/wiki/Breadth-first_search))

  The solution obtained by this algorithm presents a
  (Or the only) smaller set of possible moves to reach the end state.
  Due to the nature of the game, the lowest number of moves does not necessarily reflect
  an optimal solution. In the implementation of this algorithm there is an option that allows us to expand
  all nodes. Through this method *Brute Force* is possible for us to always find the
  maximum score for the game. However due to the great computational power and memory needed for large trays is
  unthinkable to use any of these methods.

* Iterative Deepening DepthFirst Search ([IDDFS](https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search))
  
  This algorithm is equivalent to Breadth First Search. The difference between
  them is that this exchange memory resources for computational time.

* [Greedy Search](https://en.wikipedia.org/wiki/Greedy_algorithm)

  In this work, this algorithm is not considered useful. Because of
  polynomial nature of the game score sometimes it is necessary to make a "bad"
  move, that is, a move with low score to then compensate it with
  a better move.

* MaxMin

  The *maxmin* algorithm is an algorithm invented by us, using search heuristics.
  It was possible to create this algorithm due to a property of the Hopeless boards. This property allows it to be possible to calculate the minimum points possible to make with a board. This is happens because all the moves possible to do in a given board can be performed. This allows us to assign a minimum score to each board and the heuristic value that will serve to
  compare the prosperity of each board.
  With this, the maxmin algorithm expands the play whose resulting board lets us make the maximum of minimal scores. The algorithm ends when it reaches a final state.
  The need to develop this algorithm stemmed from the need to create
  an algorithm that scales linearly with the number of moves and at the same
  time maintains a good quality of the final score. For this reason this
  the algorithm is *recommended for large boards*.

* [A*](https://en.wikipedia.org/wiki/A*_search_algorithm)

  In order to use this algorithm in this game it requires some modifications to the original algorithm.

##<a name="Notes">
All boards are randomly generated based in the size and difficulty (number of colors used) selected.
