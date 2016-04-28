package iart.graphics;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 28/04/2016.
 */
public class MainMenu {

    public JFrame frame;
    private JPanel panel1;
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
        this.frame.setLayout(new FlowLayout());
        dfs = new JButton("DFS");
        dfs.setBounds(50, 50, 80, 25);

        bfs = new JButton("BFS");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("GREEDY");
        aStar = new JButton("A*");
        idaStar = new JButton("IDASTAR");
        panel1 = new JPanel();

        panel1.add(dfs);
        panel1.add(bfs);
        panel1.add(iddfs);
        panel1.add(aStar);
        panel1.add(idaStar);
        panel1.add(greedy);

        this.frame.add(panel1);

        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
