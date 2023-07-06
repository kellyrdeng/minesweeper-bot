package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.awt.Point;

//this class generates a minesweeper grid based on difficulty
//it creates an answer grid with the mines and minecount for each cell
//it also creates a blank user grid for the user to interact with and reveal cells from the answer board
public class Grid {
    static final int MINE = -1;
    static final int FLAG = -2;
    static final int[][] offset = {{-1, -1}, {-1, 0}, {-1, 1},
                                   {0, -1},           {0, 1},
                                   {1, -1},  {1, 0},  {1, 1}};
    private int[][] answerGrid;
    private int[][] userGrid;

    public Grid(String difficulty) { //generates the answer and user grids cased on difficulty passed in
        int size;
        int bombs;
        switch (difficulty) {
            case "beginner":
                size = 9;
                bombs = 10;
                break;
            case "intermediate":
                size = 16;
                bombs = 40;
                break;
            case "expert":
                size = 24;
                bombs = 99;
                break;
            default:
                size = 9;
                bombs = 10;
        }

        this.answerGrid = new int[size][size];
        this.userGrid = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(this.answerGrid[i], 0);
            Arrays.fill(this.userGrid[i], 0);
        }

        generateBombs(answerGrid, bombs);
        fillMinecount(answerGrid);
    }

    public int[][] getAnswerGrid() {
        return this.answerGrid;
    }

    public int[][] getUserGrid() {
        return this.userGrid;
    }

    public void setUserGridCell(int row, int column, int value) {
        this.userGrid[row][column] = value;
    }

    //prints out grid along with row/column indexes for easier cell identification
    public void printGrid(int[][] grid) {
        System.out.print("   ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        System.out.print("\n");
        
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == MINE) {
                    System.out.print("* ");
                } else if (grid[i][j] == FLAG) {
                    System.out.print("F ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    //use random number generator to select random indexes to place bombs
    public void generateBombs(int[][] answerGrid, int bombs) throws RuntimeException {
        int totalCells = (int)Math.pow(answerGrid.length, 2); //81 for b
        if (bombs > totalCells) {
            throw new RuntimeException("Number of mines exceeds number of cells");
        }

        Random rand = new Random();
        Set<Integer> seenBefore = new HashSet<>();

        //randomly place bombs
        while (seenBefore.size() < bombs) {
            int i = rand.nextInt(totalCells);

            if (!seenBefore.contains(i)) { //if i hasn't been seen before, add to hashset and continue
                seenBefore.add(i);
                int row = i / answerGrid.length;
                int column = i % answerGrid.length;
                answerGrid[row][column] = -1;
            }
        }
    }

    //use bomb placement to fill answer grid cells with the number of bombs they are adjacent to
    public void fillMinecount(int[][] answerGrid) {
        for (int i = 0; i < answerGrid.length; i ++) {
            for (int j = 0; j < answerGrid.length; j++) {
                if (answerGrid[i][j] == -1) {
                    continue;
                }
                answerGrid[i][j] = countMines(i, j, answerGrid);
            }
        }
    }

    //counts the number of immediate neighbors with mines for the point (i, j) using the offset const
    public int countMines(int i, int j, int[][] answerGrid) {
        int minecount = 0;
        for (int[] point : offset) {
            int row = i + point[0];
            int column = j + point[1];

            if (row < 0 || column < 0 || row >= answerGrid.length || column >= answerGrid.length) { //neighbor is out of boundaries
                continue;
            }
            minecount++;
        }
        return minecount;
    }
}