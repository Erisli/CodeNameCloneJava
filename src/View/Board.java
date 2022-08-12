package View;

import javax.swing.*;
import java.awt.*;
abstract class Board extends JFrame {

    public Board(String name) {
        super(name);
    }

    public abstract void iniBoardView();
    public abstract void addComponentsToPane(final Container pane);
    public abstract void setBoardLayout(JPanel jPanel, GridLayout gridLayout);
}
