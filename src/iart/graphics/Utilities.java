package iart.graphics;

import iart.algorithms.AStarSearch;
import iart.game.Hopeless;
import iart.utilities.Point;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by inesa on 27/05/2016.
 */
public class Utilities {

    public static void runAStar(Hopeless hope, boolean safe, JLabel jlabel) {

        ArrayList<Integer> initialTable = new ArrayList<>(hope.getTable());

        AStarSearch rip = new AStarSearch(hope, safe);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));


        Thread t = new Thread(rip);

        t.start();

        try {
            Thread thread = new Thread() {
                public void run() {
                    do {
                        System.out.println("Score = " + rip.getTopScore());

                        System.out.println("Level = " + rip.getCurrentLevel());

                        System.out.println("Size = " + rip.getOpenListSize());
                    } while (!this.isInterrupted());
                }
            };
            thread.start();
            t.join();
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        ArrayList<Point> bestMoves = rip.getAStarMoves();

        hope.setTable(initialTable);

        int points = 0;

        if (rip.getBestScore() != 0) {
            for (Point move : bestMoves) {

                Game.score += Game.hope.makePlay(move, new ArrayList<iart.utilities.Point>());
                jlabel.setText("Score: " + Game.score);
                jlabel.paintImmediately(jlabel.getVisibleRect());
                Game.south.jlabel.setText("Move - (" + move.getCol() + " , " + move.getRow() + ")");
                Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                repaintTable();

                System.out.println("Move ----" + move.getRow() + " " + move.getCol());
                Game.hope.print();

                System.out.println("Final ----");
                hope.print();


                System.out.println("Moves = " + bestMoves);
                System.out.println("A* Score = " + rip.getBestScore());

                System.out.println("A* Sum Of Points = " + points);
            }
        }
    }

    public static void repaintTable() {

        for (int j = 0; j < Game.hope.getRow(); j++) {
            for (int i = 0; i < Game.hope.getCol(); i++) {
                Game.centerPanel.paintImmediately(i * Game.centerPanel.REC_WITH, j * Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH);
            }
        }
    }

    public static void changeBoard(Character c, ChangeEvent e){

        switch (c){
            case 'H':
                Game.HBOARD = Integer.parseInt(((JSpinner)e.getSource()).getValue().toString());
                Game.hope.setRow(Game.HBOARD);
                break;
            case 'W':
                Game.WBOARD = Integer.parseInt(((JSpinner)e.getSource()).getValue().toString());
                Game.hope.setRow(Game.WBOARD);
                break;
            case 'D':
                Game.DIFF= Integer.parseInt(((JSpinner)e.getSource()).getValue().toString());
                Game.hope.setRow(Game.DIFF);
                break;
            default:
                System.out.println("Error in character! W or H or D");
                break;
        }
    }

}
