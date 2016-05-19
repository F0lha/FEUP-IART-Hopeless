package iart.graphics;

import iart.game.Hopeless;

import java.awt.*;

import javax.swing.*;

public class Game extends JFrame {

    private static int PREF_W = 1000;
    private static int PREF_H = 600;

    public Game(){
        super("Hopeless");

        Hopeless hopeAStar3 = new Hopeless(10,20,4);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterPanel centerPanel = new CenterPanel(hopeAStar3);
        setLayout(new BorderLayout());
        NorthPanel north = new NorthPanel();
        Panel east = new Panel();
        east.add(Box.createHorizontalStrut(100));
        WestPanel west = new WestPanel(9999);
        SouthPanel south = new SouthPanel();

        PREF_W = west.PREF_W + centerPanel.PREF_W + 100 ;
        PREF_H = 100 + centerPanel.PREF_H + 100 + south.PREF_H;

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

    public static void main(String[] args) {
        new Game();
    }

}

