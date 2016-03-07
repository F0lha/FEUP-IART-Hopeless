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
        Hopeless hope = new Hopeless(5,5,4);

        Hopeless temp = new Hopeless(5,5,4);

        temp.table = new ArrayList<>(hope.table);

        for(int i= 0;i < hope.row;i++)
        {
            for(int j = 0; j < hope.col;j++)
            {
                System.out.print(hope.table.get(i*hope.col+j)+"|");
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
