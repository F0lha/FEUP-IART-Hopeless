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

    private boolean user = true;
    public static int REC_WITH = 30;
    private ArrayList<Rectangle> squares = new ArrayList<>();
    private ArrayList<iart.utilities.Point> points = new ArrayList<>();
    private MouseListener mouseListener= null;

    public JLabel gameOver;

    public CenterPanel() {

        gameOver = new JLabel();
        gameOver.setText("GAMEOVER");
        gameOver.setFont(new Font("Verdana", 1, 15));
        gameOver.setHorizontalAlignment(0);
        gameOver.setVerticalAlignment(0);
        gameOver.setPreferredSize(new Dimension(1000,500));
        gameOver.setForeground(Color.WHITE);
        add(gameOver);
        gameOver.setVisible(false);

        addSquaresNewBoard();

        mouseListener();
    }

    private void addSquare(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        squares.add(rect);
    }

    public void addSquaresNewBoard(){
        squares = new ArrayList<>();
        for (int j = 0; j < Game.hope.getRow(); j++) {
            for (int i = 0; i < Game.hope.getCol(); i++) {
                addSquare(getRealI(i) * REC_WITH, getRealJ(j) * REC_WITH, REC_WITH, REC_WITH);
            }
        }
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
                        case 5:
                            g2.setColor(Color.pink);
                            break;
                        case 6:
                            g2.setColor(Color.cyan);
                            break;
                        case 7:
                            g2.setColor(Color.GREEN);
                            break;
                        case 8:
                            g2.setColor(Color.magenta);
                            break;
                        case 9:
                            g2.setColor(Color.lightGray);
                            break;
                        case 10:
                            g2.setColor(Color.white);
                            break;
                        default:
                            g2.setColor(Color.black);
                            break;
                }

                g2.fillRect(getRealI(i) * REC_WITH, getRealJ(j)* REC_WITH, REC_WITH, REC_WITH);
            }
        }
    }

    public int offsetI(){
        return (this.getWidth()/REC_WITH-Game.hope.getCol())/2;
    }

    public int offsetJ(){
        return (this.getHeight()/REC_WITH-Game.hope.getRow())/2;
    }

    public int getRealI(int i){
        return i+offsetI();
    }

    public int getRealJ(int j){
        return j+offsetJ();
    }


    public void mouseListener() {
        addMouseListener(
                this.mouseListener = new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                int x = e.getX();
                int y = e.getY();

                x = (int) Math.floor((float) (x - offsetI() * REC_WITH) / REC_WITH);
                y = (int) Math.floor((float) (y - offsetJ() * REC_WITH) / REC_WITH);

                int score;
                if ((score = Game.hope.makePlay(new iart.utilities.Point(y, x), null)) > 0) {

                    Game.score += score;

                    if(Game.score > Game.highScore)
                        Game.highScore = Game.score;

                    Game.updateScores();

                    Game.south.jlabel.setText("Move - (" + y + " , " + x + ")");
                    Game.south.jlabel.paintImmediately(Game.south.jlabel.getVisibleRect());

                    repaintTable(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void repaintTable(boolean immediate) {
        gameOver.setVisible(Game.hope.gameOver());
        if(!immediate) {
            this.removeAll();
            this.revalidate();
            this.repaint();
            add(gameOver);
        }
        else{
            for (int j = 0; j < Game.hope.getRow(); j++) {
                for (int i = 0; i < Game.hope.getCol(); i++) {
                    Game.centerPanel.paintImmediately(Game.centerPanel.getRealI(i) * Game.centerPanel.REC_WITH, Game.centerPanel.getRealJ(j) * Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH);
                }
            }
        }
    }

    public void setUser(boolean user)
    {
        this.user = user;
        this.removeMouseListener(mouseListener);
        if(user)
            mouseListener();
    }


}
