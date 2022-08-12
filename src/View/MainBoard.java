package View;

import Players.Player;
import Utility.Utility;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainBoard extends Board {

    static final String[] playerList = {"Player 1", "Player 2"};
    private ArrayList<Player> playerMapList = new ArrayList<>();
    private int attempts;
    private ArrayList<String> words;
    private ArrayList<Integer> greenClicked = new ArrayList<>();
    private int curPlayer;

    private JPanel jPanel;
    Utility utility = new Utility();
    JComboBox<String> showMapBox = new JComboBox<>(playerList);
    JButton nextButton = new JButton("Next player");
    JButton showMapButton = new JButton("Show map");
    JButton replayButton = new JButton("Replay");
    Label curPlayerLabel = new Label();


    public MainBoard(String name) {
        super(name);
        jPanel = new JPanel();
        setResizable(false);
    }

    public void iniMapsAndBoardData() {
        curPlayer = 0;
        attempts = 14;
        this.words = utility.randomizeBoard();
        playerMapList = utility.randomizeMap();
        curPlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        curPlayerLabel.setText("Player 1's turn, attempts left: " + attempts);
        showMapBox.setSelectedIndex(1);
    }

    @Override
    public void iniBoardView() {
        utility.fakeButton(jPanel);

        //Add buttons to experiment with Grid Layout
        for (String s : words) {
            JButton btn = new JButton(s);
            btn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            btn.addActionListener(e -> {
                JButton tempBtn = (JButton) e.getSource();
                validateWithMap(playerMapList.get(curPlayer == 1 ? 0 : 1), words.indexOf(tempBtn.getText()), tempBtn);
                checkIfEndOfGame();
            });

            jPanel.add(btn);
        }
    }

    public void nextPlayer() {
        attempts--;
        curPlayer = curPlayer == 0 ? 1 : 0;
        curPlayerLabel.setText("Player " + (curPlayer + 1) + "'s turn:, attempts left: " + attempts);
    }

    public void checkIfEndOfGame() {
        if (greenClicked.size() == 15) {
            JOptionPane.showMessageDialog(jPanel,
                    "You have pressed all the green cards! Victory!",
                    "Victory",
                    JOptionPane.WARNING_MESSAGE);
        } else if (attempts <= 0) {
            JOptionPane.showMessageDialog(jPanel,
                    "You have used all the attempts! End of game",
                    "End of game",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void validateWithMap(Player validatePlayer, int index, JButton tempBtn) {
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
        iniBoardView();
        setBoardLayout(jPanel, new GridLayout(5, 5));

        JPanel controls = new JPanel();
        setBoardLayout(controls, new GridLayout(3, 2));

        //Process the show map button press
        showMapButton.addActionListener(e -> {
            //Get the map choice value
            String mapChoice = (String) showMapBox.getSelectedItem();
            if (mapChoice != null)
                    utility.createAndShowMapGUI(mapChoice, playerMapList);

            //Set up the layout of the buttons
            //gridLayout.layoutContainer(jPanel);
        });
        //Process the next player button press
        nextButton.addActionListener(e -> nextPlayer());

        //Process the replay button press
        replayButton.addActionListener(e -> {
            jPanel.removeAll();
            iniMapsAndBoardData();
            iniBoardView();
            revalidate();
            repaint();
        });

        //Add controls
        controls.add(curPlayerLabel);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        controls.add(nextButton);
        showMapBox.setFont(new Font("Arial", Font.BOLD, 14));
        controls.add(showMapBox);
        showMapButton.setFont(new Font("Arial", Font.BOLD, 14));
        controls.add(showMapButton);
        controls.add(new JLabel());
        replayButton.setFont(new Font("Arial", Font.BOLD, 18));
        controls.add(replayButton);

        pane.add(jPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    @Override
    public void setBoardLayout(JPanel _jPanel, GridLayout gridLayout) {
        _jPanel.setLayout(gridLayout);
    }


}
