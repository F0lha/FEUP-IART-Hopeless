package iart.graphics;

import iart.utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by inesa on 19/05/2016.
 */
public class CenterPanel extends JPanel {

    public static int PREF_W = 500;
    public static int PREF_H = 300;
    public static int REC_WITH = 30;
    private ArrayList<Rectangle> squares = new ArrayList<Rectangle>();
    private ArrayList<iart.utilities.Point> points = new ArrayList<>();

    public CenterPanel() {

        PREF_W = Game.hope.getCol() * REC_WITH;
        PREF_H = Game.hope.getRow() * REC_WITH;

        for (int j = 0; j < Game.hope.getRow(); j++) {
            for (int i = 0; i < Game.hope.getCol(); i++) {
                addSquare(i * REC_WITH, j * REC_WITH, REC_WITH, REC_WITH);
            }
        }
        //setBorder(BorderFactory.createLineBorder(Color.black));

        mouseListener();

    }

    private void addSquare(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        squares.add(rect);
    }

    public void addSquaresNewBoard(){
        for (int j = 0; j < Game.hope.getRow(); j++) {
            for (int i = 0; i < Game.hope.getCol(); i++) {
                addSquare(i * REC_WITH, j * REC_WITH, REC_WITH, REC_WITH);
            }
        }
        mouseListener();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int j = 0; j < Game.hope.getRow(); j++) {
            for (int i = 0; i < Game.hope.getCol(); i++) {

                    switch (Game.hope.getColor(new iart.utilities.Point(j,i))){
                    case 1:
                        g2.setColor(Color.red);
                        break;
                    case 2:
                        g2.setColor(Color.orange);
                        break;
                    case 3:
                        g2.setColor(Color.yellow);
                        break;
                    case 4:
                        g2.setColor(Color.blue);
                        break;
                    default:
                        g2.setColor(Color.black);
                        break;

                }

                g2.fillRect(i * REC_WITH, j * REC_WITH, REC_WITH, REC_WITH);
                g2.draw(squares.get(j * Game.hope.getCol() + i));
            }
        }
    }


    public void mouseListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (y >= 0 && y <= PREF_H && x >= 0 && x <= PREF_W) {
                    System.out.println("X " + x + " Y " + y);
                    System.out.println("X " + (int) Math.floor(x / 30) + " Y " + (int) Math.floor(y / 30));
                    x = (int) Math.floor(x / 30);
                    y = (int) Math.floor(y / 30);

                    int score;
                    if((score = Game.hope.makePlay(new iart.utilities.Point(y,x),null)) > 0){

                        Game.score += score;

                        Game.west.jlabel.setText("Score: " + Game.score);
                        Game.west.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

                        Game.south.jlabel.setText("Move - (" + y + " , " + x + ")");
                        Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

                        Utilities.repaintTable();
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}
