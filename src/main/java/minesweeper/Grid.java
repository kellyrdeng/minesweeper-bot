package minesweeper;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

//this class generates a minesweeper grid based on difficulty
//it creates an answer grid with the mines and minecount for each cell
//it also creates a blank user grid for the user to interact with and reveal cells from the answer board
public class Grid {
    public static final int MINE = -1;
    public static final int FLAG = -2;
    public static final int BLANK = -3;
    public static final int[][] OFFSET = {{-1, -1}, {-1, 0}, {-1, 1},
                                          {0, -1},           {0,  1},
                                          {1, -1},  {1,  0}, {1,  1}};
    private int[][] answerGrid;
    private int[][] userGrid;
    private String difficulty;
    private int blanks; //a blank cell is a cell who's minecount hasn't been revealed (includes flags), game ends when mines == blanks
    int size;
    int mines;

    //  CONSTRUCTOR =============================================================================================

    public Grid(String difficulty) { //generates the answer and user grids cased on difficulty passed in
        this.difficulty = difficulty;
        switch (difficulty) {
            case "beginner":
                size = 9;
                blanks = 81;
                mines = 10;
                break;
            case "intermediate":
                size = 16;
                blanks = 256;
                mines = 40;
                break;
            case "expert":
                size = 24;
                blanks = 576;
                mines = 99;
                break;
            default:
                size = 9;
                blanks = 81;
                mines = 10;
        }

        this.answerGrid = new int[size][size];
        this.userGrid = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(this.answerGrid[i], 0);
            Arrays.fill(this.userGrid[i], -3);
        }

        generateBombs(answerGrid, mines);
        fillMinecount(answerGrid);
    }

    //  GETTERS/SETTERS =============================================================================================

    public int[][] getAnswerGrid() {
        return this.answerGrid;
    }

    public int[][] getUserGrid() {
        return this.userGrid;
    }
    
    public String getDifficulty() {
        return this.difficulty;
    }

    public int getSize() {
        return this.answerGrid.length;
    }

    public int getMines() {
        return this.mines;
    }

    public int getBlanks() {
        return this.blanks;
    }

    public void setBlanks(int blanks) {
        this.blanks = blanks;
    }

    public void decrementBlanks() {
        this.blanks = this.blanks - 1;
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

    public void setGrid(Grid grid) {
        grid = grid;
    }

    //  GENERATE GRIDS =============================================================================================

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

    //  HELPERS FOR GRID GENERATION =============================================================================================

    //prints out grid along with row/column indexes for easier cell identification
    public void printGrid(int[][] grid) {
        System.out.print("\n");
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
        System.out.print("\n");
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

    //  GAMEPLAY MECHANICS =============================================================================================

    //return 0 on success, -1 if mine hit, -2 if out of bounds
    public int click(int row, int column) {
        //check if out of bounds
        if (row < 0 || column < 0 || row >= getAnswerGrid().length || column >= getAnswerGrid().length) {
            return -2;
        }

        int cellValue = getAnswerGrid()[row][column];

        switch (cellValue) {
            case MINE:
                setUserGridCell(row, column, MINE);
                return -1;

            case 0:
                zeroCellBFS(row, column, this);
                return 0;

            case FLAG:
                setUserGridCell(row, column, BLANK);
                return 0;

            default: //1, 2, 3... minecount
                if (getUserGrid()[row][column] == cellValue) { //already been clicked/revealed
                    return chord(row, column);
                } else { 
                    setUserGridCell(row, column, cellValue); //reveal minecount
                    decrementBlanks();
                    return 0;
                }
        }
    }

    //0 on success, -2 if out of bounds
    public int flag(int row, int column) {
        //check if out of bounds
        if (row < 0 || column < 0 || row >= getAnswerGrid().length || column >= getAnswerGrid().length) {
            return -2;
        }

        int cellValue = getUserGrid()[row][column];

        switch (cellValue) {
            case FLAG:
                setUserGridCell(row, column, BLANK);
                return 0;
            
            case MINE: //same as blank

            case BLANK:
                setUserGridCell(row, column, FLAG);
                return 0;

            default: //minecount already revealed, can't flag over a revealed cell
                return 0;
        }
    }

    //when clicking a cell whose minecount has already been revealed, you can:
    //a) reveal minecounts of neighboring cells if all neighboring mines have already been found
    //b) do nothing (maybe flash cells in future implementation) if not all mines have been found yet

    //return 0 on success, -1 if failed chord (incorrect flag) ends the game
    public int chord(int i, int j) {
        int minecount = getAnswerGrid()[i][j];
        int[][] userGrid = getUserGrid();
        int flags = countMinesOrFlags(i, j, userGrid, FLAG);

        if (flags != minecount || userGrid[i][j] == BLANK) { //mines not all found or cell not yet revealed, so can't chord (do nothing)
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