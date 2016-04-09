package iart;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Integer;

/**
 * Created by Pedro Castro on 02/03/2016.
 */

//TODO mems troca todos os rows com os cols
public class Hopeless {

    public ArrayList<Integer> table;

    int row, col;

    int difficulty;

    public Hopeless(int row, int col, int diff){
        this.row = row;
        this.col = col;
        this.difficulty = diff;

        table = new ArrayList<>();

        initializeTable();

    }

    public int getColor(Point point){
        if((point.getRow()>=0 && point.getRow()<row)&&(point.getCol()>=0 && point.getCol()<col))
            return table.get(point.getRow()*this.col+point.getCol());
        else return -1;
    }

    void initializeTable(){
        Random rand = new Random();

        for(int i = 0;i < row;i++)
        {
            for(int j = 0; j < col;j++)
            {
                table.add(rand.nextInt(difficulty) + 1);
            }
        }
    }

    boolean validMove(Point point)
    {
        if (point.getRow() >= row || point.getCol() >= col) {
            //System.out.println("out of bounds");
            return false;
        }
        else if(getColor(point) == 0){
            //System.out.println("empty");
            return false;
        }
        else if((point.getRow()>0 && (getColor(new Point(point.getRow()-1,point.getCol())) == getColor(point))) ||
                (point.getCol()>0 && (getColor(new Point(point.getRow(),point.getCol()-1)) == getColor(point))) ||
                (point.getRow()<(row-1) && (getColor(new Point(point.getRow()+1,point.getCol())) == getColor(point))) ||
                (point.getCol()<(col-1) && (getColor(new Point(point.getRow(),point.getCol()+1)) == getColor(point)))
                )
            return true;
        else
        {
            //System.out.println("isolated" + point + " colour = " + getColor(point));

            //System.out.println("Cima = " + getColor(new iart.Point(point.getRow()-1,point.getCol())));
            //System.out.println("Baixo = " + getColor(new iart.Point(point.getRow()+1,point.getCol())));
            //System.out.println("Esquerda = " + getColor(new iart.Point(point.getRow(),point.getCol()-1)));
            //System.out.println("Direita = " + getColor(new iart.Point(point.getRow(),point.getCol()+1)));
            return false;
        }
    }

    public ArrayList<Point> getAllValidMoves(){

        ArrayList<Point> listOfValidMoves = new ArrayList<>();

        for(int i = 0;i < row;i++) {
            for (int j = 0; j < col; j++) {
                if(validMove(new Point(i,j)))
                    listOfValidMoves.add(new Point(i,j));
            }
        }
        return listOfValidMoves;
    }

    public int makePlay(Point point, ArrayList<Point> validMoves){

        if(!validMove(point)){
            return -1;
        }

        int colour = getColor(point);

        // removes all Points recursively
        int removals = removePoint(point,colour,validMoves);

        //colapse the table

        colapseBoardVertically();
        colapseBoardHorizonatlly();

        double points = getPoints(removals);

        return (int)points;
    }

    public static double getPoints(int removals){
        return Math.pow((double)((removals-1)*2),2.0);
    }

    int removePoint(Point point, int colour,ArrayList<Point> validMoves)
    {
        Point up,down,left,right;

        //delete color

        int index = validMoves.indexOf(point);

        //this.print();
        //System.out.println("Index : " +index + "//iart.Point " + point.toString() +"// Colour : " + getColor(point));

        table.set((point.getRow()*this.col)+point.getCol(),0);

        if(index != -1) {
            //System.out.println("Apagou jogada desnecessaria");
            validMoves.remove(index);
        }

        //accumulator
        int acc = 1;

        if(point.getRow()<row-1)
            up = new Point(point.getRow()+1,point.getCol());
        else up = null;

        if (up != null && (colour == getColor(up)))
            acc += this.removePoint(up,colour,validMoves);

        if(point.getRow()>0)
            down = new Point(point.getRow()-1,point.getCol());
        else down = null;

        if (down != null &&(colour == getColor(down)))
            acc += this.removePoint(down,colour,validMoves);

        if(point.getCol()<col-1)
            left = new Point(point.getRow(),point.getCol()+1);
        else left = null;

        if (left != null && (colour == getColor(left)))
            acc += this.removePoint(left,colour,validMoves);

        if(point.getCol()>0)
            right = new Point(point.getRow(),point.getCol()-1);
        else right = null;

        if (right != null && (colour == getColor(right)))
            acc += this.removePoint(right,colour,validMoves);

        return acc;
    }

    void colapseBoardVertically(){
        for(int i = row-1; i > 0;i--)
        {
            for(int j = 0; j< col ;j++)
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

                    table.set((i*this.col)+j,table.get(upperValue*this.col+j));
                    table.set((upperValue*this.col)+j,0);
                }
            }
        }
    }

    boolean checkIfColumnIsEmpty(int col){
        for(int row = 0;row < this.row;row++)
        {
                if(table.get(row*this.col+col) != 0)
                    return false;
        }
        return true;
    }

    void colapseBoardHorizonatlly(){
        for(int i = 0; i < col-1;i++)
        {
            if(checkIfColumnIsEmpty(i))
            {
                int left = i+1;
                while(left < col-1)
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
        for(int i = 0; i < row;i++)
        {
            int temp = table.get(i*this.col+col1);
            table.set((i*this.col)+col1,table.get(i*this.col+col2));
            table.set((i*this.col)+col2,temp);
        }
    }

    public boolean gameOver(){
        return getAllValidMoves().isEmpty();
    }

    public void print(){
        for(int i= 0;i < row;i++)
        {
            for(int j = 0; j < col;j++)
            {
                System.out.print(table.get(i*col+j)+"|");
            }
            System.out.println();
        }
    }

    public void copyTable(ArrayList<Integer> newTable){
        this.table = new ArrayList<>(newTable);
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getDifficulty(){
        return difficulty;
    }
}


