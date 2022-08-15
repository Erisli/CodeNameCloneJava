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
    final String[] wordsBank = {"dragon","finger","slide","socks","crayon","bat","mouse","ocean","eye","wheel","beach","curl","lemon","bed","leaf","ghost","bathroom","cheese","cherry","shoe","feather","flag","man","egg","bee","lips","ship","book","line","boat","pig","cookie","pool","grapes","plant","truck","bridge","eyes","chicken","head","skateboard","heart","mountains","ring","helicopter","drum","ear","button","train","nail","doctor","sidewalk","cowboy","sister","package","kite","pipe","video camera","sail","pitchfork","wave","fire hydrant","owl","crow","bomb","marker","zookeeper","stick","hair","wheelbarrow","cockroach","pogo stick","seal","sushi","sprinkler","snowball","airport","birthday","motorcycle","maid","pilot","saltwater","stapler","dig","hunter","pear","t-shirt","pollution","fax","hole","marshmallow","hook","yardstick","twig","soccer","sand","state","anvil","tulip","notebook","devious","blue tooth","cheerleader","concession stand","roommate","charger","clown","front","receipt","stay","zoom","lunar rover","degree","sandbox","best friend","important","deliver","prime meridian","toolbox","ruby","mast","calm","costume","pharaoh","punk","twist","glue stick","ringleader","attack","manatee","mirror","pigpen","drawback","print","commercial","fiddle","bookstore","fiance","cattle","vitamin","recess","last","goblin","diagonal","wrap","elope","pro","violent","oar","swimming","drift","brainstorm","depth","property","friction","nutmeg","tribe","random","insurance","archaeologist","improve","system","danger","debt","risk","pawnshop","error","in-law","password","offstage","blacksmith","silt","inquisition","enemy","zone defense","armada","pelt","VIP","steamboat","addendum","whiplash","forklift","member","good-bye","pomp","dud","loiterer","resourceful","infection","try","promise","semester","default","cartoonist","riddle","tattle","feeling","cause","hobby","coast"};

    public ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < size) {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

    // Randomizing the list of words
    public ArrayList<String> randomizeBoard() {

        ArrayList<String> words = new ArrayList<>();
        Utility utility = new Utility();
        ArrayList<Integer> list = utility.getRandomNonRepeatingIntegers(MAXWORDS, 0, wordsBank.length - 1);

        for (int i : list)
            words.add(wordsBank[i]);
        return words;
    }

    // Randomizing the map
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
        player1.setMatchGreen(new ArrayList<>(mapList.subList(0, 3)));
        player2.setMatchGreen(new ArrayList<>(mapList.subList(0, 3)));
        mapList.removeAll(new ArrayList<>(mapList.subList(0, 3)));

        //5. random 5 unique green for each
        player1.setGreen(new ArrayList<>(mapList.subList(0, 5)));
        mapList.removeAll(new ArrayList<>(mapList.subList(0, 5)));
        player2.setGreen(new ArrayList<>(mapList.subList(0, 5)));
        mapList.removeAll(new ArrayList<>(mapList.subList(0, 5)));

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
        MainBoard main = new MainBoard("Main Board");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.addComponentsToPane(main.getContentPane());
        //Display the window.
        main.pack();
        main.setVisible(true);
    }

    public void createAndShowMapGUI(String name, ArrayList<Player> players) {
        MapBoard map = new MapBoard(name, players);
        map.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
