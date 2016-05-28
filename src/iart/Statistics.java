package iart;

import iart.algorithms.*;
import iart.game.Hopeless;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Statistics {

    static void createStatistics(int sampleSize, int timeout, int tableRows, int tableCols){

        int i = 0;

        Hopeless AStar;

        Hopeless AStarGoalState = new Hopeless(tableRows,tableCols,4);

        Hopeless bruteForce = new Hopeless(tableRows,tableCols,4);

        Hopeless BFS = new Hopeless(tableRows,tableCols,4);

        Hopeless DFS = new Hopeless(tableRows,tableCols,4);

        Hopeless greedy = new Hopeless(tableRows,tableCols,4);

        Hopeless iddfs = new Hopeless(tableRows,tableCols,4);

        Hopeless maxmin = new Hopeless(tableRows,tableCols,4);

        ArrayList<ArrayList<Integer>> statistics = new ArrayList<>();
        ArrayList<ArrayList<Long>> timeOfExecution = new ArrayList<>();

        while (i < sampleSize) {
            ArrayList<Integer> current = new ArrayList<>();
            ArrayList<Long> timeArray = new ArrayList<>();

            AStar = new Hopeless(tableRows, tableCols, 4);

            //copying tables
            AStarGoalState.setTable(AStar.getTable());
            bruteForce.setTable(AStar.getTable());
            BFS.setTable(AStar.getTable());
            DFS.setTable(AStar.getTable());
            greedy.setTable(AStar.getTable());
            iddfs.setTable(AStar.getTable());
            maxmin.setTable(AStar.getTable());

            //AStar Run
            try {
                AStarSearch AStartThread = new AStarSearch(AStar, true);
                Thread t = new Thread(AStartThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if (AStartThread.isFinished()) {
                    current.add(0,AStartThread.getBestScore());
                } else {
                    System.out.println("Error AStar Safe");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //AStar Safe Run
            try {
                AStarSearch AStartThreadGoalState = new AStarSearch(AStarGoalState, false);
                Thread t = new Thread(AStartThreadGoalState);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if (AStartThreadGoalState.isFinished()) {
                    current.add(1,AStartThreadGoalState.getBestScore());
                } else {
                    System.out.println("Error AStar Improved");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //BruteForce Run
            try {
                BreadthFirstSearch BruteForce = new BreadthFirstSearch(bruteForce,true);
                Thread t = new Thread(BruteForce);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(BruteForce.isFinished())
                {
                    current.add(BruteForce.getBestScore());
                }
                else
                {
                    System.out.println("Error BruteForce");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //BFS Run
            try {
                BreadthFirstSearch BFSThread = new BreadthFirstSearch(BFS,true);
                Thread t = new Thread(BFSThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(BFSThread.isFinished())
                {
                    current.add(BFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error BFS");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //DFS Run
            try {
                DepthFirstSearch DFSThread = new DepthFirstSearch(DFS);
                Thread t = new Thread(DFSThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(DFSThread.isFinished())
                {
                    current.add(DFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error DFS");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Greedy Run
            try {
                Greedy GreedyThread = new Greedy(greedy);
                Thread t = new Thread(GreedyThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(GreedyThread.isFinished())
                {
                    current.add(GreedyThread.getBestScore());
                }
                else
                {
                    System.out.println("Error Greedy");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //IDDFS Run
            try {
                IDDFS IDDFSThread = new IDDFS(iddfs);
                Thread t = new Thread(IDDFSThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(IDDFSThread.isFinished())
                {
                    current.add(IDDFSThread.getBestScore());
                }
                else
                {
                    System.out.println("Error IDDFS");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //IDDFS Run
            try {
                MaxMin MaxMinThread = new MaxMin(maxmin);
                Thread t = new Thread(MaxMinThread);
                long time = System.nanoTime();
                t.start();
                t.join(timeout);
                time -= System.nanoTime();
                if(MaxMinThread.isFinished())
                {
                    current.add(MaxMinThread.getBestScore());
                }
                else
                {
                    System.out.println("Error IDDFS");
                    current.add(-1);
                    t.stop();
                }
                timeArray.add(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            statistics.add(current);
            timeOfExecution.add(timeArray);
            i++;
        }
        makeStatistics(statistics,timeOfExecution);
    }

    static void makeStatistics(ArrayList<ArrayList<Integer>> statistics, ArrayList<ArrayList<Long>> timeOfExecution) {


        try {
            FileOutputStream out = new FileOutputStream(new File("statistics.xlsx"));

            //Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet sheet = workbook.createSheet("Stats");

            makeFirstLine(sheet);

            int i;

            for(i = 0; i < statistics.size();i++){
                Row row = sheet.createRow(i+1);

                Cell cell = row.createCell(0);//ID

                cell.setCellValue(i+1);
                for(int j = 0;j < statistics.get(i).size()*2;j += 2){
                    cell = row.createCell((j)+1);

                    cell.setCellValue(statistics.get(i).get(j/2));

                    cell = row.createCell((j)+2);

                    cell.setCellValue(Math.abs((float)timeOfExecution.get(i).get(j/2) / 1000000));
                }
            }

            makeAverage(sheet,i);

            calculateEfficiency(sheet,i);

            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void makeFirstLine(XSSFSheet sheet){

        Row row = sheet.createRow(0);

        int i = 0;

        Cell nextCell = row.createCell(i++);

        nextCell.setCellValue("TableNumber");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("A* GoalState = 0");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("A* GoalState = 0 (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("A* GoalState = h(n)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("A* GoalState = h(n) (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("BruteForce");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("BruteForce (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("BFS");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("BFS (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("DFS");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("DFS (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("Greedy");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("Greedy (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("IDDFS");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("IDDFS (Time)");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("MaxMin");

        nextCell = row.createCell(i++);

        nextCell.setCellValue("MaxMin (Time)");
    }

    static void makeAverage(XSSFSheet sheet,int i) {

        Row row = sheet.createRow(i + 1);

        int j = 0;

        Cell cell = row.createCell(j++);

        cell.setCellValue("Average");

        for (; j < 17; j++) {
            cell = row.createCell(j);

            String strFormula = "AVERAGE(" + Character.toString((char) (65 + j)) + "2:" + Character.toString((char) (65 + j)) + i + ")";
            cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cell.setCellFormula(strFormula);
        }
    }

        static void calculateEfficiency(XSSFSheet sheet, int i){
            Row row = sheet.createRow(i + 2);

            int j = 0;

            Cell cell = row.createCell(j++);

            cell.setCellValue("Efficiency");

            for (; j < 17; j+=2) {
                cell = row.createCell(j);

                String strFormula = Character.toString((char) (65 + j))+(i+2) + "/" + Character.toString((char) (65 + j + 1)) + (i+2);
                cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                cell.setCellFormula(strFormula);
            }

    }


}
