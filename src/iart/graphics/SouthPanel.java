package iart.graphics;

import iart.game.Hopeless;

import javax.swing.*;
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

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(10));
        add(slider);

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
                Game.hopeAStar3 = new Hopeless(Game.HBOARD, Game.WBOARD, Game.DIFF);

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

                System.out.println("CArregou new! ------------------------");
                Game.hopeAStar3.print();
            }
        });

        resetCurrentBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.hopeAStar3.table = initialTable;

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

                System.out.println("CArregou new! ------------------------");
                Game.hopeAStar3.print();
            }
        });
    }

    ;
}
