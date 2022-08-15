import Utility.Utility;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Utility utility = new Utility();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(utility::createAndShowMainGUI);
    }
}
