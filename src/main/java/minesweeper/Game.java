package minesweeper;

import java.util.List;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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

        switch (cellValue) {
            case MINE:
                grid.setUserGridCell(row, column, MINE);
                return -1;

            case 0:
                blankCellBFS(row, column, grid);
                return 0;

            case FLAG:
                grid.setUserGridCell(row, column, BLANK);
                return 0;

            default: //1, 2, 3...
                if (grid.getUserGrid()[row][column] == cellValue) { //already been clicked/revealed
                    chord(row, column, cellValue);
                } else {
                    grid.setUserGridCell(row, column, cellValue);
                }
                return 0;
        }
    }

    //when clicking a cell whose minecount has already been revealed, you can:
    //a) reveal minecounts of neighboring cells if all neighboring mines have already been found
    //b) do nothing (maybe flash cells in future implementation) if not all mines have been found yet
    public void chord(int row, int column, int minecount) {
        int foundMines = grid.countMines(row, column, grid.getUserGrid());

        if (minecount != foundMines) { //mines not all found so can't chord (do nothing)
            return;
        }
        //mines all found, reveal all other cells
        for (int[] neighbor : OFFSET) {
            if (grid.getUserGrid()[neighbor[0]][neighbor[1]] == BLANK) {
                
            }
        }
    }

    //if a 0 is clicked, it reveals all adjacent 0s
    public void blankCellBFS(int i, int j, Grid grid) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        HashSet<String> visited = new HashSet<String>();
        int[][] answerGrid = grid.getAnswerGrid();

        queue.add(new int[]{i, j});

        while (queue.size() > 0) {
            //pop top cell and visit (check if 0 in answerGrid, if yes, reveal on userGrid), mark as visited
            int[] curCell = queue.poll();
            String coordStr = curCell[0] + "," + curCell[1]; //unique key to store in HashSet
            visited.add(coordStr);

            if (answerGrid[curCell[0]][curCell[1]] == 0) { //if 0, set userGrid and add neighbors
                grid.setUserGridCell(curCell[0], curCell[1], 0);

                //for each 0, add its neighbors (if in bounds, not already in queue, and they haven't already been visited)
                for (int[] point : OFFSET) {
                    int row = i + point[0];
                    int column = j + point[1];

                    if (row < 0 || column < 0 || row >= answerGrid.length || column >= answerGrid.length) { //neighbor is out of boundaries
                        continue;
                    }

                    int[] neighbor = new int[]{row, column};
                    String neighborStr = row + "," + column;
                    if (answerGrid[row][column] == 0 && !queue.contains(neighbor) && !visited.contains(neighborStr)) {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game("beginner");

        int[][] answerGrid = {{ 1, 1, 0, 0},
                              {-1, 1, 0, 0},
                              { 2, 3, 2, 1},
                              { 1,-1,-1, 1}};
        game.getGrid().setAnswerGrid(answerGrid);

        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        game.getGrid().setUserGrid(userGrid);

        int c1 = game.click(0, 2);
        game.getGrid().printGrid(userGrid);
    }
}
