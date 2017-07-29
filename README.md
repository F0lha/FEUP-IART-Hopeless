# Hopeless - Board Game 

[Hopeless](https://github.com/F0lha/IART/tree/master/src/iart) is a board game based on [this one](http://greenfelt.net/hopeless).
It was developed for the AI curricular unit. The aim of our project is to give all the necessary moves to get the maximum score possible to the board given. To calculate them we used various search algorithms, such as A*. It is also possible to compare the algorithms in terms of prossesing time, memory used and score so you can evaluate which is best. We made an easy to use interface so it is simple for you play and see the moves made by the different algoritms.
To play it all you have to do is import this project to an IDE that runs Java (for example, IntelliJ), press run and follow the instructions given.
Or run the hopeless jar file, if you're in windows just execute this command-line: 
      
    java -jar hopeless.jar

# Table of Contents
1. [Game Overview](#Game)
2. [How to Use](#HowtoUse)
3. [Preview](#Preview)
4. [Algorithms Used](#algorithms)
5. [Notes](#Notes)

## <a name="Game"></a>Game Overview

The aim of the game is to clear away as many blocks as possible. 
If a block as one or more neighbor with the same color, by clicking on it they all disappear. The blocks on top of them will fall, like in Tetris game and if any column is cleared, the blocks will shift right. 
The game is over when there are no more moves left.
The score is calculated based in this formula: 

score = (2(n − 1))^2 , em que n = nº of squares 

## <a name="HowtoUse"></a>How to Use

It is easy to use once you click run a window will open.
At your left you will see all the algorithms developed and also an option so you can play the game by yourself not the AI, this is also possible by clicking in the squares directly. 
You can also define the size of the board in the options height and width, reset the current board and generate a new one. 
With the option statistics you generate an excel file that compares algorithms, for more information read [notes](#notes) section.

## <a name="Preview">Preview

Here is an example of the window that pops up.

![alt text](https://github.com/F0lha/IART/blob/master/firstScreen.png "sreen")


## <a name="algorithms">Algorithms Used

* **Depth First Search** ([DFS](https://en.wikipedia.org/wiki/Depth-first_search))

  For this poject this algorithm isn't valid because the results don't have any quality because any move can lead to a final state
  not occuring any backtracking.

* **Breadth First Search** ([BFS](https://en.wikipedia.org/wiki/Breadth-first_search))

  The solution obtained by this algorithm presents a
  (Or the only) smaller set of possible moves to reach the end state.
  Due to the nature of the game, the lowest number of moves does not necessarily reflect
  an optimal solution. In the implementation of this algorithm there is an option that allows us to expand
  all nodes. Through this method *Brute Force* is possible for us to always find the
  maximum score for the game. However due to the great computational power and memory needed for large boards is
  unthinkable to use any of these methods.

* **Iterative Deepening DepthFirst Search** ([IDDFS](https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search))
  
  This algorithm is equivalent to Breadth First Search. The difference between
  them is that this exchange memory resources for computational time.

* [**Greedy Search**](https://en.wikipedia.org/wiki/Greedy_algorithm)

  In this work, this algorithm is not considered useful. Because of the
  polynomial nature of the game score sometimes it is necessary to make a "bad"
  move, that is, a move with low score to then compensate it with
  a better move.

* **MaxMin**

  The *maxmin* algorithm is an algorithm invented by us, using search heuristics.
  It was possible to create this algorithm due to a property of the Hopeless boards. This property allows it to be possible to calculate the minimum points possible to make with a board. This happens because all the moves possible to do in a given board can be performed. This allows us to assign a minimum score to each board and the heuristic value that will serve to
  compare the prosperity of each board.
  With this, the maxmin algorithm expands the play whose resulting board lets us make the maximum of minimal scores. The algorithm ends when it reaches a final state.
  The need to develop this algorithm stemmed from the need to create
  an algorithm that scales linearly with the number of moves and at the same
  time maintains a good quality of the final score. For this reason this
  the algorithm is *recommended for large boards*.

* [**A***](https://en.wikipedia.org/wiki/A*_search_algorithm)

  In order to use this algorithm in this game it requires some modifications to the original algorithm.
  
  **Minimizing vs Maximization**
  
    The description says that A* "Has the objective to find the path of least cost."
    However, when it comes to maximizing this is not the objective. In maximizing the most promising node is the one with the value of f\*(n) greater than all others.
    Now h\*(n) will estimate the reward to reach the final state instead of the previous cost.
    
  
  **Heuristic function**
  
    The definition of the admissibility of a heuristic also has to be reviewed because now it is a maximization problem.
    The necessary adjustment to the definition is:
    **The value of h\*(n) will always be greater than or equal to the reward to
    the final optimum state from n.**
    In other words will have to always be higher than the maximum score that can
    be obtained from that node.
    The only way for admissibility be ensured is to create a function that
    calculates the score if all points that are at stake were positioned in an optimal way.
    such equation is: 
      h\*(n) = 0
    foreach different_color as color
      h\*(n) += Points(numberOfSquares(color))

    This score will constantly overestimate. The problem is that it is not possible to approximate this value to the actual score without simulating moves.
    However, simulating moves is exactly the objective of the algorithm.
    
    **Final State**
    
    When evaluating a final state, the value of h\*(n) can result in a higher value than 0. This is a result of a possible overestimation of the heuristic value.
      
      Therefore, while the f(t) upper bounds the optimal solution, g(t) may not, since
      h(t) may be larger than 0. Thus, unlike A* for MIN problems, expanding a goal
      with A* in MAX problems does not guarantee that the optimal solution has been
      found.”
    

    Because h\*(n) is, in pratice, a reward to get to the final state it makes no sense for the value to be different from 0. 
    However it is possible that g(n) is not optimal, then the solution found may not be optimum even if h(n) = 0.
    However we can use h(n) = 0. If we do we will be making a search for more nodes since we are minimizing the value of f\*(n)
    thereby allowing nodes in OPEN to be expanded that with the value of h\*(n)> 0 wouldn't be.
    In the final work this option is left to the user.

* [**Iterative Deepening A***](https://en.wikipedia.org/wiki/Iterative_deepening_A*)

  The implementation of the algorithm Iterative Deepening A* doesn't make sense when we are talking about
  maximization. Setting a limit for f* isn't beneficial since we are looking to maximize it. 
  For this reason the algorithm has not been implemented.


## <a name="Notes">Notes
All boards are randomly generated based in the size and difficulty (number of colors) selected.

  **Generated Statistics**

  The multiple algorithms have different run times and get different results.
  We implemented a function that generates a certain number of different boards (for
  user input). For these boards we run all algorithms and register the
  scores and the execution time of each algorithm, and calculate the average of these
  values for each algorithm. From these values we calculate the efficiency of each
  of these algorithms.
  As previously mentioned, all of this is automatically generated in
  a Microsoft Excel file named "statistics.xlsx" after you click in the statistics button.

In case you have any doudt you can read our [report](https://github.com/F0lha/IART/blob/master/FinalReport.pdf).
