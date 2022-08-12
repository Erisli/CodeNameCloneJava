package View;

import Players.Player;
import Utility.Utility;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MapBoard extends Board {
    private Player player;
    private JPanel jPanel;
    Utility utility = new Utility();

    public MapBoard(String name, ArrayList<Player> players) {
        super(name);
        jPanel = new JPanel();
        if (name.equalsIgnoreCase("Player 1"))
            player = players.get(0);
        else
            player = players.get(1);

        setResizable(false);
    }

    @Override
    public void iniBoardView() {
        utility.fakeButton(jPanel);

        //Add buttons to experiment with Grid Layout
        for (int i = 0; i < 25; i++) {
            JButton btn = new JButton();
            btn.setBackground(Color.BLACK);
            if (player.getAllGreen().contains(i)) {
                btn.setBackground(Color.GREEN);
            } else if (player.getAllBlack().contains(i)) {
                btn.setBackground(Color.BLACK);
            } else {
                btn.setBackground(Color.WHITE);
            }
            jPanel.add(btn);

        }
    }

    public void addComponentsToPane(final Container pane) {
        iniBoardView();
        setBoardLayout(jPanel, new GridLayout(5, 5));

        pane.add(jPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
    }

    @Override
    public void setBoardLayout(JPanel _jPanel, GridLayout gridLayout) {
        _jPanel.setLayout(gridLayout);
    }


}
