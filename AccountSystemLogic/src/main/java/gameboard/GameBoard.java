package gameboard;

import helper_classes.exception.InvalidGameTileException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game board made out of <code>GameTile</code>
 */
public class GameBoard {
    private List<List<GameTile>> gameBoard;
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
     * @param gameBoard member's game board
     * @param amountOfRows rows of game board
     * @param amountOfColumns column of game board
     * @param tilesRevealed the amount of tiles that have been revealed
     */

    public GameBoard(List<List<GameTile>> gameBoard, int amountOfRows, int amountOfColumns, int tilesRevealed) {
        this.gameBoard = gameBoard;
        this.amountOfRows = amountOfRows;
        this.amountOfColumns = amountOfColumns;
        this.tilesRevealed = tilesRevealed;
    }

    /**
     * Constructor that accepts already made game board
     * @param gameBoard member's game board
     * @param tilesRevealed the amount of tiles that have been revealed
     */

    public GameBoard(List<List<GameTile>> gameBoard, int tilesRevealed) {
        this.gameBoard = gameBoard;
        this.amountOfRows = gameBoard.size();
        this.amountOfColumns = gameBoard.get(0).size();
        this.tilesRevealed = tilesRevealed;
    }

    /**
     * Creates new game board
     * @param amountOfRows amount of rows game board should contain
     * @param amountOfColumns amount of columns game board should contain
     */

    private void createGameBoard(int amountOfRows, int amountOfColumns) {
        List<List<GameTile>> newGameBoard = new ArrayList<>(amountOfRows);
        for (int i = 0; i < amountOfRows; i++) {
            List<GameTile> gameRow = new ArrayList<>(amountOfColumns);
            for (int j = 0; j < amountOfColumns; j++) {
                gameRow.add(GameTile.createTile());
            }
            newGameBoard.add(gameRow);
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

    public int revealTile(int rowNumber, int columnNumber) {
        try {
            if (gameBoard.get(rowNumber).get(columnNumber).isRevealed())
                throw new InvalidGameTileException("Tile already opened");
            else {
                setTilesRevealed(getTilesRevealed() + 1);
                gameBoard.get(rowNumber).get(columnNumber).setRevealed(true);
                return gameBoard.get(rowNumber).get(columnNumber).getMilesValue();
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
        for (List<GameTile> row : getGameBoard()) {
            String rowInBoard = "";
            for (GameTile tile : row) {
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

    public List<List<GameTile>> getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(List<List<GameTile>> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getTilesRevealed() {
        return tilesRevealed;
    }

    public void setTilesRevealed(int tilesRevealed) {
        this.tilesRevealed = tilesRevealed;
    }

    public int getAmountOfColumns() {
        return amountOfColumns;
    }

    public void setAmountOfColumns(int amountOfColumns) {
        this.amountOfColumns = amountOfColumns;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public void setAmountOfRows(int amountOfRows) {
        this.amountOfRows = amountOfRows;
    }
}
