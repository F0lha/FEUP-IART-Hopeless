package iart.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 19/05/2016.
 */
public class WestPanel extends JPanel {

    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton idaStar;

    private static final int PREF_W = 200;
    private static final int PREF_H = 400;


    public WestPanel() {
        add(Box.createHorizontalStrut(100));
        setBorder(BorderFactory.createLineBorder(Color.black));

        dfs = new JButton("DFS");
        bfs = new JButton("BFS");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("GREEDY");
        aStar = new JButton("A*");
        idaStar = new JButton("IDASTAR");

        add(dfs);
        add(bfs);
        add(iddfs);
        add(aStar);
        add(idaStar);
        add(greedy);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
