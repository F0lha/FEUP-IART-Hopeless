package iart.graphics;

import iart.game.Hopeless;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by inesa on 19/05/2016.
 */
public class CenterPanel extends JPanel {

    public static int PREF_W = 500;
    public static int PREF_H = 300;
    private static int REC_WITH = 30;
    private ArrayList<Rectangle> squares = new ArrayList<Rectangle>();

    public CenterPanel() {

        PREF_W = Game.hopeAStar3.getCol()*REC_WITH;
        PREF_H = Game.hopeAStar3.getRow()*REC_WITH;

        for(int j = 0; j < Game.hopeAStar3.getRow(); j++){
            for(int i = 0; i < Game.hopeAStar3.getCol(); i++) {
               addSquare(i*REC_WITH , j*REC_WITH, REC_WITH, REC_WITH);
            }
        }
        //setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public void addSquare(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        squares.add(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int j = 0; j < Game.hopeAStar3.getRow(); j++){
            for(int i = 0; i < Game.hopeAStar3.getCol(); i++) {

                switch (Game.hopeAStar3.table.get(j * Game.hopeAStar3.getCol() + i)){
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
                g2.fillRect(i*REC_WITH , j*REC_WITH, REC_WITH, REC_WITH);
                g2.draw(squares.get(j * Game.hopeAStar3.getCol() + i));
            }
        }
    }
}
