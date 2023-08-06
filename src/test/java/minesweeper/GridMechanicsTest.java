package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GridMechanicsTest {
    public static final int M = -1;  //MINE
    public static final int F = -2;  //FLAG
    public static final int B = -3;  //BLANK

    static int[][] answerGrid = {{ 1, 1, 0, 0},
                                 { M, 1, 0, 0},
                                 { 2, 3, 2, 1},
                                 { 1, M, M, 1}};
    static int[][] answerGrid2 = {{ M, 1, 0, 0},
                                  { 1, 1, 0, 0},
                                  { 0, 0, 1, 1},
                                  { 0, 0, 1, M}};
    static int[][] answerGrid3 = {{ 0, 0, 0, 0},
                                  { 0, 0, 0, 0},
                                  { 0, 0, 0, 0},
                                  { 0, 0, 0, 0}};
    static int[][] answerGrid4 = {{ 0, 0, 1, 1},
                                  { 1, 0, 0, 1},
                                  { 1, 1, 0, 0},
                                  { 1, 0, 0, 1}};
    
    @Test
    public void testClickNumber() {
        Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 0);
        int[][] expectedGrid1 = {{ 1,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = grid.click(2, 1);
        int[][] expectedGrid2 = {{ 1,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  3,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZero() {
        Grid grid = new Grid("beginner");
        
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 3);
        int[][] expectedGrid1 = {{ B,  1,  0,  0},
                                 { B,  1,  0,  0},
                                 { B,  3,  2,  1},
                                 { B,  B,  B,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        //reset grid to test bfs from a different starting point
        int[][] userGrid2 = {{ B,  B,  B,  B},
                             { B,  B,  B,  B},
                             { B,  B,  B,  B},
                             { B,  B,  B,  B}};
        grid.setUserGrid(userGrid2);

        int c2 = grid.click(1, 2);
        int[][] expectedGrid2 = {{ B,  1,  0,  0},
                                 { B,  1,  0,  0},
                                 { B,  3,  2,  1},
                                 { B,  B,  B,  B}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesSmall() {
       Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid2);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 3);
        int[][] expectedGrid1 = {{ B,  1,  0,  0},
                                 { 1,  1,  0,  0},
                                 { 0,  0,  1,  1},
                                 { 0,  0,  1,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesBig() {
        Grid grid = new Grid("beginner");
        int[][] bigAnswerGrid = {{ M,  2,  1,  0,  0,  0},
                                 { 2,  M,  2,  1,  0,  0},
                                 { 1,  2,  M,  2,  1,  0},
                                 { 0,  1,  2,  M,  2,  1},
                                 { 0,  0,  1,  2,  M,  2},
                                 { 0,  0,  0,  1,  2,  M}};
        grid.setAnswerGrid(bigAnswerGrid);

        int[][] userGrid = {{ B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 5);
        int[][] expectedGrid1 = {{ B,  B,  1,  0,  0,  0},
                                 { B,  B,  2,  1,  0,  0},
                                 { B,  B,  B,  2,  1,  0},
                                 { B,  B,  B,  B,  2,  1},
                                 { B,  B,  B,  B,  B,  B},
                                 { B,  B,  B,  B,  B,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = grid.click(5, 0);
        int[][] expectedGrid2 = {{ B,  B,  1,  0,  0,  0},
                                 { B,  B,  2,  1,  0,  0},
                                 { 1,  2,  B,  2,  1,  0},
                                 { 0,  1,  2,  B,  2,  1},
                                 { 0,  0,  1,  2,  B,  B},
                                 { 0,  0,  0,  1,  B,  B}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test //click a blank that reveals a zero, perform bfs
    public void testClickAllZeros() {
        Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid3);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 0);
        int[][] expectedGrid1 = {{ 0,  0,  0,  0},
                                 { 0,  0,  0,  0},
                                 { 0,  0,  0,  0},
                                 { 0,  0,  0,  0}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testWeirdShapedBFS() {
        Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid4);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(1, 1);
        int[][] expectedGrid1 = {{ 0,  0,  1,  1},
                                 { 1,  0,  0,  1},
                                 { 1,  1,  0,  0},
                                 { 1,  0,  0,  1}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickBomb() {
        Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(1, 0);
        int[][] expectedGrid1 = {{ B,  B,  B,  B},
                                 { M,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals( M, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testChording() {
        Grid grid = new Grid("beginner");
        int[][] chordAnswerGrid = {{ M, 1, 1, M},
                                   { 1, 1, 1, 1},
                                   { 0, 0, 0, 0},
                                   { 0, 0, 0, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ F,  1,  B,  B},
                            { 1,  1,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(1, 1);
        int[][] expectedGrid1 = {{ F, 1, 1, B},
                                 { 1, 1, 1, 1},
                                 { 0, 0, 0, 0},
                                 { 0, 0, 0, 0}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testComplicatedChording() {
        Grid grid = new Grid("beginner");
        int[][] chordAnswerGrid = {{ M,  2,  M,  1},
                                   { 1,  2,  1,  1},
                                   { 0,  0,  0,  0},
                                   { 0,  0,  0,  0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ F,  2,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(0, 1); //nothing should happen
        int[][] expectedGrid1 = {{ F,  2,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = grid.flag(0, 2); 
        int[][] expectedGrid2 = {{ F,  2,  F,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));

        int c3 = grid.click(0, 1); 
        int[][] expectedGrid3 = {{ F,  2,  F,  B},
                                 { 1,  2,  1,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c3);
        assertTrue(grid.sameGrids(expectedGrid3, userGrid));
    }

    @Test
    public void testUnsuccessfulChording() {
        Grid grid = new Grid("beginner");
        int[][] chordAnswerGrid = {{ M, 1, 1, M},
                                   { 1, 1, 1, 1},
                                   { 0, 0, 0, 0},
                                   { 0, 0, 0, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  1,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(1, 1);
        int[][] expectedGrid1 = {{ B,  B,  B,  B},
                                 { B,  1,  B,  B},
                                 { B,  B,  B,  B},
                                 { B,  B,  B,  B}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickOutOfBounds() {
        Grid grid = new Grid("beginner");
        grid.setAnswerGrid(answerGrid);
        int c1 = grid.click(100, 100);
        int c2 = grid.click(1, 1);
        int c3 = grid.click( M,  M);
        assertEquals(-2, c1);
        assertEquals(0, c2);
        assertEquals(-2, c3);
    }

    @Test //regular click, just reveal the minecount of 1 cell
    public void testBlanks() {
        Grid grid = new Grid("beginner");
        int[][] fullAnswerGrid = {{ M,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  M,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2,  M,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2,  M,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2,  M,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2,  M,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2,  M,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2,  M,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2,  M}};
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

        grid.click(0, 2);
        assertEquals(80, grid.getBlanks());
        grid.click(7, 6);
        assertEquals(79, grid.getBlanks());
    }

    @Test //regular click, just reveal the minecount of 1 cell
    public void testBFSBlanks() {
        Grid grid = new Grid("beginner");
        int[][] fullAnswerGrid = {{ M,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  M,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2,  M,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2,  M,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2,  M,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2,  M,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2,  M,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2,  M,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2,  M}};
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

        grid.click(0, 8);
        assertEquals(47, grid.getBlanks());
        int[][] expectedGrid = {{ B,  B,  1,  0,  0,  0,  0,  0,  0},
                                { B,  B,  2,  1,  0,  0,  0,  0,  0},
                                { B,  B,  B,  2,  1,  0,  0,  0,  0},
                                { B,  B,  B,  B,  2,  1,  0,  0,  0},
                                { B,  B,  B,  B,  B,  2,  1,  0,  0},
                                { B,  B,  B,  B,  B,  B,  2,  1,  0},
                                { B,  B,  B,  B,  B,  B,  B,  2,  1},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B}};
        assertTrue(grid.sameGrids(expectedGrid, userGrid));
    }

    @Test //flagging doesn't affect number of blanks
    public void testFlaggingBlanks() {
        Grid grid = new Grid("beginner");
        int[][] fullAnswerGrid = {{ M,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  M,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2,  M,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2,  M,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2,  M,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2,  M,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2,  M,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2,  M,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2,  M}};
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

        grid.flag(0, 2);
        assertEquals(81, grid.getBlanks());
        grid.flag(7, 6);
        assertEquals(81, grid.getBlanks());
    }

    @Test
    public void testChordingBlanks() {
        Grid grid = new Grid("beginner");
        int[][] fullAnswerGrid = {{ M,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2,  M,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2,  M,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2,  M,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2,  M,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2,  M,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2,  M,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2,  M,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2,  M}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{ F,  2,  B,  B,  B,  B,  B,  B,  B},
                            { B,  F,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B},
                            { B,  B,  B,  B,  B,  B,  B,  B,  B}};
        grid.setUserGrid(userGrid);
        grid.setBlanks(78);

        grid.click(0, 1);
        assertEquals(75, grid.getBlanks());
        int[][] expectedGrid = {{ F,  2,  1,  B,  B,  B,  B,  B,  B},
                                { 2,  F,  2,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B},
                                { B,  B,  B,  B,  B,  B,  B,  B,  B}};
        assertTrue(grid.sameGrids(userGrid, expectedGrid));
    }

    @Test
    public void testIncorrectFlagChord() { //chording with an incorrectly placed flag should end the game
        Grid grid = new Grid("beginner");
        
        int[][] chordAnswerGrid = {{ M, 1, 1, M},
                                   { 1, 1, 1, 1},
                                   { 0, 0, 0, 0},
                                   { 0, 0, 0, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ B,  F,  B,  B},
                            { B,  1,  B,  B},
                            { B,  B,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.click(1, 1);
        assertEquals(-1, c1);
    }
    @Test
    public void testTooManyFlagsStillWorks() {
        Grid grid = new Grid("beginner");
        
        int[][] chordAnswerGrid = {{ 0, 0, 1, 1},
                                   { 1, 1, 2, M},
                                   { 1, M, 2, 1},
                                   { 1, 1, 1, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ B,  B,  B,  B},
                            { B,  B,  B,  F},
                            { B,  F,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.flag(0, 2);
        assertEquals(0, c1);
        int[][] expectedGrid = {{ B,  B,  F,  B},
                                { B,  B,  B,  F},
                                { B,  F,  B,  B},
                                { B,  B,  B,  B}};
        assertTrue(grid.sameGrids(expectedGrid, userGrid));
    }

    @Test
    public void testChordWithTooManyFlagsDoesNothing() {
        Grid grid = new Grid("beginner");
        
        int[][] chordAnswerGrid = {{ 0, 0, 1, 1},
                                   { 1, 1, 2, M},
                                   { 1, M, 2, 1},
                                   { 1, 1, 1, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ B,  B,  F,  B},
                            { B,  B,  2,  F},
                            { B,  F,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.chord(1, 2);
        assertEquals(0, c1);
        int[][] expectedGrid = {{ B,  B,  F,  B},
                                { B,  B,  2,  F},
                                { B,  F,  B,  B},
                                { B,  B,  B,  B}};
        assertTrue(grid.sameGrids(expectedGrid, userGrid));
    }

    @Test
    public void testCantChordABlankCell() {
        Grid grid = new Grid("beginner");
        
        int[][] chordAnswerGrid = {{ 0, 0, 1, 1},
                                   { 1, 1, 2, M},
                                   { 1, M, 2, 1},
                                   { 1, 1, 1, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{ B,  B,  F,  B},
                            { B,  B,  2,  F},
                            { B,  F,  B,  B},
                            { B,  B,  B,  B}};
        grid.setUserGrid(userGrid);

        int c1 = grid.chord(0, 0);
        assertEquals(0, c1);
        int[][] expectedGrid = {{ B,  B,  F,  B},
                                { B,  B,  2,  F},
                                { B,  F,  B,  B},
                                { B,  B,  B,  B}};
        assertTrue(grid.sameGrids(expectedGrid, userGrid));
    }
}