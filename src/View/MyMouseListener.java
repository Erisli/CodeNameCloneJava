package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton l = (JButton) e.getSource();

        if(l.getBackground() != Color.red) {
            l.setOpaque(true);
            l.setBackground(Color.red);
        }
        else{
            l.setBackground(new JButton().getBackground());
        }
    }
}