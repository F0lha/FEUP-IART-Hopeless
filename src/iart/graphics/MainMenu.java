package iart.graphics;

import javax.swing.*;

/**
 * Created by inesa on 28/04/2016.
 */
public class MainMenu extends JFrame{


    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton idaStar;
    private JPanel panel1;

    public void MainMenu(){
        setLayout(null);

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

        add(panel1);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
