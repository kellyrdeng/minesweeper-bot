import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
            case "intermediate":
                size = 16;
                bombs = 40;
            case "expert":
                size = 24;
                bombs = 99;
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
            answerGrid[row][column] = 'x';
        }

        return answerGrid;
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