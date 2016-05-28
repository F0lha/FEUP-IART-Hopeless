package iart.graphics;

import iart.game.Hopeless;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static iart.graphics.Utilities.changeBoard;
import static iart.graphics.Utilities.repaintTable;

/**
 * Created by inesa on 19/05/2016.
 */
public class SouthPanel extends JPanel {
    private JButton newBoard;
    private JButton resetCurrentBoard;

    ArrayList<Integer> initialTable;

    public static final int PREF_W = 200;
    public static final int PREF_H = 300;
    public JLabel jlabel;
    private SpinnerModel diffSpModel, widthSpModel, hightSpModel;
    public int x = 0;
    public int y = 0;

    public SouthPanel(ArrayList<Integer> h) {
        initialTable = h;
        add(Box.createVerticalStrut(100));
        newBoard = new JButton("New Board");
        newBoard.setPreferredSize(new Dimension(100, 30));
        add(newBoard);
        add(Box.createHorizontalStrut(20));
        resetCurrentBoard = new JButton("Reset Current Board");
        resetCurrentBoard.setPreferredSize(new Dimension(150, 30));
        add(resetCurrentBoard);

        add(Box.createHorizontalStrut(20));

        jlabel = new JLabel();
        jlabel.setText("Board Height");
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(110, 60));

        add(jlabel);

        hightSpModel =
                new SpinnerNumberModel(Game.HBOARD, //initial value
                        1, //min
                        10, //max
                        1);//step
        JSpinner spinner = new JSpinner(hightSpModel);
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
              // System.out.println("Altura " + ((JSpinner)e.getSource()).getValue());
                changeBoard('H', e);
            }
        });
        add(spinner);

        jlabel = new JLabel();
        jlabel.setText("Board Width");
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(110, 60));

        add(jlabel);

        widthSpModel  =
                new SpinnerNumberModel(Game.WBOARD, //initial value
                        1, //min
                        20, //max
                        1);//step
        spinner = new JSpinner(widthSpModel);
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
              //  System.out.println("Largura " + ((JSpinner)e.getSource()).getValue());
                changeBoard('W', e);
            }
        });
        add(spinner);

        jlabel = new JLabel();
        jlabel.setText("Board Difficulty");
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(130, 60));

        add(jlabel);

        diffSpModel =
                new SpinnerNumberModel(Game.DIFF, //initial value
                        1, //min
                        7, //max
                        1);//step
        spinner = new JSpinner(diffSpModel);
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
               // System.out.println("Dificuldade " + ((JSpinner)e.getSource()).getValue());
                changeBoard('D', e);
            }
        });
        add(spinner);

        jlabel = new JLabel();
        jlabel.setText("Move - (" + x + " , " + y + ")");
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200, 60));
        add(jlabel);
        buttons();
    }


    public void buttons() {
        newBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Game.hopeAStar3 = new Hopeless((int)hightSpModel.getValue(), (int) widthSpModel.getValue(), (int)diffSpModel.getValue());

                Game.centerPanel.addSquaresNewBoard();
                repaintTable();

                Game.score = 0;
                Game.west.jlabel.setText("Score: " + Game.score);
                Game.west.jlabel.paintImmediately( Game.west.jlabel.getVisibleRect());

                Game.south.jlabel.setText("Move - (" + 0  + " , " + 0 + ")");
                Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

                initialTable = new ArrayList<Integer>(Game.hopeAStar3.getTable());

            }
        });

        resetCurrentBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.hopeAStar3.setTable(initialTable);

                for (int j = 0; j < Game.hopeAStar3.getRow(); j++) {
                    for (int i = 0; i < Game.hopeAStar3.getCol(); i++) {
                        Game.centerPanel.paintImmediately(i * Game.centerPanel.REC_WITH, j * Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH);
                    }
                }

                Game.score = 0;
                Game.west.jlabel.setText("Score: " + Game.score);
                Game.west.jlabel.paintImmediately( Game.west.jlabel.getVisibleRect());

                Game.south.jlabel.setText("Move - (" + 0  + " , " + 0 + ")");
                Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

            }
        });
    }

    ;
}
