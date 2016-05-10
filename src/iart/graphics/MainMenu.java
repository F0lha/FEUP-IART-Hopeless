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

        GridBagConstraints gbc = new GridBagConstraints();


        dfs = new JButton("DFS");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;


        bfs = new JButton("BFS");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        iddfs = new JButton("IDDFS");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;

        greedy = new JButton("GREEDY");
        greedy.setAlignmentX(Component.CENTER_ALIGNMENT);

        aStar = new JButton("A*");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;

        idaStar = new JButton("IDASTAR");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;

        panelmenuRight = new JPanel(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        panelmenuRight.setSize(100,600);
        panelmenuRight.setBackground(Color.red);

        panelmenuRight.add(dfs, gbc);
        panelmenuRight.add(bfs, gbc);
        panelmenuRight.add(iddfs, gbc);
        panelmenuRight.add(aStar, gbc);
        panelmenuRight.add(idaStar, gbc);
        panelmenuRight.add(greedy, gbc);

        this.frame.add(panelmenuRight);

        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
