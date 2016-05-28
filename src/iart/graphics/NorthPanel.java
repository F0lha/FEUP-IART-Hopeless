package iart.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by inesa on 19/05/2016.
 */
public class NorthPanel  extends JPanel {

    public NorthPanel() {
        add(Box.createVerticalStrut(70));
        JLabel jlabel = new JLabel("HOPELESS");
        jlabel.setFont(new Font("Verdana",1,50));
        add(jlabel);
    }
}
