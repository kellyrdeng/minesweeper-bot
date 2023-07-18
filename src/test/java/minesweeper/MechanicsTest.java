package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class MechanicsTest {
    static int[][] answerGrid = {{ 1, 1, 0, 0},
                                 {-1, 1, 0, 0},
                                 { 2, 3, 2, 1},
                                 { 1,-1,-1, 1}};
    static int[][] answerGrid2 = {{-1, 1, 0, 0},
                                  { 1, 1, 0, 0},
                                  { 0, 0, 1, 1},
                                  { 0, 0, 1,-1}};
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
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 0);
        int[][] expectedGrid1 = {{ 1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = mech.click(2, 1);
        int[][] expectedGrid2 = {{ 1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3,  3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZero() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 3);
        int[][] expectedGrid1 = {{-3,  1,  0,  0},
                                 {-3,  1,  0,  0},
                                 {-3,  3,  2,  1},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        //reset grid to test bfs from a different starting point
        int[][] userGrid2 = {{-3, -3, -3, -3},
                             {-3, -3, -3, -3},
                             {-3, -3, -3, -3},
                             {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid2);

        int c2 = mech.click(1, 2);
        int[][] expectedGrid2 = {{-3,  1,  0,  0},
                                 {-3,  1,  0,  0},
                                 {-3,  3,  2,  1},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesSmall() {
       Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid2);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 3);
        int[][] expectedGrid1 = {{-3,  1,  0,  0},
                                 { 1,  1,  0,  0},
                                 { 0,  0,  1,  1},
                                 { 0,  0,  1, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesBig() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] bigAnswerGrid = {{-1,  2,  1,  0,  0,  0},
                                 { 2, -1,  2,  1,  0,  0},
                                 { 1,  2, -1,  2,  1,  0},
                                 { 0,  1,  2, -1,  2,  1},
                                 { 0,  0,  1,  2, -1,  2},
                                 { 0,  0,  0,  1,  2, -1}};
        grid.setAnswerGrid(bigAnswerGrid);

        int[][] userGrid = {{-3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 5);
        int[][] expectedGrid1 = {{-3, -3,  1,  0,  0,  0},
                                 {-3, -3,  2,  1,  0,  0},
                                 {-3, -3, -3,  2,  1,  0},
                                 {-3, -3, -3, -3,  2,  1},
                                 {-3, -3, -3, -3, -3, -3},
                                 {-3, -3, -3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = mech.click(5, 0);
        int[][] expectedGrid2 = {{-3, -3,  1,  0,  0,  0},
                                 {-3, -3,  2,  1,  0,  0},
                                 { 1,  2, -3,  2,  1,  0},
                                 { 0,  1,  2, -3,  2,  1},
                                 { 0,  0,  1,  2, -3, -3},
                                 { 0,  0,  0,  1, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test //click a blank that reveals a zero, perform bfs
    public void testClickAllZeros() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid3);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 0);
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
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid4);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(1, 1);
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
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(1, 0);
        int[][] expectedGrid1 = {{-3, -3, -3, -3},
                                 {-1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(-1, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testChording() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] chordAnswerGrid = {{-1, 1, 1,-1},
                                   { 1, 1, 1, 1},
                                   { 0, 0, 0, 0},
                                   { 0, 0, 0, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{-2,  1, -3, -3},
                            { 1,  1, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(1, 1);
        int[][] expectedGrid1 = {{-2, 1, 1,-3},
                                 { 1, 1, 1, 1},
                                 { 0, 0, 0, 0},
                                 { 0, 0, 0, 0}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testComplicatedChording() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] chordAnswerGrid = {{-1,  2, -1,  1},
                                   { 1,  2,  1,  1},
                                   { 0,  0,  0,  0},
                                   { 0,  0,  0,  0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{-2,  2, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(0, 1); //nothing should happen
        int[][] expectedGrid1 = {{-2,  2, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = mech.flag(0, 2); 
        int[][] expectedGrid2 = {{-2,  2, -2, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));

        int c3 = mech.click(0, 1); 
        int[][] expectedGrid3 = {{-2,  2, -2, -3},
                                 { 1,  2,  1, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c3);
        assertTrue(grid.sameGrids(expectedGrid3, userGrid));
    }

    @Test
    public void testUnsuccessfulChording() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] chordAnswerGrid = {{-1, 1, 1,-1},
                                   { 1, 1, 1, 1},
                                   { 0, 0, 0, 0},
                                   { 0, 0, 0, 0}};
        grid.setAnswerGrid(chordAnswerGrid);

        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3,  1, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = mech.click(1, 1);
        int[][] expectedGrid1 = {{-3, -3, -3, -3},
                                 {-3,  1, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickOutOfBounds() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        grid.setAnswerGrid(answerGrid);
        int c1 = mech.click(100, 100);
        int c2 = mech.click(1, 1);
        int c3 = mech.click(-1, -1);
        assertEquals(-2, c1);
        assertEquals(0, c2);
        assertEquals(-2, c3);
    }

    @Test //regular click, just reveal the minecount of 1 cell
    public void testBlanks() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] fullAnswerGrid = {{-1,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2, -1,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2, -1,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2, -1,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2, -1,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2, -1,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2, -1,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2, -1,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2, -1}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        mech.click(0, 2);
        assertEquals(80, grid.getBlanks());
        mech.click(7, 6);
        assertEquals(79, grid.getBlanks());
    }

    @Test //regular click, just reveal the minecount of 1 cell
    public void testBFSBlanks() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] fullAnswerGrid = {{-1,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2, -1,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2, -1,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2, -1,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2, -1,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2, -1,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2, -1,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2, -1,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2, -1}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        mech.click(0, 8);
        assertEquals(47, grid.getBlanks());
        int[][] expectedGrid = {{-3, -3,  1,  0,  0,  0,  0,  0,  0},
                                {-3, -3,  2,  1,  0,  0,  0,  0,  0},
                                {-3, -3, -3,  2,  1,  0,  0,  0,  0},
                                {-3, -3, -3, -3,  2,  1,  0,  0,  0},
                                {-3, -3, -3, -3, -3,  2,  1,  0,  0},
                                {-3, -3, -3, -3, -3, -3,  2,  1,  0},
                                {-3, -3, -3, -3, -3, -3, -3,  2,  1},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        assertTrue(grid.sameGrids(expectedGrid, userGrid));
    }

    @Test //flagging doesn't affect number of blanks
    public void testFlaggingBlanks() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] fullAnswerGrid = {{-1,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2, -1,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2, -1,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2, -1,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2, -1,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2, -1,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2, -1,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2, -1,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2, -1}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        mech.flag(0, 2);
        assertEquals(81, grid.getBlanks());
        mech.flag(7, 6);
        assertEquals(81, grid.getBlanks());
    }

    @Test
    public void testChordingBlanks() {
        Grid grid = new Grid("beginner");
        Mechanics mech = new Mechanics(grid);
        int[][] fullAnswerGrid = {{-1,  2,  1,  0,  0,  0,  0,  0,  0},
                                  { 2, -1,  2,  1,  0,  0,  0,  0,  0},
                                  { 1,  2, -1,  2,  1,  0,  0,  0,  0},
                                  { 0,  1,  2, -1,  2,  1,  0,  0,  0},
                                  { 0,  0,  1,  2, -1,  2,  1,  0,  0},
                                  { 0,  0,  0,  1,  2, -1,  2,  1,  0},
                                  { 0,  0,  0,  0,  1,  2, -1,  2,  1},
                                  { 0,  0,  0,  0,  0,  1,  2, -1,  2},
                                  { 0,  0,  0,  0,  0,  0,  1,  2, -1}};
        grid.setAnswerGrid(fullAnswerGrid);

        int[][] userGrid = {{-2,  2, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -2, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                            {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        grid.setUserGrid(userGrid);
        grid.setBlanks(78);

        mech.click(0, 1);
        assertEquals(75, grid.getBlanks());
        int[][] expectedGrid = {{-2,  2,  1, -3, -3, -3, -3, -3, -3},
                                { 2, -2,  2, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3},
                                {-3, -3, -3, -3, -3, -3, -3, -3, -3}};
        assertTrue(grid.sameGrids(userGrid, expectedGrid));
    }
}