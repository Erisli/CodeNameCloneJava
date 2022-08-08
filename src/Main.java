import Players.Map;
import Utility.Utility;
import View.MainBoard;
import View.MapBoard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

public class Main {
    final static int MAX = 25;

    private static void createAndShowMainGUI(ArrayList<String> board) {
        //Create and set up the window.
        MainBoard main = new MainBoard("Main Board",board);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        main.addComponentsToPane(main.getContentPane());
        //Display the window.
        main.pack();
        main.setVisible(true);
    }

    private static void createAndShowMapGUI(String name, ArrayList<Integer> green, ArrayList<Integer> black) {
        //Create and set up the window.
        MapBoard map = new MapBoard(name,green, black);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        map.addComponentsToPane(map.getContentPane());
        //Display the window.
        map.pack();
        map.setVisible(true);
    }

    public static void main(String[] args) {

        String[] words = {"aquavit", "abbreviation", "analogy", "actress", "area", "anxiety", "amnesty", "airport", "autograph", "array",
                "bloom", "brainwashing", "bootie", "brook", "back", "bard", "baggie", "brokerage", "bunch", "binary",
                "crew", "creation", "citizen", "cocktail", "collusion", "criterion", "control", "cargo", "cricketer", "copying",
                "digital", "dough", "disadvantage", "die", "download", "drummer", "dredger", "drop", "deacon", "della",
                "elicit", "ext", "espalier", "execution", "ethernet", "electron", "exhibition", "emphasis", "excursion", "eighty",
                "fare", "footage", "flask", "foundation", "filmmaker", "flutter", "firm", "flunky", "friday", "fin",
                "groove", "grand", "gland", "glory", "greed", "giant", "gender", "guacamole", "gather", "gaiters",
                "hitch", "humanity", "handle", "hideout", "harmonize", "half", "hickey", "hook", "hallway", "house-elf",
                "insecurity", "ice", "incidence", "integral", "inquiry", "infrastructure", "ingredient", "initiative", "ideologue", "intern",
                "jersey", "judo", "jewel", "judgement", "jump", "jellybeans", "judge", "jury", "jordanian", "juice",
                "kosa", "kenya", "knife", "knock", "khaki", "kern", "kiss", "kindergarten", "kick", "karma",
                "lathe", "log", "legend", "luncheon", "leave", "laura", "label", "lighthouse", "lily", "loafer",
                "mutton", "miserliness", "mid-february", "maternity", "monday", "moody", "mirror", "misogyny", "meatball", "madam",
                "nightclub", "newsletter", "nightstand", "nationalism", "needlework", "nit", "need", "non-locals", "necklace", "national",
                "organising", "opinion", "opposite", "optimism", "obesity", "organisation", "optimal", "octopus", "organiser", "olive",
                "pastry", "parrot", "playground", "privacy", "physics", "patina", "prescription", "price", "pearl", "pasture",
                "quite", "questionnaire", "quality", "quantum", "quill", "question", "quicksand", "queen", "quest", "qualification",
                "romania", "reach", "reset", "reservation", "rise", "referee", "revolution", "racism", "radiosonde", "riddle",
                "soul", "shower", "swiss", "sunrise", "sheath", "starter", "stain", "shampoo", "shears", "screamer",
                "translator", "texture", "temporary", "tonality", "tennis", "travelling", "tenement", "teammate", "triplet", "triviality",
                "unwillingness", "upper", "underwear", "use", "undergraduate", "underwire", "undertaker", "user", "unshielded", "USB",
                "visa", "vitamin", "villain", "vogue", "ventilation", "viewpoint", "vulgarity", "vietnam", "vaulting", "vendor",
                "world", "will", "winner", "wile", "whip", "wand", "while", "word", "wombat", "white",
                "xylophone", "x-ray", "xbox", "x men", "xerographic",
                "yurt", "yawn", "yahoo", "yarmulke", "yard", "yesterday", "yew", "yugoslavian", "yogurt", "yak",
                "zenith", "zodiac", "zone", "zebra", "ziploc", "zeitgeist", "zucchini", "zero"};

        // Randomizing the list of words
        ArrayList<String> board = new ArrayList<>();
        Utility utility = new Utility();
        ArrayList<Integer> list = new ArrayList<>();
        list = utility.getRandomNonRepeatingIntegers(MAX, 0, 125);

        for (int i : list)
            board.add(words[i]);

        // For test purpose
        for (int i = 0; i < board.size(); i++) {
            System.out.print(" " + board.get(i));
            if (i >= 4 && (i +1)% 5 == 0) {
                System.out.println();
            }
        }

        // Randomizing the map
        Map player1 = new Map();
        Map player2 = new Map();
        ArrayList<Integer> mapList = new ArrayList<>();
        int i = 0;
        while(i<MAX){
            mapList.add(i);
            i++;
        }
        Collections.shuffle(mapList);

        //1. random 1 matched black
        player1.setMatchBlack(mapList.get(0));
        player2.setMatchBlack(mapList.get(0));
        mapList.remove(0);

        //2. random 2 reversed green black
        player1.setMatchBtoG(mapList.get(0));
        player2.setMatchGtoB(mapList.get(0));
        mapList.remove(0);
        player1.setMatchGtoB(mapList.get(0));
        player2.setMatchBtoG(mapList.get(0));
        mapList.remove(0);

        //3. random 1 unique black
        player1.setBlack(mapList.get(0));
        mapList.remove(0);
        player2.setBlack(mapList.get(0));
        mapList.remove(0);

        //4. random 3 matched green
        player1.setMatchGreen(new ArrayList(mapList.subList(0, 3)));
        player2.setMatchGreen(new ArrayList( mapList.subList(0, 3)));
        mapList.removeAll(new ArrayList(mapList.subList(0, 3)));

        //5. random 5 unique green for each
        player1.setGreen(new ArrayList(mapList.subList(0, 5)));
        mapList.removeAll(new ArrayList(mapList.subList(0, 5)));
        player2.setGreen(new ArrayList(mapList.subList(0, 5)));
        mapList.removeAll(new ArrayList(mapList.subList(0, 5)));

        //6. final map
        System.out.println("player1 Green:");
        System.out.println(player1.getAllGreen());
        System.out.println("player2 Green:");
        System.out.println(player2.getAllGreen());
        System.out.println("player1 Black:");
        System.out.println(player1.getAllBlack());
        System.out.println("player2 Black:");
        System.out.println(player2.getAllBlack());


        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowMapGUI("Player 1",player1.getAllGreen(),player1.getAllBlack());
                createAndShowMapGUI("Player 2",player2.getAllGreen(),player2.getAllBlack());
                createAndShowMainGUI(board);
            }
        });
    }
}
