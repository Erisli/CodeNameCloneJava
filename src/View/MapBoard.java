package View;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MapBoard extends JFrame {

    final static int maxGap = 20;

    ArrayList<Integer> green;
    ArrayList<Integer> black;
    GridLayout gridLayout = new GridLayout(5, 5);

    public MapBoard(String name, ArrayList<Integer> green, ArrayList<Integer> black) {
        super(name);
        this.green = green;
        this.black = black;
        setResizable(false);
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(gridLayout);

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 5) + maxGap,
                (int) (buttonSize.getHeight() * 5) + maxGap * 2));

        //Add buttons to experiment with Grid Layout
        for (int i =0;i<25;i++)
        {
            JButton btn = new JButton();
            btn.setBackground(Color.BLACK);
            if(green.contains(i)){
                btn.setBackground(Color.GREEN);
            }else if(black.contains(i)){
                btn.setBackground(Color.BLACK);
            }else{
                btn.setBackground(Color.WHITE);
            }
            compsToExperiment.add(btn);

        }

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
    }
}
