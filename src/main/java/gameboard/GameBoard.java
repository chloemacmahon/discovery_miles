package gameboard;

import java.util.List;

public class GameBoard {
    private List<List<GameTile>> gameBoard;
    private int tilesRevealed;

    public GameBoard(List<List<GameTile>> gameBoard, int tilesRevealed) {
        this.gameBoard = gameBoard;
        this.tilesRevealed = tilesRevealed;
    }

    public int revealTile(int rowNumber, int columnNumber){

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
}
