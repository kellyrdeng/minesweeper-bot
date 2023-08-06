package minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import minesweeper.Grid.ClickSuccess;

public class GameTest {
    public static final int M = -1;  //MINE
    public static final int F = -2;  //FLAG
    public static final int B = -3;  //BLANK

    //after each move, check grid, check success, check blanks
    @Test
    public void testFullBeginnerGame() {
        Grid grid = new Grid("beginner");
        int[][] fullAnswerGrid = {{ 1,  1,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  M,  3,  1,  1,  0,  0,  0,  0},
                                  { 3,  M,  3,  M,  1,  0,  1,  1,  1},
                                  { M,  2,  2,  1,  2,  1,  2,  M,  1},
                                  { 2,  2,  0,  2,  1,  M,  2,  1,  1},
                                  { M,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  M,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  M,  1},
                                  { 0,  0,  0,  0,  1,  M,  2,  1,  1}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{ B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B}};
        grid.setUserGrid(userGrid);
    }
}
