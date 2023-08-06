package minesweeper;

import java.util.Scanner;

import minesweeper.Grid.ClickSuccess;

public class Game {
    
    public static void start() {
        Grid grid = newGame();

        //initialize variables used in the loop
        int blanks = grid.getBlanks();
        int mines = grid.getMines();
        ClickSuccess success = ClickSuccess.SUCCESS;
        boolean firstClick = true;
        Scanner scnr = new Scanner(System.in);

        //game runs in this loop
        while (blanks > mines && success != ClickSuccess.GAMEEND) { //-1 is mine hit
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
                    safeFirstClick(row, column, grid);
                    firstClick = false;
                }

                success = newMove(action, row, column, grid);
            } while (success == ClickSuccess.UNSUCCESS); //invalid input


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

        scnr.close();
        return new Grid(difficulty);
    }

    public static ClickSuccess newMove(String action, int row, int column, Grid grid) {  //"click 2 4
        switch (action) {
            case "click":
                return grid.click(row, column);
            case "flag":
                return grid.flag(row, column);
            case "chord":
                return grid.chord(row, column);
            default:
                //invalid input, out of bounds
                return ClickSuccess.UNSUCCESS;
        }
    }

    public static void safeFirstClick(int row, int column, Grid grid) {
        String difficulty = grid.getDifficulty();

        while (grid.getAnswerGrid()[row][column] == -1) { //while first click is unsafe, keep generating new grids until it is safe
            Grid newGrid = new Grid(difficulty);
            grid.setAnswerGrid(newGrid.getAnswerGrid());
        }
    }

    public static void printAnswerGridAndMessage(ClickSuccess success, Grid grid) {
        if (success == ClickSuccess.SUCCESS) {
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