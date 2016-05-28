package iart.graphics;

import iart.game.Hopeless;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static iart.graphics.Utilities.runAStar;

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
    Hopeless hopeAStar3;
    public JLabel jlabel;

    public static final int PREF_W = 200;
    public static final int PREF_H = 300;


    public WestPanel() {
        add(Box.createHorizontalStrut(100));
        //setBorder(BorderFactory.createLineBorder(Color.black));

        dfs = new JButton("DFS");
        bfs = new JButton("BFS");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("GREEDY");
        aStar = new JButton("A*");

        add(dfs);
        dfs.setPreferredSize(new Dimension(100, 30));
        add(bfs);
        bfs.setPreferredSize(new Dimension(100, 30));
        add(aStar);
        aStar.setPreferredSize(new Dimension(100, 30));
        add(iddfs);
        iddfs.setPreferredSize(new Dimension(100, 30));
        add(greedy);
        greedy.setPreferredSize(new Dimension(100, 30));


        jlabel = new JLabel();
        jlabel.setText("Score: " + Game.score);
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200, 60));
        add(jlabel);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }



    public void buttons() {

        aStar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runAStar(Game.hope, true, jlabel);

/*
                ArrayList<Integer> initialTable = new ArrayList<>(Game.hope.getTable());
                AStarSearch rip = new AStarSearch(Game.hope, true);
                Thread t = new Thread(rip);
                t.start();


                try {
                    Thread thread = new Thread() {
                        public void run() {
                            //rip.setAccelarator(true);
                        }
                    };

                    thread.start();
                    t.join();
                    thread.interrupt();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                ArrayList<iart.utilities.Point> bestMoves = rip.getAStarMoves();


                if (rip.getBestScore() != 0)
                    for (iart.utilities.Point move : bestMoves) {
                        Game.score += Game.hope.makePlay(move, new ArrayList<iart.utilities.Point>());
                        jlabel.setText("Score: " + Game.score);
                        jlabel.paintImmediately(jlabel.getVisibleRect());
                        Game.south.jlabel.setText("Move - (" + move.getCol() + " , " + move.getRow() + ")");
                        Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

                       try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        repaintTabel(bestMoves);

                        System.out.println("Move ----" + move.getRow() + " " + move.getCol());
                        Game.hope.print();

                    }
                System.out.println("Final ----");
                Game.hope.print();


                System.out.println("Moves = " + bestMoves);
                System.out.println("A* Score = " + rip.getBestScore());

                System.out.println("A* Sum Of Points = " + Game.score);
*/

            }
        });


    }
}
