package View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainBoard extends JFrame {
    //    private MyColor[][] myColors;
//    private JLabel[][] myLabels;
//
//    public ColorGrid(int rows, int cols, int cellWidth) {
//        myColors = new MyColor[rows][cols];
//        myLabels = new JLabel[rows][cols];
//
//        MyMouseListener myListener = new MyMouseListener(this);
//        Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
//        setLayout(new GridLayout(rows, cols));
//        for (int row = 0; row < myLabels.length; row++) {
//            for (int col = 0; col < myLabels[row].length; col++) {
//                JLabel myLabel = new JLabel();
//                myLabel = new JLabel();
//                myLabel.setOpaque(true);
//                MyColor myColor = MyColor.GREEN;
//                myColors[row][col] = myColor;
//                myLabel.setBackground(myColor.getColor());
//                myLabel.addMouseListener(myListener);
//                myLabel.setPreferredSize(labelPrefSize);
//                add(myLabel);
//                myLabels[row][col] = myLabel;
//            }
//        }
//    }
//
//    public MyColor[][] getMyColors() {
//        return myColors;
//    }
//
//    public void labelPressed(JLabel label) {
//        for (int row = 0; row < myLabels.length; row++) {
//            for (int col = 0; col < myLabels[row].length; col++) {
//                if (label == myLabels[row][col]) {
//                    MyColor myColor = myColors[row][col].next();
//                    myColors[row][col] = myColor;
//                    myLabels[row][col].setBackground(myColor.getColor());
//                }
//            }
//        }
//    }
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;

    ArrayList<String> board;
    JButton applyButton = new JButton("Apply gaps");
    GridLayout experimentLayout = new GridLayout(5, 5);

    public MainBoard(String name, ArrayList<String> board) {
        super(name);
        this.board = board;
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
        for (String s : board)
            compsToExperiment.add(new JButton(s));

        //Add controls to set up horizontal and vertical gaps
        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
        controls.add(horGapComboBox);
        controls.add(verGapComboBox);
        controls.add(applyButton);

        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Get the horizontal gap value
                String horGap = (String) horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String) verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value
                experimentLayout.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                experimentLayout.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                experimentLayout.layoutContainer(compsToExperiment);
            }
        });
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

}
