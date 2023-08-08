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
                                  { 2,  2,  0,  0,  1,  M,  2,  1,  1},
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

        assertEquals(ClickSuccess.SUCCESS, grid.click(0, 8));
        assertEquals(59, grid.getBlanks());
        int[][] expectedGrid1 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  B,  B,  1,  0,  1,  1,  1},
                                 { B,  B,  B,  B,  2,  1,  2,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B}};
        assertTrue(grid.sameGrids(userGrid, expectedGrid1));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(2, 3));
        assertEquals(59, grid.getBlanks());
        int[][] expectedGrid2 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  B,  F,  1,  0,  1,  1,  1},
                                 { B,  B,  B,  B,  2,  1,  2,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid2));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(2, 4));
        assertEquals(58, grid.getBlanks());
        int[][] expectedGrid3 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  B,  F,  1,  0,  1,  1,  1},
                                 { B,  B,  B,  1,  2,  1,  2,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid3));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(3, 3));
        assertEquals(33, grid.getBlanks());
        int[][] expectedGrid4 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  B,  B},
                                 { B,  2,  0,  0,  1,  B,  B,  B,  B},
                                 { B,  1,  0,  1,  2,  B,  B,  B,  B},
                                 { 1,  1,  0,  1,  B,  B,  B,  B,  B},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid4));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(4, 5));
        assertEquals(33, grid.getBlanks());
        int[][] expectedGrid5 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  B,  B},
                                 { B,  2,  0,  0,  1,  F,  B,  B,  B},
                                 { B,  1,  0,  1,  2,  B,  B,  B,  B},
                                 { 1,  1,  0,  1,  B,  B,  B,  B,  B},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid5));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(3, 5));
        assertEquals(32, grid.getBlanks());
        int[][] expectedGrid6 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  B,  B},
                                 { B,  2,  0,  0,  1,  F,  2,  B,  B},
                                 { B,  1,  0,  1,  2,  B,  B,  B,  B},
                                 { 1,  1,  0,  1,  B,  B,  B,  B,  B},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid6));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(3, 7));
        assertEquals(32, grid.getBlanks());
        int[][] expectedGrid7 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  F,  B},
                                 { B,  2,  0,  0,  1,  F,  2,  B,  B},
                                 { B,  1,  0,  1,  2,  B,  B,  B,  B},
                                 { 1,  1,  0,  1,  B,  B,  B,  B,  B},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid7));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(4, 6));
        assertEquals(23, grid.getBlanks());
        int[][] expectedGrid8 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  F,  B},
                                 { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                 { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                 { 1,  1,  0,  1,  B,  B,  1,  1,  1},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid8));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(4, 7));
        assertEquals(22, grid.getBlanks());
        int[][] expectedGrid9 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                 { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                 { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                 { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                 { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                 { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                 { 1,  1,  0,  1,  B,  B,  1,  1,  1},
                                 { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                 { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid9));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(6, 4));
        assertEquals(22, grid.getBlanks());
        int[][] expectedGrid10 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  B,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                  { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid10));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(5, 5));
        assertEquals(21, grid.getBlanks());
        int[][] expectedGrid11 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  B,  B,  B,  B},
                                  { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid11));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(6, 5));
        assertEquals(19, grid.getBlanks());
        int[][] expectedGrid12 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  B,  B},
                                  { 0,  0,  0,  0,  1,  B,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid12));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(8, 5));
        assertEquals(19, grid.getBlanks());
        int[][] expectedGrid13 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  B,  B},
                                  { 0,  0,  0,  0,  1,  F,  B,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid13));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(7, 5));
        assertEquals(18, grid.getBlanks());
        int[][] expectedGrid14 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  B,  B},
                                  { 0,  0,  0,  0,  1,  F,  2,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid14));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(7, 7));
        assertEquals(18, grid.getBlanks());
        int[][] expectedGrid15 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  B},
                                  { 0,  0,  0,  0,  1,  F,  2,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid15));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(6, 8));
        assertEquals(17, grid.getBlanks());
        int[][] expectedGrid16 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  B,  B}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid16));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(7, 8));
        assertEquals(15, grid.getBlanks());
        int[][] expectedGrid17 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { B,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid17));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(5, 0));
        assertEquals(15, grid.getBlanks());
        int[][] expectedGrid18 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { B,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid18));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(5, 1));
        assertEquals(14, grid.getBlanks());
        int[][] expectedGrid19 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { B,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid19));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(3, 0));
        assertEquals(14, grid.getBlanks());
        int[][] expectedGrid20 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  B,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid20));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(2, 1));
        assertEquals(14, grid.getBlanks());
        int[][] expectedGrid21 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  B,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  F,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid21));

        assertEquals(ClickSuccess.SUCCESS, grid.flag(1, 1));
        assertEquals(14, grid.getBlanks());
        int[][] expectedGrid22 = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  F,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  F,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid22));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(1, 2));
        assertEquals(13, grid.getBlanks());
        int[][] expectedGrid23 = {{ B,  1,  1,  0,  0,  0,  0,  0,  0},
                                  { B,  F,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  F,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid23));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(0, 1));
        assertEquals(11, grid.getBlanks());
        int[][] expectedGrid24 = {{ 1,  1,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  F,  3,  1,  1,  0,  0,  0,  0},
                                  { B,  F,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid24));

        assertEquals(ClickSuccess.SUCCESS, grid.chord(1, 0));
        assertEquals(10, grid.getBlanks());
        int[][] expectedGrid25 = {{ 1,  1,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  F,  3,  1,  1,  0,  0,  0,  0},
                                  { 3,  F,  3,  F,  1,  0,  1,  1,  1},
                                  { F,  2,  2,  1,  2,  1,  2,  F,  1},
                                  { 2,  2,  0,  0,  1,  F,  2,  1,  1},
                                  { F,  1,  0,  1,  2,  2,  1,  0,  0},
                                  { 1,  1,  0,  1,  F,  1,  1,  1,  1},
                                  { 0,  0,  0,  1,  2,  2,  2,  F,  1},
                                  { 0,  0,  0,  0,  1,  F,  2,  1,  1}}; 
        assertTrue(grid.sameGrids(userGrid, expectedGrid25));
    }

    @Test
    public void testBFSWhenSomeAlreadyRevealed() {
        Grid grid = new Grid("beginner");
        int[][] answerGrid = {{ 0,  0,  1,  M},
                              { 0,  0,  1,  1},
                              { 1,  1,  0,  0},
                              { M,  1,  0,  0}};
        grid.setAnswerGrid(answerGrid);

        int[][] userGrid = {{ B,  B,  1,  F},
                            { B,  B,  B,  1},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        grid.setBlanks(14);
        assertEquals(ClickSuccess.SUCCESS, grid.chord(0, 2));
        int[][] expectedGrid1 = {{ 0,  0,  1,  F},
                                 { 0,  0,  1,  1},
                                 { 1,  1,  0,  0},
                                 { B,  1,  0,  0}};
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
        assertEquals(2, grid.getBlanks());
    }
}
