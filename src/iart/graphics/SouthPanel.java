package iart.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 19/05/2016.
 */
public class SouthPanel extends JPanel {
    private JButton newBoard;
    private JButton resetCurrentBoard;

    public static final int PREF_W = 200;
    public static final int PREF_H = 300;
    public int x = 0;
    public int y = 0;

    public SouthPanel() {
        add(Box.createVerticalStrut(100));
        newBoard = new JButton("New Board");
        newBoard.setPreferredSize(new Dimension(100,30));
        add(newBoard);
        add(Box.createHorizontalStrut(20));
        resetCurrentBoard = new JButton("Reset Current Board");
        resetCurrentBoard.setPreferredSize(new Dimension(150,30));
        add(resetCurrentBoard);

        add(Box.createHorizontalStrut(20));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(10));
        add(slider);

        JLabel jlabel = new JLabel("Move - (" + x + " , " + y + ")");
        jlabel.setFont(new Font("Verdana",1,15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200,60));
        add(jlabel);

    }
}
