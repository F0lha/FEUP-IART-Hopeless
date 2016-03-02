import java.util.List;
import java.util.Random;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class main {

    public static void main(String[] args)
    {
        Hopeless hope = new Hopeless(6,6,3);

        for(int[] row : hope.table){
            for(int col : row)
                System.out.print(col + "|");
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
        DepthFirstSearch rip = new DepthFirstSearch(hope);

        /*for(int i = 0; i < rip.finalResults.size();i++)
        {
            System.out.print(rip.finalResults.get(i)+"/");
        }*/

        System.out.println("Moves = " + rip.bestMoves);
        System.out.println("Score = " + rip.bestScore);
        System.out.println("Solutions = " + rip.solutions);

    }
}
