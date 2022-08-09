package View;

import Players.Map;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainBoard extends JFrame {
    public void createAndShowMapGUI(String name, ArrayList<Integer> green, ArrayList<Integer> black) {
        //Create and set up the window.
        MapBoard map = new MapBoard(name,green, black);
        map.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Set up the content pane.
        map.addComponentsToPane(map.getContentPane());
        //Display the window.
        map.pack();
        map.setVisible(true);
    }
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
    static final String playerList[] = {"Player 1", "Player 2"};

    final static int maxGap = 20;
    JComboBox showMapBox;

    ArrayList<String> words;
    Map player1;
    Map player2;
    JButton applyButton = new JButton("Apply choices");
    JButton showMapButton = new JButton("Show map");
    GridLayout experimentLayout = new GridLayout(5, 5);

    public MainBoard(String name, ArrayList<String> words, Map player1,Map player2) {
        super(name);
        this.words = words;
        this.player1 = player1;
        this.player2 = player2;
        setResizable(false);
    }

    public void initBtns() {
        showMapBox = new JComboBox(playerList);
    }

    public void addComponentsToPane(final Container pane) {
        initBtns();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 2));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 5) + maxGap,
                (int) (buttonSize.getHeight() * 5) + maxGap * 2));

        //Add buttons to experiment with Grid Layout
        for (String s : words)
            compsToExperiment.add(new JButton(s));

        //Add controls to set up horizontal and vertical gaps
        controls.add(new Label("Player 1's turn"));
        controls.add(applyButton);
        controls.add(showMapBox);
        controls.add(showMapButton);


        //Process the Apply gaps button press
        showMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Get the map choice value
                String mapChoice = (String) showMapBox.getSelectedItem();

                if(mapChoice.equalsIgnoreCase("Player 1")){
                    createAndShowMapGUI("Player 1",player1.getAllGreen(),player1.getAllBlack());
                }else if(mapChoice.equalsIgnoreCase("Player 2")){
                    createAndShowMapGUI("Player 2",player2.getAllGreen(),player2.getAllBlack());
                }
                //Set up the layout of the buttons
                experimentLayout.layoutContainer(compsToExperiment);
            }
        });

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: validation
            }
        });

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }



}
