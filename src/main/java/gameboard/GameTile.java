package gameboard;

public class GameTile {
    private int milesValue;
    private boolean revealed;

    public GameTile(int milesValue, boolean revealed) {
        this.milesValue = milesValue;
        this.revealed = revealed;
    }

    public int getMilesValue() {
        return milesValue;
    }

    public void setMilesValue(int milesValue) {
        this.milesValue = milesValue;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
}
