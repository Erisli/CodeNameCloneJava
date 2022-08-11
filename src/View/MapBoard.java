package View;

import Utility.Utility;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MapBoard extends JFrame {


    Utility utility = new Utility();
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
        utility.fakeButton(compsToExperiment);

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
