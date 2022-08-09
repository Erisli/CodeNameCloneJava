package Board;

public class Slot {
    String word;
    Color color;
    int index;

    int reversed;

    public Slot(String word, Color color, int index) {
        this.word = word;
        this.color = color;
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getReversed() {
        return reversed;
    }

    public void setReversed(int reversed) {
        this.reversed = reversed;
    }
}
