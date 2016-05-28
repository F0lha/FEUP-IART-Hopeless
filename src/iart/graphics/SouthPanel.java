package iart.graphics;

import iart.game.Hopeless;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    public SpinnerModel diffSpModel, widthSpModel, hightSpModel;
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
                        15, //max
                        1);//step
        JSpinner spinner = new JSpinner(hightSpModel);
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
                        30, //max
                        1);//step
        spinner = new JSpinner(widthSpModel);
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
        add(spinner);

        jlabel = new JLabel();
        jlabel.setText("Move - (" + x + " , " + y + ")");
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200, 60));
        add(jlabel);

        //listeners
        buttons();
    }

    public void buttons() {
        newBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.newBoard();
                Game.west.play.setVisible(false);
            }
        });

        resetCurrentBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.resetBoard();
                Game.west.play.setVisible(false);
            }
        });
    }
}
