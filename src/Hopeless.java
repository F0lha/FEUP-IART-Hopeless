import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Integer;

/**
 * Created by Pedro Castro on 02/03/2016.
 */

//TODO mems troca todos os rows com os cols
public class Hopeless {

    public int[][] table;
    int difficulty;

    public Hopeless(int row, int col, int diff){
        table = new int[row][col];
        difficulty = diff;

        initializeTable();

    }

    int getColor(Point point){
        if((point.getRow()>=0 && point.getRow()<table.length)&&(point.getCol()>=0 && point.getCol()<table[0].length))
            return table[point.getRow()][point.getCol()];
        else return -1;
    }

    void initializeTable(){
        Random rand = new Random();

        for(int i = 0;i < table.length;i++)
        {
            for(int j = 0; j < table[i].length;j++)
            {
                table[i][j] = rand.nextInt(difficulty) + 1;
            }
        }
    }

    boolean validMove(Point point)
    {
        if (point.getRow() >= table.length || point.getCol() >= table[0].length)
            return false;
        else if(getColor(point) == 0)
            return false;

        else if(
                (point.getRow()>0 && (table[point.getRow()-1][point.getCol()] == table[point.getRow()][point.getCol()])) ||
                (point.getCol()>0 && (table[point.getRow()][point.getCol()-1] == table[point.getRow()][point.getCol()])) ||
                (point.getRow()<(table.length-2) && (table[point.getRow()+1][point.getCol()] == table[point.getRow()][point.getCol()])) ||
                (point.getCol()<(table[0].length-2) && (table[point.getRow()][point.getCol()+1] == table[point.getRow()][point.getCol()]))
                )
            return true;
        else return false;
    }

    List<Point> getAllValidMoves(){

        ArrayList<Point> listOfValidMoves = new ArrayList<>();

        for(int i = 0;i < table.length;i++) {
            for (int j = 0; j < table[i].length; j++) {

                if(validMove(new Point(i,j)))
                    listOfValidMoves.add(new Point(i,j));
            }
        }
        return listOfValidMoves;
    }

    int makePlay(Point point){

        if(!validMove(point)){
            System.out.println("yo"+ point);
            return -1;
        }

        int colour = getColor(point);

        // removes all Points recursively
        int removals = removePoint(point,colour);

        //colapse the table

        colapseBoardVertically();
        colapseBoardHorizonatlly();

        double points = Math.pow((double)((removals-1)*2),2.0);

        return (int)points;
    }

    int removePoint(Point point, int colour)
    {
        Point up,down,left,right;

        //delete color

        table[point.getRow()][point.getCol()] = 0;

        //accumulator
        int acc = 1;

        if(point.getRow()<table.length-1)
            up = new Point(point.getRow()+1,point.getCol());
        else up = null;

        if (up != null && (colour == getColor(up)))
            acc += removePoint(up,colour);

        if(point.getRow()>0)
            down = new Point(point.getRow()-1,point.getCol());
        else down = null;

        if (down != null &&(colour == getColor(down)))
            acc += removePoint(down,colour);

        if(point.getCol()<table[0].length-1)
            left = new Point(point.getRow(),point.getCol()+1);
        else left = null;

        if (left != null && (colour == getColor(left)))
            acc += removePoint(left,colour);

        if(point.getCol()>0)
            right = new Point(point.getRow(),point.getCol()-1);
        else right = null;

        if (right != null && (colour == getColor(right)))
            acc += removePoint(right,colour);
        return acc;
    }

    void colapseBoardVertically(){
        for(int i = table.length-1; i > 0;i--)
        {
            for(int j = 0; j< table[i].length ;j++)
            {
                if(getColor(new Point(i,j)) == 0)
                {
                    int upperValue= i-1;

                    while(upperValue>0)
                    {
                        if(getColor(new Point(upperValue,j)) != 0)
                            break;
                        else upperValue--;
                    }

                    table[i][j] = table[upperValue][j];
                    table[upperValue][j] = 0;
                }
            }
        }
    }

    boolean checkIfColumnIsEmpty(int col){
        for(int row = 0;row < table.length;row++)
        {
                if(table[row][col] != 0)
                    return false;
        }
        return true;
    }

    void colapseBoardHorizonatlly(){
        for(int i = 0; i < table[0].length-1;i++)
        {
            if(checkIfColumnIsEmpty(i))
            {
                int left = i+1;
                while(left < table[0].length-1)
                {
                    if(!checkIfColumnIsEmpty(left))
                        break;
                    else left++;
                }
                switchColumn(left,i);
            }
        }
    }

    void switchColumn(int col1, int col2){
        for(int i = 0; i < table.length;i++)
        {
            int temp = table[i][col1];
            table[i][col1] = table[i][col2];
            table[i][col2] = temp;
        }
    }

    boolean gameOver(){
        return getAllValidMoves().isEmpty();
    }
}


