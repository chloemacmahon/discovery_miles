package gameboard;

import java.util.List;

public class GameBoard {
    private List<List<GameTile>> gameBoard;
    private int amountOfRows;
    private int getAmountOfColumns;
    private int tilesRevealed;

    public GameBoard(List<List<GameTile>> gameBoard, int amountOfRows, int getAmountOfColumns, int tilesRevealed) {
        this.gameBoard = gameBoard;
        this.amountOfRows = amountOfRows;
        this.getAmountOfColumns = getAmountOfColumns;
        this.tilesRevealed = tilesRevealed;
    }

    public int revealTile(int rowNumber, int columnNumber){
        if
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

    public int getGetAmountOfColumns() {
        return getAmountOfColumns;
    }

    public void setGetAmountOfColumns(int getAmountOfColumns) {
        this.getAmountOfColumns = getAmountOfColumns;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public void setAmountOfRows(int amountOfRows) {
        this.amountOfRows = amountOfRows;
    }
}
