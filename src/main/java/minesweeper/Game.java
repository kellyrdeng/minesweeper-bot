package minesweeper;

public class Game {
    private Grid grid;

    public Game(String difficulty) {
        this.grid = new Grid(difficulty);
    }

    //return 0 on success, -1 if mine hit
    public int Click(int row, int column) {
        char cellValue = grid.getAnswerGrid()[row][column];
        if (cellValue == '*') {
            return -1;
        } else if (cellValue == 0) {
            //bfs to reveal all neighboring 0 cells
            return 0;
        } //cell value of 1, 2, 3...
        grid.setUserGridCell(row, column, cellValue);
        return 0;
    }
}
