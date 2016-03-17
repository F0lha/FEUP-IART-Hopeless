/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class Point {
    int row;
    int col;


    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
    int getRow(){
        return row;
    }
    int getCol(){
        return col;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Point && getRow() == ((Point) o).getRow() && getCol() == ((Point) o).getCol());
    }

    public String toString(){
        return "(" + row +"," + col + ")";
    }
}
