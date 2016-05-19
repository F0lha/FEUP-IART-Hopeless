package iart.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 19/05/3016.
 */
public class WestPanel extends JPanel {

    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton idaStar;

    public static final int PREF_W = 200;
    public static final int PREF_H = 300;


    public WestPanel(int score) {
        add(Box.createHorizontalStrut(100));
        //setBorder(BorderFactory.createLineBorder(Color.black));

        dfs = new JButton("DFS");
        bfs = new JButton("BFS");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("GREEDY");
        aStar = new JButton("A*");
        idaStar = new JButton("IDASTAR");

        add(dfs);
        dfs.setPreferredSize(new Dimension(100,30));
        add(bfs);
        bfs.setPreferredSize(new Dimension(100,30));
        add(iddfs);
        iddfs.setPreferredSize(new Dimension(100,30));
        add(aStar);
        aStar.setPreferredSize(new Dimension(100,30));
        add(idaStar);
        idaStar.setPreferredSize(new Dimension(100,30));
        add(greedy);
        greedy.setPreferredSize(new Dimension(100,30));

        JLabel jlabel = new JLabel("Score: " + score);
        jlabel.setFont(new Font("Verdana",1,15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200,20));
        add(jlabel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
