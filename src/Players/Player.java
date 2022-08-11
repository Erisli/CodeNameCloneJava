package Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    //special
    private List<Integer> matchGreen;   //size of 3;
    private int matchBlack;
    private int matchGtoB;
    private int matchBtoG;

    //unique
    private List<Integer> green;    //size of 5
    private int black;

    public ArrayList<Integer> getAllGreen() {
        ArrayList<Integer> ret = (ArrayList<Integer>) matchGreen;
        ret.addAll(green);
        ret.add(matchGtoB);
        Collections.sort(ret);
        return ret;
    }

    public ArrayList<Integer> getAllBlack(){
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(matchBtoG);
        ret.add(black);
        ret.add(matchBlack);
        Collections.sort(ret);
        return ret;
    }

    public List<Integer> getMatchGreen() {
        return matchGreen;
    }

    public void setMatchGreen(ArrayList<Integer> matchGreen) {
        this.matchGreen = matchGreen;
    }

    public int getMatchBlack() {
        return matchBlack;
    }

    public void setMatchBlack(int matchBlack) {
        this.matchBlack = matchBlack;
    }

    public int getMatchGtoB() {
        return matchGtoB;
    }

    public void setMatchGtoB(int matchGtoB) {
        this.matchGtoB = matchGtoB;
    }

    public int getMatchBtoG() {
        return matchBtoG;
    }

    public void setMatchBtoG(int matchBtoG) {
        this.matchBtoG = matchBtoG;
    }

    public void setGreen(ArrayList<Integer> green) {
        this.green = green;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    public List<Integer> getGreen() {
        return this.green;
    }

    public int getBlack() {
        return this.black;
    }

    @Override
    public String toString() {
        return "Green: " + getAllGreen() + "/nBlack: " + getAllBlack();
    }
}
