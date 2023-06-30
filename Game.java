import java.util.Arrays;

public class Game {
    private char[][] answerGrid;
    private char[][] userGrid;

    public Game(String difficulty) { //generates the answer and user grids cased on difficulty passed in
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

    public static void main(String[] args) {
        Game test = new Game("beginner");
        System.out.println("Answer Grid:");
        test.printGrid(test.getAnswerGrid());
        System.out.print("\n");
        System.out.println("User Grid:");
        test.printGrid(test.getUserGrid());
    }
}