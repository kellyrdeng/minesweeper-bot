package minesweeper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//this class generates a minesweeper grid based on difficulty
//it creates an answer grid with the mines and minecount for each cell
//it also creates a blank user grid for the user to interact with and reveal cells from the answer board
public class Grid {
    static final int MINE = -1;
    static final int FLAG = -2;
    static final int BLANK = -3;
    static final int[][] OFFSET = {{-1, -1}, {-1, 0}, {-1, 1},
                                   {0, -1},           {0, 1},
                                   {1, -1},  {1, 0},  {1, 1}};
    private int[][] answerGrid;
    private int[][] userGrid;
    private String difficulty;

    public Grid(String difficulty) { //generates the answer and user grids cased on difficulty passed in
        this.difficulty = difficulty;
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
            Arrays.fill(this.userGrid[i], -3);
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
    
    public String getDifficulty() {
        return this.difficulty;
    }
    
    public void setUserGridCell(int row, int column, int value) {
        this.userGrid[row][column] = value;
    }

    public void setAnswerGrid(int[][] answerGrid) {
        this.answerGrid = answerGrid;
    }

    public void setUserGrid(int[][] userGrid) {
        this.userGrid = userGrid;
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
            for (int j = 0; j < grid.length; j++) { //maybe swap this to a switch statement for readability
                int cell = grid[i][j];
                if (cell == MINE) {
                    System.out.print("* ");
                } else if (cell == FLAG) {
                    System.out.print("F ");
                } else if (cell == BLANK) {
                    System.out.print("- ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.print("\n");
        }
    }

    //determines if the contents of 2 grids are the same, used for testing
    public boolean sameGrids(int[][] grid1, int[][] grid2) {
        if (grid1.length != grid2.length) {
            return false;
        }
        
        for (int i = 0; i < grid1.length; i++) {
            if (grid1[i].length != grid2[i].length) {
                return false;
            }
        }

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1.length; j++) {
                if (grid1[i][j] != grid2[i][j]) {
                    return false;
                }
            }
        }
        return true;
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
                answerGrid[i][j] = countMinesOrFlags(i, j, answerGrid, -1);
            }
        }
    }

    //counts the number of immediate neighbors with mines OR flags (depending on the toCount var passed in)
    //for the point (i, j) using the offset const
    public int countMinesOrFlags(int i, int j, int[][] grid, int toCount) {
        int minecount = 0;

        for (int[] point : OFFSET) {
            int row = i + point[0];
            int column = j + point[1];

            if (row < 0 || column < 0 || row >= grid.length || column >= grid.length) { //neighbor is out of boundaries
                continue;
            }

            if (grid[row][column] == toCount) {
                minecount++;
            }
        }
        return minecount;
    }

    public static void main(String[] args) {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0},
                               {0, 0, 0},
                               {0, 0, 1}};
        System.out.println(beginner.countMinesOrFlags(5, 5, answerGrid, -1));
        //not sure whats wrong here but every time i call countMinesOrFlags in main or in a test
        //the inputs automatically become 0 and 0 no matter what parameters i give it
        //it works fine when fillMinecount() calls it though
    }
}