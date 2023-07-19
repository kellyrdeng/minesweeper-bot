package minesweeper;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import static minesweeper.Grid.MINE;
import static minesweeper.Grid.FLAG;
import static minesweeper.Grid.BLANK;
import static minesweeper.Grid.OFFSET;

public class Mechanics {
    private Grid grid;

    public Mechanics(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return this.grid;
    }

    //return 0 on success, -1 if mine hit, -2 if out of bounds
    public int click(int row, int column) {
        //check if out of bounds
        if (row < 0 || column < 0 || row >= grid.getAnswerGrid().length || column >= grid.getAnswerGrid().length) {
            return -2;
        }

        int cellValue = grid.getAnswerGrid()[row][column];

        switch (cellValue) {
            case MINE:
                grid.setUserGridCell(row, column, MINE);
                return -1;

            case 0:
                zeroCellBFS(row, column, grid);
                return 0;

            case FLAG:
                grid.setUserGridCell(row, column, BLANK);
                return 0;

            default: //1, 2, 3... minecount
                if (grid.getUserGrid()[row][column] == cellValue) { //already been clicked/revealed
                    return chord(row, column);
                } else { 
                    grid.setUserGridCell(row, column, cellValue); //reveal minecount
                    grid.decrementBlanks();
                    return 0;
                }
        }
    }

    //0 on success, -2 if out of bounds
    public int flag(int row, int column) {
        //check if out of bounds
        if (row < 0 || column < 0 || row >= grid.getAnswerGrid().length || column >= grid.getAnswerGrid().length) {
            return -2;
        }

        int cellValue = grid.getAnswerGrid()[row][column];

        switch (cellValue) {
            case FLAG:
                grid.setUserGridCell(row, column, BLANK);
                return 0;
            
            case MINE: //same as blank
            
            case BLANK:
                grid.setUserGridCell(row, column, FLAG);
                return 0;

            default: //minecount already revealed
                return 0;
        }
    }

    //when clicking a cell whose minecount has already been revealed, you can:
    //a) reveal minecounts of neighboring cells if all neighboring mines have already been found
    //b) do nothing (maybe flash cells in future implementation) if not all mines have been found yet

    //return 0 on success, -1 if failed chord (incorrect flag) ends the game
    public int chord(int i, int j) {
        int minecount = grid.getAnswerGrid()[i][j];
        int[][] userGrid = grid.getUserGrid();
        int flags = grid.countMinesOrFlags(i, j, userGrid, FLAG);

        if (flags != minecount) { //mines not all found so can't chord (do nothing)
            return 0;
        }

        //mines all found, click all other neighbor cells (it will reveal nonzeros, do bfs for 0s)
        for (int[] neighbor : OFFSET) {
            int row = i + neighbor[0];
            int column = j + neighbor[1];

            if (row < 0 || column < 0 || row >= userGrid.length || column >= userGrid.length) { //neighbor is out of boundaries
                continue;
            }

            if (userGrid[row][column] == BLANK) {
                int c = click(row, column);
                if (c == -1) {
                    return c;
                }
            }
        }
        return 0;
    }

    //if a 0 cell is clicked, it reveals adjacent 0s and then one more layer of cells adjacent to those 0s
    public void zeroCellBFS(int i, int j, Grid grid) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        HashSet<Integer> visited = new HashSet<Integer>();
        int[][] answerGrid = grid.getAnswerGrid();

        queue.add(new int[]{i, j});
        visited.add(i * grid.getSize() + j); //unique key to store in HashSet

        while (queue.size() > 0) {
            int[] popped = queue.poll();
            int minecount = answerGrid[popped[0]][popped[1]];
            grid.setUserGridCell(popped[0], popped[1], minecount); //reveal cell

            if (minecount == 0) { //if 0, add neighbors if in bounds and not already visited
                for (int[] point : OFFSET) {
                    int row = popped[0] + point[0];
                    int column = popped[1] + point[1];

                    if (row < 0 || column < 0 || row >= answerGrid.length || column >= answerGrid.length) { //neighbor is out of boundaries
                        continue;
                    }

                    int[] neighbor = new int[]{row, column};
                    int neighborInt = neighbor[0] * grid.getSize() + neighbor[1];

                    if (!visited.contains(neighborInt)) { //add neighbor to queue if not yet visited
                        queue.add(neighbor);
                        visited.add(neighborInt);
                    }
                }
            }
        }
        grid.setBlanks(grid.getBlanks() - visited.size()); //decrement all the cells we revealed
    }
}
