package iart.utilities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pedro Castro on 07/03/2016.
 */
public class HeuristicTable {

    ArrayList<Integer> tableRegions;
    ArrayList<Integer> tableVisited;

    int nextColor = 1;



    public HeuristicTable(int size){

        tableRegions = new ArrayList<>(Collections.nCopies(size, 0));
        tableVisited = new ArrayList<>(Collections.nCopies(size, 0));

    }

    public ArrayList<Integer> getTableVisited() {
        return tableVisited;
    }

    public ArrayList<Integer> getTableRegions() {
        return tableRegions;
    }

    public int getNextColor() {
        return nextColor;
    }

    public void addNextColor(){
        nextColor++;
    }
}
