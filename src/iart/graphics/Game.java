package iart.graphics;

import iart.game.Hopeless;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JFrame {

    private static int PREF_W = 1000;
    private static int PREF_H = 600;
    public static int HBOARD = 5;
    public static int WBOARD = 5;
    public static int DIFF = 4;

    public static int score = 0;
    public static int highScore = 0;
    public static ArrayList<iart.utilities.Point> bestMoves;
    public static Hopeless hope = new Hopeless(HBOARD,WBOARD,DIFF);

    static CenterPanel centerPanel = new CenterPanel();
    static WestPanel west = new WestPanel();
    static SouthPanel south = new SouthPanel(hope.getTable());

    public Game(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        NorthPanel north = new NorthPanel();
        Panel east = new Panel();
        east.add(Box.createHorizontalStrut(100));

        setSize(PREF_W,PREF_H);

        getContentPane().add(north, BorderLayout.PAGE_START);
        getContentPane().add(west, BorderLayout.WEST);
        getContentPane().add(south, BorderLayout.SOUTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(east, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){ Game g =  new Game();
    }

    static public void resetBoard(){
        Game.hope.setTable(Game.south.initialTable);

        Game.centerPanel.addSquaresNewBoard();

        Game.score = 0;

        updateScores();

        Game.south.jlabel.setText("Move - (" + 0  + " , " + 0 + ")");
        Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

        Game.centerPanel.repaintTable(false);
    }

    static public void newBoard(){

        Game.hope = new Hopeless((int)Game.south.hightSpModel.getValue(), (int) Game.south.widthSpModel.getValue(), (int)Game.south.diffSpModel.getValue());

        Game.centerPanel.addSquaresNewBoard();

        Game.score = 0;
        Game.highScore = 0;

        updateScores();

        Game.south.jlabel.setText("Move - (" + 0  + " , " + 0 + ")");
        Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

        Game.south.initialTable = new ArrayList<>(Game.hope.getTable());

        Game.centerPanel.repaintTable(false);
    }

    static public void updateScores(){
        Game.west.jlabelScore.setText("Score: " + Game.score);
        Game.west.jlabelScore.paintImmediately( Game.west.jlabelScore.getVisibleRect());

        Game.west.jlabelHighScore.setText("HighScore: " + Game.highScore);
        Game.west.jlabelHighScore.paintImmediately( Game.west.jlabelScore.getVisibleRect());

    }

}

