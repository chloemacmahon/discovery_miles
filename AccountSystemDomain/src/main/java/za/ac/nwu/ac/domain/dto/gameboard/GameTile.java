package za.ac.nwu.ac.domain.dto.gameboard;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Random;

/**
 * Represents a game tile in the za.ac.nwu.ac.domain.dto.gameboard
 */
@Data
@Table (name = "game_tile")
@Entity
@Component
public class GameTile {

    //Composite Key constraint
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long gameTileID;

    @Column(name = "miles_value")
    private int milesValue;

    @Column(name = "revealed")
    private boolean revealed;

    private int rowNumber;

    private int columnNumber;

    private static int maxMilesValue = 600;

    public GameTile() {
    }

    /**
     * Receives the miles that the tile represents and a boolean value if the tile has been revealed
     *
     * @param milesValue the miles that the tile represents
     * @param revealed   a boolean value that shows if the tile is revealed
     * @param rowNumber row number of the tile
     * @param columnNumber column number of the tile
     */

    public GameTile(int milesValue, boolean revealed, int rowNumber, int columnNumber) {
        this.milesValue = milesValue;
        this.revealed = revealed;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Static method that creates a game tile
     *
     * @return a game tile
     */

    public static GameTile createTile(int row, int column) {
        Random mileGenerator = new Random();
        return new GameTile(mileGenerator.nextInt(maxMilesValue), false, row, column);
    }

}
