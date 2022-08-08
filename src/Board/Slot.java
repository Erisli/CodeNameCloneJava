package Board;

public class Slot {
    String word;
    Color color;

    public Slot(String word, Color color) {
        this.word = word;
        this.color = color;
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
}
