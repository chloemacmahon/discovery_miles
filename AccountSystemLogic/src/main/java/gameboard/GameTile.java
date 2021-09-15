package gameboard;

import java.util.Random;

/**
 * Represents a game tile in the gameboard
 */

public class GameTile {
    private int milesValue;
    private boolean revealed;
    private static int maxMilesValue = 600;

    /**
     * Receives the miles that the tile represents and a boolean value if the tile has been revealed
     * @param milesValue the miles that the tile represents
     * @param revealed a boolean value that shows if the tile is revealed
     */

    public GameTile(int milesValue, boolean revealed) {
        this.milesValue = milesValue;
        this.revealed = revealed;
    }

    /**
     * Static method that creates a game tile
     * @return a game tile
     */

    public static GameTile createTile() {
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
