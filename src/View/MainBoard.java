package View;

import Players.Player;
import Utility.Utility;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainBoard extends JFrame {


    Utility utility = new Utility();
    static final String playerList[] = {"Player 1", "Player 2"};

    final static int maxGap = 20;
    JComboBox showMapBox = new JComboBox(playerList);;
    int attempts;


    ArrayList<String> words;
    ArrayList<Player> playerMapList = new ArrayList<>();
    JButton nextButton = new JButton("Next player");
    JButton showMapButton = new JButton("Show map");
    JButton replayButton = new JButton("Replay");
    GridLayout gridLayout = new GridLayout(5, 5);
    Label curPlayerLabel = new Label();
    ArrayList<Integer> greenClicked = new ArrayList<>();
    int curPlayer;

    public MainBoard(String name) {
        super(name);

        setResizable(false);
    }

    public void iniMapsAndBoardData() {
        curPlayer = 0;
        attempts = 14;
        this.words = utility.randomizeBoard();
        playerMapList = utility.randomizeMap();
        curPlayerLabel.setText("Player 1's turn, attempts left: " + attempts);
        showMapBox.setSelectedIndex(1);
    }

    public void iniBoardView(JPanel jPanel) {
        jPanel.setLayout(gridLayout);

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
                    JButton tempBtn = (JButton) e.getSource();
                    int index = words.indexOf(tempBtn.getText());
                    validateWithMap(playerMapList.get(curPlayer == 1? 0: 1), index, tempBtn, jPanel);
                    checkIfEndOfGame(jPanel);
                }
            });

            jPanel.add(temp);
        }


    }

    public void nextPlayer() {
        attempts--;
        curPlayer = curPlayer == 0 ? 1 : 0;
        curPlayerLabel.setText("Player " + (curPlayer+1) + "'s turn:, attempts left: " + attempts);
    }

    public void checkIfEndOfGame(JPanel jPanel) {
        if (greenClicked.size() == 15) {
            JOptionPane.showMessageDialog(jPanel,
                    "You have pressed all the green cards! Victory!",
                    "Victory",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if (attempts <= 0) {
            JOptionPane.showMessageDialog(jPanel,
                    "You have used all the attempts! End of game",
                    "End of game",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void validateWithMap(Player validatePlayer, int index, JButton tempBtn, JPanel jPanel) {
        if (validatePlayer.getAllGreen().contains(index) && !greenClicked.contains(index)) {
            greenClicked.add(index);
            tempBtn.setBackground(Color.green);
            System.out.println("Green index: " + index);
        } else if (validatePlayer.getAllBlack().contains(index)) {
            //end of game
            tempBtn.setBackground(Color.black);
            JOptionPane.showMessageDialog(jPanel,
                    "You pressed the black card! End of game.",
                    "End of game",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Black index: " + index);
        } else {
            tempBtn.setBackground(Color.lightGray);
            nextPlayer();
            System.out.println("White index: " + index);
        }
    }

    public void addComponentsToPane(final Container pane) {
        iniMapsAndBoardData();
        final JPanel jPanel = new JPanel();
        iniBoardView(jPanel);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(3, 2));

//Process the showmap button press
        showMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Get the map choice value
                String mapChoice = (String) showMapBox.getSelectedItem();
                if (mapChoice.equalsIgnoreCase("Player 1")) {
                    utility.createAndShowMapGUI(mapChoice, playerMapList.get(0).getAllGreen(), playerMapList.get(0).getAllBlack());
                } else {
                    utility.createAndShowMapGUI(mapChoice, playerMapList.get(1).getAllGreen(), playerMapList.get(1).getAllBlack());
                }
                //Set up the layout of the buttons
                gridLayout.layoutContainer(jPanel);
            }
        });
        //Process the nextplayer button press
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPlayer();
            }
        });

        //Process the replay button press
        replayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jPanel.removeAll();
                iniMapsAndBoardData();
                iniBoardView(jPanel);
                revalidate();
                repaint();
            }
        });
        
        //Add controls
        controls.add(curPlayerLabel);
        controls.add(nextButton);
        controls.add(showMapBox);
        controls.add(showMapButton);
        controls.add(new JLabel());
        controls.add(replayButton);

        pane.add(jPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }


}
