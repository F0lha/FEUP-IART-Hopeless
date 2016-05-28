package iart.game;

import iart.utilities.Point;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Integer;

public class Hopeless {

    private ArrayList<Integer> table;

    private int row, col;

    private int difficulty;

    /**
     * Hopeless game Object constructor
     * @param row number of table rows
     * @param col number of table columns
     * @param diff difficulty
     */
    public Hopeless(int row, int col, int diff){
        this.row = row;
        this.col = col;
        this.difficulty = diff;

        table = new ArrayList<>();

        initializeTable();

    }

    /**
     * Returns colour of a Point in table
     * @param point point of table
     * @return colour of point, -1 if point outside table
     */
    public int getColor(Point point){
        if((point.getRow()>=0 && point.getRow()<row)&&(point.getCol()>=0 && point.getCol()<col))
            return table.get(point.getRow() * col + point.getCol());
        else return -1;
    }

    /**
     * Initializes a random Hopeless table
     */
    public void initializeTable(){
        Random rand = new Random();

        for(int i = 0;i < row;i++)
        {
            for(int j = 0; j < col;j++)
            {
                table.add(rand.nextInt(difficulty) + 1);
            }
        }
    }

    /**
     * Checks if a point is a valid move by checking if its not an empty space or outside the bounds of given the current table
     * @param point point to check
     * @return true if valid, false if not
     */
    boolean validMove(Point point)
    {
        if (point.getRow() >= row || point.getCol() >= col) {
            return false;
        }
        else if(getColor(point) == 0){
            return false;
        }
        else if((point.getRow()>0 && (getColor(new Point(point.getRow()-1,point.getCol())) == getColor(point))) ||
                (point.getCol()>0 && (getColor(new Point(point.getRow(),point.getCol()-1)) == getColor(point))) ||
                (point.getRow()<(row-1) && (getColor(new Point(point.getRow()+1,point.getCol())) == getColor(point))) ||
                (point.getCol()<(col-1) && (getColor(new Point(point.getRow(),point.getCol()+1)) == getColor(point)))
                )
            return true;
        else
            return false;
    }

    /**
     * Returns a list of points where its possible to make a play
     * @return list of points
     */
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

    /**
     * Makes a play and makes changes to table. Removes from validMoves the points that make up the block to where point points.
     * @param point point of the move
     * @param validMoves list of points
     * @return the number of points made with play, -1 if invalid play
     */
    public int makePlay(Point point, ArrayList<Point> validMoves){

        if(!validMove(point)){
            return -1;
        }

        int colour = getColor(point);

        // removes all Points recursively
        int removals = removePoint(point,colour,validMoves);

        //colapse the table

        collapseBoardVertically();
        collapseBoardHorizontally();

        double points = getPoints(removals);

        return (int)points;
    }

    /**
     * Calculates points given the size of the block.
     * @param removals size of block
     * @return points of removal
     */
    public static double getPoints(int removals){
        return Math.pow((double)((removals-1)*2),2.0);
    }

    /**
     * Removes point given from the table, making it empty. Called recursively for adjacent points with same colour.
     * @param point current point
     * @param colour current color
     * @param validMoves list of point
     * @return number of points removed
     */
    int removePoint(Point point, int colour,ArrayList<Point> validMoves)
    {
        Point up,down,left,right;

        int index;
        //delete color
        if(validMoves != null)
            index = validMoves.indexOf(point);
        else  index = -1;

        table.set((point.getRow()*this.col)+point.getCol(),0);

        //removes unnecessary play
        if(index != -1) {
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

    /**
     * Collapse the board vertically if possible.
     */
    void collapseBoardVertically(){
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

    /**
     * Checks if current column is empty.
     * @param col current column
     * @return true if column empty, false if not
     */
    boolean checkIfColumnIsEmpty(int col){
        for(int row = 0;row < this.row;row++)
        {
                if(table.get(row*this.col+col) != 0)
                    return false;
        }
        return true;
    }

    /**
     * Collapse the board vertically if horizontally.
     */
    void collapseBoardHorizontally(){
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

    /**
     * Switches col1 with col2
     * @param col1
     * @param col2
     */
    void switchColumn(int col1, int col2){
        for(int i = 0; i < row;i++)
        {
            int temp = table.get(i*this.col+col1);
            table.set((i*this.col)+col1,table.get(i*this.col+col2));
            table.set((i*this.col)+col2,temp);
        }
    }

    /**
     * Checks if the its possible to make more plays. If not then the game is over.
     * @return if game is over
     */
    public boolean gameOver(){
        return getAllValidMoves().isEmpty();
    }

    /**
     * Print table to CLI.
     */
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

    /**
     * Set table. Makes a copy of it.
     * @param newTable
     */
    public void setTable(ArrayList<Integer> newTable){

        this.table = new ArrayList<>(newTable);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the number of columns.
     * @return number of columns
     */
    public int getCol() {
        return col;
    }
    /**
     * Returns the number of rows.
     * @return number of rows
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the difficulty level.
     * @return difficulty level
     */
    public int getDifficulty(){
        return difficulty;
    }

    /**
     * Returns the table of game
     * @return table
     */
    public ArrayList<Integer> getTable() {
        return table;
    }


}


