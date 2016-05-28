package iart.graphics;

import iart.algorithms.*;
import iart.game.Hopeless;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static iart.graphics.Utilities.runAStar;

/**
 * Created by inesa on 19/05/3016.
 */
public class WestPanel extends JPanel {

    private JButton user;
    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton bruteForce;
    private JButton maxMin;
    private JButton aStar2;
    JButton play;
    public JLabel jlabelScore;
    public JLabel jlabelHighScore;

    public static final int PREF_W = 200;
    public static final int PREF_H = 350;


    public WestPanel() {

        add(Box.createHorizontalStrut(100));

        user = new JButton("User");
        dfs = new JButton("DFS");
        bfs = new JButton("BFS");
        bruteForce = new JButton("Brute Force");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("Greedy");
        aStar = new JButton("A* (h*(n) = 0)");
        aStar2 = new JButton("A*");
        maxMin = new JButton("MaxMin");
        play = new JButton("Play");

        add(user);
        user.setPreferredSize(new Dimension(120, 30));
        add(dfs);
        dfs.setPreferredSize(new Dimension(120, 30));
        add(bfs);
        bfs.setPreferredSize(new Dimension(120, 30));
        add(bruteForce);
        bruteForce.setPreferredSize(new Dimension(120, 30));
        add(aStar);
        aStar.setPreferredSize(new Dimension(120, 30));
        add(aStar2);
        aStar2.setPreferredSize(new Dimension(120, 30));
        add(iddfs);
        iddfs.setPreferredSize(new Dimension(120, 30));
        add(greedy);
        greedy.setPreferredSize(new Dimension(120, 30));
        add(maxMin);
        maxMin.setPreferredSize(new Dimension(120, 30));

        jlabelScore = new JLabel();
        jlabelScore.setText("Score: " + Game.score);
        jlabelScore.setFont(new Font("Verdana", 1, 15));
        jlabelScore.setHorizontalAlignment(0);
        jlabelScore.setVerticalAlignment(0);
        jlabelScore.setPreferredSize(new Dimension(200, 20));
        add(jlabelScore);

        jlabelHighScore = new JLabel();
        jlabelHighScore.setText("HighScore: " + Game.highScore);
        jlabelHighScore.setFont(new Font("Verdana", 1, 15));
        jlabelHighScore.setHorizontalAlignment(0);
        jlabelHighScore.setVerticalAlignment(0);
        jlabelHighScore.setPreferredSize(new Dimension(200, 20));
        add(jlabelHighScore);

        add(play);
        play.setPreferredSize(new Dimension(120, 30));
        play.setVisible(false);

        buttons();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }



    public void buttons() {

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(true);
                Game.resetBoard();
            }
        });
        aStar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                AStarSearch alg = new AStarSearch(newHope,true);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getAStarMoves();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        dfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                DepthFirstSearch alg = new DepthFirstSearch(newHope);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getDFSBestPlays();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        bfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                BreadthFirstSearch alg = new BreadthFirstSearch(newHope,false);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getBFSPlays();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        bruteForce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                BreadthFirstSearch alg = new BreadthFirstSearch(newHope,true);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getBFSPlays();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        aStar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                AStarSearch alg = new AStarSearch(newHope,false);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getAStarMoves();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        greedy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                Greedy alg = new Greedy(newHope);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getGreedyPlays();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        iddfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                IDDFS alg = new IDDFS(newHope);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getIDDFSMoves();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        maxMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                Game.centerPanel.setUser(false);
                Game.resetBoard();
                Hopeless newHope = new Hopeless(Game.hope.getRow(),Game.hope.getCol(),Game.hope.getDifficulty());
                newHope.setTable(Game.hope.getTable());
                MaxMin alg = new MaxMin(newHope);
                Thread t = new Thread(alg);
                t.start();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            t.join();
                            Game.bestMoves = alg.getBestMaxMinPlays();
                            play.setVisible(true);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.resetBoard();
                Utilities.makePlays(Game.bestMoves);
            }
        });



    }
}
