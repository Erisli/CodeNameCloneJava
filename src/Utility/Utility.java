package Utility;

import Players.Player;
import View.MainBoard;
import View.MapBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Utility {
    final static int MAXWORDS = 25;
    final static int MAXGAP = 20;
    final String[] wordsBank = {"aquavit", "abbreviation", "analogy", "actress", "area", "anxiety", "amnesty", "airport", "autograph", "array",
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

    public ArrayList getRandomNonRepeatingIntegers(int size, int min,
                                                   int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

    public ArrayList<String> randomizeBoard() {
        // Randomizing the list of words
        ArrayList<String> words = new ArrayList<>();
        Utility utility = new Utility();
        ArrayList<Integer> list = new ArrayList<>();
        list = utility.getRandomNonRepeatingIntegers(MAXWORDS, 0, wordsBank.length - 1);

        for (int i : list)
            words.add(wordsBank[i]);

        return words;

    }

    public ArrayList<Player> randomizeMap() {
        ArrayList<Player> ret = new ArrayList<>();

        // Randomizing the map
        Player player1 = new Player();
        Player player2 = new Player();
        ArrayList<Integer> mapList = new ArrayList<>();

        for (int i = 0; i < MAXWORDS; i++) {
            mapList.add(i);
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
        player2.setMatchGreen(new ArrayList(mapList.subList(0, 3)));
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

        ret.add(player1);
        ret.add(player2);

        return ret;
    }

    public void createAndShowMainGUI() {
        //Create and set up the window.
        MainBoard main = new MainBoard("Main Board");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        main.addComponentsToPane(main.getContentPane());
        //Display the window.
        main.pack();
        main.setVisible(true);
    }

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

    public void fakeButton(JPanel jPanel) {
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        b.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        Dimension buttonSize = b.getPreferredSize();
        jPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 5) + MAXGAP,
                (int) (buttonSize.getHeight() * 5) + MAXGAP * 2));
    }

}
