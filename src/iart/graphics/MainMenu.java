package iart.graphics;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 28/04/2016.
 */
public class MainMenu {

    public JFrame frame;
    private JPanel panelmenuRight;
    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton idaStar;

    public static void main(String[] args)
    {
        new MainMenu();
    }
    public MainMenu(){
        frame = new JFrame("HOPELESS");
        this.frame.setLayout(null);

        dfs = new JButton("DFS");


        bfs = new JButton("BFS");

        iddfs = new JButton("IDDFS");

        greedy = new JButton("GREEDY");

        aStar = new JButton("A*");

        idaStar = new JButton("IDASTAR");

        panelmenuRight = new JPanel(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        panelmenuRight.setSize(100,600);
        panelmenuRight.setBackground(Color.red);


        this.frame.add(panelmenuRight);

        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
