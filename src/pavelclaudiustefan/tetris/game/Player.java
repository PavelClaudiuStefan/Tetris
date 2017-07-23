package pavelclaudiustefan.tetris.game;

public class Player {

    // TODO - Work in progress

    private String nume;
    private int topScore;
    private int highestNumberOfCompletedLines;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public int getHighestNumberOfCompletedLines() {
        return highestNumberOfCompletedLines;
    }

    public void setHighestNumberOfCompletedLines(int highestNumberOfCompletedLines) {
        this.highestNumberOfCompletedLines = highestNumberOfCompletedLines;
    }
}
