package za.ac.nwu.ac.domain.dto.gameboard;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Random;

/**
 * Represents a game tile in the za.ac.nwu.ac.domain.dto.gameboard
 */
@Data
@Entity
@Component
public class GameTile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long gameTileID;

    @Column(name = "miles_value")
    private int milesValue;

    @Column(name = "revealed")
    private boolean revealed;

    @Column(name = "row")
    private int row;

    @Column(name = "column")
    private int column;

    @JoinColumn(referencedColumnName =  "za.ac.nwu.ac.domain.dto.member.member_id",name = "game_tile.member_id")
    @ManyToMany
    private Long memberId;

    private static int maxMilesValue = 600;

    public GameTile() {
    }

    /**
     * Receives the miles that the tile represents and a boolean value if the tile has been revealed
     *
     * @param milesValue the miles that the tile represents
     * @param revealed   a boolean value that shows if the tile is revealed
     * @param row row number of the tile
     * @param column column number of the tile
     * @param memberId id number of the za.ac.nwu.ac.domain.dto.member
     */

    public GameTile(int milesValue, boolean revealed, int row, int column, long memberId) {
        this.milesValue = milesValue;
        this.revealed = revealed;
        this.row = row;
        this.column = column;
        this.memberId = memberId;
    }

    /**
     * Static method that creates a game tile
     *
     * @return a game tile
     */

    public static GameTile createTile(int row, int column, Long memberId) {
        Random mileGenerator = new Random();
        return new GameTile(mileGenerator.nextInt(maxMilesValue), false, row, column, memberId);
    }

}
