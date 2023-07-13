package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class GameTest {
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
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(0, 0);
        int[][] expectedGrid1 = {{ 1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = game.click(2, 1);
        int[][] expectedGrid2 = {{ 1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3,  3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZero() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(0, 3);
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

        int c2 = game.click(1, 2);
        int[][] expectedGrid2 = {{-3,  1,  0,  0},
                                 {-3,  1,  0,  0},
                                 {-3,  3,  2,  1},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesSmall() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid2);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(0, 3);
        int[][] expectedGrid1 = {{-3,  1,  0,  0},
                                 { 1,  1,  0,  0},
                                 { 0,  0,  1,  1},
                                 { 0,  0,  1, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickZeroTwoZonesBig() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
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

        int c1 = game.click(0, 5);
        int[][] expectedGrid1 = {{-3, -3,  1,  0,  0,  0},
                                 {-3, -3,  2,  1,  0,  0},
                                 {-3, -3, -3,  2,  1,  0},
                                 {-3, -3, -3, -3,  2,  1},
                                 {-3, -3, -3, -3, -3, -3},
                                 {-3, -3, -3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = game.click(5, 0);
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
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid3);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(0, 0);
        int[][] expectedGrid1 = {{ 0,  0,  0,  0},
                                 { 0,  0,  0,  0},
                                 { 0,  0,  0,  0},
                                 { 0,  0,  0,  0}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testWeirdShapedBFS() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid4);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(1, 1);
        int[][] expectedGrid1 = {{ 0,  0,  1,  1},
                                 { 1,  0,  0,  1},
                                 { 1,  1,  0,  0},
                                 { 1,  0,  0,  1}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testBFSNonZeroNeighbors() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
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

        HashSet<String> nonZeroNeighbors = game.blankCellBFS(0, 5, grid);
        assertTrue(nonZeroNeighbors.contains("0,2"));
        assertTrue(nonZeroNeighbors.contains("1,2"));
        assertTrue(nonZeroNeighbors.contains("1,3"));
        assertTrue(nonZeroNeighbors.contains("2,3"));
        assertTrue(nonZeroNeighbors.contains("2,4"));
        assertTrue(nonZeroNeighbors.contains("3,4"));
        assertTrue(nonZeroNeighbors.contains("3,5"));
        assertEquals(nonZeroNeighbors.size(), 7);
    }

    @Test
    public void testClickBomb() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(1, 0);
        int[][] expectedGrid1 = {{-3, -3, -3, -3},
                                 {-1, -3, -3, -3},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(-1, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));
    }

    @Test
    public void testClickOutOfBounds() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid);
        int c1 = game.click(100, 100);
        int c2 = game.click(1, 1);
        int c3 = game.click(-1, -1);
        assertEquals(-2, c1);
        assertEquals(0, c2);
        assertEquals(-2, c3);
    }

    @Test //all mines found
    public void testCompleteGame() {
        
    }
}