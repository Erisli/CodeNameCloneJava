package View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MapBoard extends JFrame {


    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;

    ArrayList<Integer> green;
    ArrayList<Integer> black;
    JButton applyButton = new JButton("Apply gaps");
    GridLayout experimentLayout = new GridLayout(5, 5);

    public MapBoard(String name, ArrayList<Integer> green, ArrayList<Integer> black) {
        super(name);
        this.green = green;
        this.black = black;
        setResizable(false);
    }

    public void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
    }

    public void addComponentsToPane(final Container pane) {
        initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 3));

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
            btn.setForeground(Color.GRAY);
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
        pane.add(controls, BorderLayout.SOUTH);
    }

}
