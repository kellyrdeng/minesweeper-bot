package minesweeper;

public class Game {
    private Grid grid;
    static final int MINE = -1;
    static final int FLAG = -2;
    static final int BLANK = -3;
    static final int[][] OFFSET = {{-1, -1}, {-1, 0}, {-1, 1},
                                   { 0, -1},          { 0, 1},
                                   { 1, -1}, { 1, 0}, { 1, 1}};

    public Game(String difficulty) {
        this.grid = new Grid(difficulty);
    }

    public Grid getGrid() {
        return this.grid;
    }

    //return 0 on success, -1 if mine hit
    public int click(int row, int column) {
        int cellValue = grid.getAnswerGrid()[row][column];

        if (cellValue == MINE) {
            return -1;
        } else if (cellValue == BLANK) {
            //bfs to reveal all neighboring 0 cells
            return 0;
        }
        //cell value of 1, 2, 3...
        grid.setUserGridCell(row, column, cellValue);
        return 0;
    }
}
