package za.ac.nwu.ac.domain.dto.gameboard;

import za.ac.nwu.ac.domain.exception.InvalidGameTileException;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//When next board generated is smaller you should delete all tiles
/**
 * Represents a game board made out of <code>GameTile</code>
 */
@Data
@Entity
@Table (name = "game_board")
@Component
public class GameBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "game_board_id")
    private Long gameBoardId;

    @OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<GameTile> gameBoard;

    //List<List<GameTile>>

    private int amountOfRows;

    private int amountOfColumns;

    private int tilesRevealed;

    /**
     * Default constructor, row and column amount is set to 5
     */
    public GameBoard() {
        this.amountOfRows = 5;
        this.amountOfColumns = 5;
        createGameBoard(amountOfRows, amountOfColumns);
        setTilesRevealed(0);
    }

    /**
     * Constructor that creates a board of a set length
     * @param amountOfRows amount of rows that the game board should have
     * @param amountOfColumns amount of columns that the game board should have
     */

    public GameBoard(int amountOfRows, int amountOfColumns) {
        createGameBoard(amountOfRows, amountOfColumns);
        this.amountOfRows = amountOfRows;
        this.amountOfColumns = amountOfColumns;
        setTilesRevealed(0);
    }

    /**
     * Constructor that accepts already made game board
     * @param gameBoard za.ac.nwu.ac.domain.dto.member's game board
     * @param amountOfRows rows of game board
     * @param amountOfColumns column of game board
     * @param tilesRevealed the amount of tiles that have been revealed
     */

    public GameBoard(List<GameTile> gameBoard, int amountOfRows, int amountOfColumns, int tilesRevealed) {
        this.amountOfRows = amountOfRows;
        this.amountOfColumns = amountOfColumns;
        if (tilesRevealed == amountOfColumns*amountOfRows){
            createGameBoard(amountOfRows,amountOfColumns);
            setTilesRevealed(0);
        } else{
            this.gameBoard = gameBoard;
            this.tilesRevealed = tilesRevealed;
        }
    }

    /**
     * Creates new game board
     * @param amountOfRows amount of rows game board should contain
     * @param amountOfColumns amount of columns game board should contain
     */

    public void createGameBoard(int amountOfRows, int amountOfColumns) {
        List<GameTile> newGameBoard = new ArrayList<>();
        for (int i = 0; i < amountOfRows; i++) {
            for (int j = 0; j < amountOfColumns; j++) {
                newGameBoard.add(GameTile.createTile(i,j));
            }
        }
        setGameBoard(newGameBoard);
    }

    /**
     * Reveals a game tile
     * If the tile chosen is not available to be revealed an exception is thrown and handled
     * @param rowNumber row number of the tile that should be revealed
     * @param columnNumber column number of the tile that should be revealed
     * @return the mile value of the revealed tile
     * @throws InvalidGameTileException is thrown if the tile can not be revealed
     */
    //JPA attribute converter, JPA lifecycle
    public int revealTile(int rowNumber, int columnNumber) {
        if (rowNumber > amountOfRows || columnNumber > amountOfColumns)
            return -1;
        try {
            List<GameTile> gameTiles = new ArrayList<>();
            if (getGameBoard().get((rowNumber*getAmountOfColumns())+columnNumber).isRevealed())
                throw new InvalidGameTileException("Tile already opened");
            else {
                setTilesRevealed(getTilesRevealed() + 1);
                getGameBoard().get((rowNumber*getAmountOfColumns())+columnNumber).setRevealed(true);
                return getGameBoard().get((rowNumber*getAmountOfColumns())+columnNumber).getMilesValue();
            }
        } catch (InvalidGameTileException invalidGameTileException) {
            return -1;
        }
    }

    /**
     * Displays game board, x is displayed if the tile has not been revealed yet otherwise the mile value is displayed
     */

    public void showGameBoard() {
        List<String> boardToPrint = new ArrayList<>();

        for (int i = 0; i < getAmountOfRows(); i++) {
            String rowInBoard = "";
            for (int j = 0; j < getAmountOfColumns(); j++) {
                GameTile tile = getGameBoard().get(amountOfColumns*i + j);
                System.out.println("i = " + i);
                System.out.println("tile = " + tile);
                if (tile.isRevealed())
                    rowInBoard = String.format("%s %3d", rowInBoard, tile.getMilesValue());
                else
                    rowInBoard = String.format("%s %3c", rowInBoard, 'x');
            }
            boardToPrint.add(rowInBoard);
        }
        for (String row : boardToPrint) {
            System.out.println(row);
        }
    }
}
