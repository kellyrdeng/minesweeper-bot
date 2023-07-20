package minesweeper;

import java.util.Scanner;

public class Game {
    
    public static void start() {
        Grid grid = newGame();
        Mechanics mech = new Mechanics(grid);

        //initialize variables used in the loop
        int blanks = grid.getBlanks();
        int mines = grid.getMines();
        int success = 0;
        boolean firstClick = true;
        Scanner scnr = new Scanner(System.in);

        //game runs in this loop
        while (blanks > mines && success != -1) { //-1 is mine hit
            grid.printGrid(grid.getUserGrid());
            String move;

            System.out.println("Enter your next move in the format \"action row column\": (ex: click 2 4)");
            System.out.println("Possible actions are click, flag, and chord.");

            do {
                move = scnr.nextLine();
                String[] args = move.split(" ");
                String action = args[0];
                int row = Integer.valueOf(args[1]);
                int column = Integer.valueOf(args[2]);

                if (action.equals("click") && firstClick) {
                    safeFirstClick(row, column, mech);
                }

                success = newMove(action, row, column, mech);
            } while (success == -2); //invalid input


            blanks = grid.getBlanks();
        }
        scnr.close();

        printAnswerGridAndMessage(success, grid);
    }

    public static Grid newGame() {
        //initialize scanner and use to input desired difficulty
        Scanner scnr = new Scanner(System.in);
        System.out.println("Type beginner, intermediate, or expert to select difficulty:");
        String difficulty = scnr.nextLine();

        //get valid input
        while (!difficulty.equals("beginner") && !difficulty.equals("intermediate") && !difficulty.equals("expert")) {
            System.out.println("Please type either beginner, intermediate, or expert:");
            difficulty = scnr.nextLine();
        }

        //initialize grid and mechanics based on difficulty
        Grid grid = new Grid(difficulty);
        return grid;
    }

    public static int newMove(String action, int row, int column, Mechanics mech) {  //"click 2 4
        switch (action) {
            case "click":
                return mech.click(row, column);
            case "flag":
                return mech.flag(row, column);
            case "chord":
                return mech.chord(row, column);
            default:
                //invalid input
                return -2;
        }
    }

    public static void safeFirstClick(int row, int column, Mechanics mech) {
        Grid grid = mech.getGrid();
        String difficulty = grid.getDifficulty();

        while (grid.getAnswerGrid()[row][column] == -1) { //while first click is unsafe, keep generating new grids until it is safe
            Grid newGrid = new Grid(difficulty);
            mech.setGrid(newGrid);
        }
    }

    public static void printAnswerGridAndMessage(int success, Grid grid) {
        if (success == 0) {
            grid.printGrid(grid.getAnswerGrid());
            System.out.print("\n");
            System.out.println("All mines found, you won!");
        } else {
            grid.printGrid(grid.getUserGrid());
            System.out.print("\n");
            System.out.println("Mine hit, you lost!");
        }
    }

    public static void main(String[] args) {
        newGame();
    }
}