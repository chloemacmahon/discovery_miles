package gameboard;

import java.util.Random;

public class GameTile {
    private int milesValue;
    private boolean revealed;
    private static int maxMilesValue = 600;

    public GameTile(int milesValue, boolean revealed) {
        this.milesValue = milesValue;
        this.revealed = revealed;
    }

    public static GameTile createTile(){
        Random mileGenerator = new Random();
        return new GameTile(mileGenerator.nextInt(maxMilesValue), false);
    }

    public static int getMaxMilesValue() {
        return maxMilesValue;
    }

    public static void setMaxMilesValue(int maxMilesValue) {
        GameTile.maxMilesValue = maxMilesValue;
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
