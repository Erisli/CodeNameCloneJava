package View;

import Players.Map;

import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainBoard extends JFrame {
    public void createAndShowMapGUI(String name, ArrayList<Integer> green, ArrayList<Integer> black) {
        //Create and set up the window.
        MapBoard map = new MapBoard(name, green, black);
        map.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Set up the content pane.
        map.addComponentsToPane(map.getContentPane());
        //Display the window.
        map.pack();
        map.setVisible(true);
    }

    static final String playerList[] = {"Player 1", "Player 2"};

    final static int maxGap = 20;
    JComboBox showMapBox;

    ArrayList<String> words;
    Map player1;
    Map player2;
    JButton applyButton = new JButton("Apply choices");
    JButton showMapButton = new JButton("Show map");
    GridLayout gridLayout = new GridLayout(5, 5);
    Label curPlayerLabel = new Label();
    int curPlayer;
    ArrayList<Integer> choices = new ArrayList<>();

    public MainBoard(String name, ArrayList<String> words, Map player1, Map player2) {
        super(name);
        this.words = words;
        this.player1 = player1;
        this.player2 = player2;
        curPlayer = 1;
        setResizable(false);
    }

    public void initBtns() {
        showMapBox = new JComboBox(playerList);
    }

    public void addComponentsToPane(final Container pane) {
        initBtns();
        final JPanel jPanel = new JPanel();
        jPanel.setLayout(gridLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 2));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Dimension buttonSize = b.getPreferredSize();
        jPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 5) + maxGap,
                (int) (buttonSize.getHeight() * 5) + maxGap * 2));

        //Add buttons to experiment with Grid Layout
        for (String s : words) {
            JButton temp = new JButton(s);
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton l = (JButton) e.getSource();

                    if (l.getBackground() != Color.red) {
                        l.setOpaque(true);
                        l.setBackground(Color.red);
                        choices.add(words.indexOf(l.getText()));
                    } else {
                        l.setBackground(new JButton().getBackground());
                        choices.remove(words.indexOf(l.getText()));
                    }
//                    System.out.println(choices.get(0));
                }
            });
            jPanel.add(temp);

        }

        //Add controls to set up horizontal and vertical gaps
        curPlayerLabel.setText("Player 1's turn:");
        controls.add(curPlayerLabel);
        controls.add(applyButton);
        controls.add(showMapBox);
        controls.add(showMapButton);


        //Process the showmap button press
        showMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Get the map choice value
                String mapChoice = (String) showMapBox.getSelectedItem();

                if (mapChoice.equalsIgnoreCase("Player 1")) {
                    createAndShowMapGUI("Player 1", player1.getAllGreen(), player1.getAllBlack());
                } else if (mapChoice.equalsIgnoreCase("Player 2")) {
                    createAndShowMapGUI("Player 2", player2.getAllGreen(), player2.getAllBlack());
                }
                //Set up the layout of the buttons
//                gridLayout.layoutContainer(jPanel);
            }
        });

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: validation
                Component[] comp = jPanel.getComponents();
                Map playerMap = new Map();
                if (curPlayer == 1) {
                    //TODO: validate with player2's map
                    playerMap = player2;

                } else if (curPlayer == 2) {
                    //TODO: validate with player1's map
                    playerMap = player1;
                }
                for (int i : choices) {
                    JButton tempBtn = new JButton();
                    for (int j = 0; j < comp.length; j++) {
                        if (words.get(i).equalsIgnoreCase(((JButton) comp[j]).getText())) {
                            tempBtn = (JButton) comp[j];

                        }
                    }
                    if (playerMap.getAllGreen().contains(i)) {
                        System.out.println("Correct index: " + i);
                        tempBtn.setBackground(Color.green);

                    } else if (playerMap.getAllBlack().contains(i)) {
                        //end of game
                        tempBtn.setBackground(Color.black);
                        JOptionPane.showMessageDialog(jPanel,
                                "You pressed the black card! End of game.",
                                "End of game",
                                JOptionPane.WARNING_MESSAGE);

                    } else {
                        tempBtn.setBackground(Color.lightGray);
                        System.out.println("White index: " + i);
                    }
                }


                curPlayer = curPlayer == 1 ? 2 : 1;
                curPlayerLabel.setText("Player " + curPlayer + "'s turn:");
                choices = new ArrayList<>();
            }

        });

        pane.add(jPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }


}
