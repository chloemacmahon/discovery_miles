package gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<List<GameTile>> gameBoard;
    private int amountOfRows;
    private int amountOfColumns;
    private int tilesRevealed;

    public GameBoard(){
        this.amountOfRows = 5;
        this.amountOfColumns = 5;
        createGameBoard(amountOfRows,amountOfColumns);
        setTilesRevealed(0);
    }

    //Creates a gameboard of a set length
    public GameBoard( int amountOfRows, int amountOfColumns){
        createGameBoard(amountOfRows,amountOfColumns);
        this.amountOfRows = amountOfRows;
        this.amountOfColumns = amountOfColumns;
        setTilesRevealed(0);
    }

    //Accepts an already made gameboard
    public GameBoard(List<List<GameTile>> gameBoard, int amountOfRows, int amountOfColumns,  int tilesRevealed) {
        this.gameBoard = gameBoard;
        this.amountOfRows = amountOfRows;
        this.amountOfColumns = amountOfColumns;
        this.tilesRevealed = tilesRevealed;
    }

    private void createGameBoard(int amountOfRows, int amountOfColumns){
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

    //-1 indicates an error has occurred
    public int revealTile(int rowNumber, int columnNumber){
        try{
            if(gameBoard.get(rowNumber).get(columnNumber).isRevealed())
                throw new Exception("Tile already opened");
            else {
                setTilesRevealed(getTilesRevealed()+1);
                gameBoard.get(rowNumber).get(columnNumber).setRevealed(true);
                return gameBoard.get(rowNumber).get(columnNumber).getMilesValue();
            }
        } catch (Exception e){
            return -1;
        }
    }

    public void showGameBoard(){
        List<String> boardToPrint = new ArrayList<>();
        for (List<GameTile> row: getGameBoard()) {
            String rowInBoard = "";
            for (GameTile tile : row) {
                if (tile.isRevealed())
                    rowInBoard = String.format("%s %3d",rowInBoard,tile.getMilesValue());
                else
                    rowInBoard = String.format("%s %3c",rowInBoard,'x');
            }
            boardToPrint.add(rowInBoard);
        }
        for (String row : boardToPrint){
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
