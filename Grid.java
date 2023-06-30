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
    private char[][] answerGrid;
    private char[][] userGrid;

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

        this.answerGrid = new char[size][size];
        this.userGrid = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(this.answerGrid[i], '0');
            Arrays.fill(this.userGrid[i], '0');
        }

        this.answerGrid = generateBombs(answerGrid, bombs);
        this.answerGrid = fillMinecount(answerGrid);
    }

    public char[][] getAnswerGrid() {
        return this.answerGrid;
    }

    public char[][] getUserGrid() {
        return this.userGrid;
    }

    public void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    //use random number generator to select random indexes to place bombs
    public char[][] generateBombs(char[][] answerGrid, int bombs) {
        Random rand = new Random();
        Set<Integer> seenBefore = new HashSet<>();
        int totalCells = (int)Math.pow(answerGrid.length, 2); //81 for b

        //randomly place bombs
        while (seenBefore.size() < bombs) {
            int i = rand.nextInt(totalCells);
            int row = i / answerGrid.length;
            int column = i % answerGrid.length;
            seenBefore.add(i);
            answerGrid[row][column] = '*';
        }

        return answerGrid;
    }

    //use bomb placement to fill answer grid cells with the number of bombs they are adjacent to
    public char[][] fillMinecount(char[][] answerGrid) {
        for (int i = 0; i < answerGrid.length; i ++) {
            for (int j = 0; j < answerGrid.length; j++) {
                if (answerGrid[i][j] == '*') {
                    continue;
                }
                List<Point> neighbors = getNeighbors(i, j, answerGrid);
                int minecount = 0;

                for (Point n : neighbors) {
                    if (answerGrid[(int)n.getX()][(int)n.getY()] == '*') {
                        minecount++;
                    }
                }
                answerGrid[i][j] = (char)(minecount + '0');
            }
        }
        return answerGrid;
    }

    public List<Point> getNeighbors(int i, int j, char[][] answerGrid) {
        List<Point> neighbors = new ArrayList<Point>();
        //add the points which are immediate neighbors to (i,j):
        //(i-1, j-1), (i-1, j), (i-1, j+1),
        //(i, j-1), ===, (i, j+1),
        //(i+1, j-1), (i+1, j), (i+1, j+1)

        for (int k = i - 1; k < i + 2; k++) {
            for (int m = j - 1; m < j + 2; m++) {
                if (k == i && m == j) {
                    continue;
                }
                neighbors.add(new Point(k, m));
            }
        }

        List<Point> validNeighbors = new ArrayList<Point>();
        //remove indexes outside of grid and store in validNeighbors
        for (Point p : neighbors) {

            if (p.getX() >= 0 && p.getY() >= 0 && p.getX() < answerGrid.length && p.getY() < answerGrid.length) {
                validNeighbors.add(p);
            }
        }
        
        return validNeighbors;
    }

    public static void main(String[] args) {
        Grid test = new Grid("beginner");
        System.out.println("Answer Grid:");
        test.printGrid(test.getAnswerGrid());
        System.out.print("\n");
        System.out.println("User Grid:");
        test.printGrid(test.getUserGrid());
    }
}