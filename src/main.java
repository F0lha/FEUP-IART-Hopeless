import javax.swing.text.html.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class main {

    public static void main(String[] args)
    {
        Hopeless hopeAStar = new Hopeless(4,4,4);

        Hopeless hopeDFS = new Hopeless(4,4,4);

        hopeDFS.table = new ArrayList<>(hopeAStar.table);

        for(int i= 0;i < hopeAStar.row;i++)
        {
            for(int j = 0; j < hopeAStar.col;j++)
            {
                System.out.print(hopeAStar.table.get(i*hopeAStar.col+j)+"|");
            }
            System.out.println();
        }
/*
        int points = 0;

        Random rand = new Random();

       while(!hope.gameOver())
       {
           List<Point> list = hope.getAllValidMoves();

           points += hope.makePlay(list.get(rand.nextInt(list.size())));

           for(int i = 0; i < hope.table.length;i++){
               for(int j = 0; j < hope.table[i].length;j++)
                   System.out.print(hope.table[i][j]+"|");
               System.out.println();
           }
           System.out.println("Points:" + points);
       }

*/
        long startTime = System.currentTimeMillis();

        //DepthFirstSearch rip = new DepthFirstSearch(hope);


        //A STAR

        runAstar(hopeAStar);

        dfs(hopeDFS);
    }


    static void runAstar(Hopeless hope){

        long startTime = System.currentTimeMillis();

        //DepthFirstSearch rip = new DepthFirstSearch(hope);


        //A STAR
        AStarSearch rip = new AStarSearch(hope);

        ArrayList<Point> bestMoves = rip.getAStarMoves();

        if(rip.bestScore!= 0)
            for(Point move : bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        for(int i= 0;i < hope.row;i++)
        {
            for(int j = 0; j < hope.col;j++)
            {
                System.out.print(hope.table.get(i*hope.col+j)+"|");
            }
            System.out.println();
        }


        System.out.println("Moves = " + bestMoves);
        System.out.println("Score = " + rip.bestScore);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);

        System.out.println("Is Gameover " + hope.gameOver());
    }

    static void dfs(Hopeless hope){
        long startTime = System.currentTimeMillis();

        DepthFirstSearch rip = new DepthFirstSearch(hope);

        if(rip.bestScore!= 0)
            for(Point move : rip.bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        for(int i= 0;i < hope.row;i++)
        {
            for(int j = 0; j < hope.col;j++)
            {
                System.out.print(hope.table.get(i*hope.col+j)+"|");
            }
            System.out.println();
        }
        for(int i = 0; i < rip.finalResults.size();i++)
        {
            System.out.print(rip.finalResults.get(i)+"/");
        }System.out.println();

        System.out.println("Moves = " + rip.bestMoves);
        System.out.println("Score = " + rip.bestScore);
        System.out.println("Solutions = " + rip.solutions);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);

        System.out.println("Is Gameover " + hope.gameOver());
    }
}
