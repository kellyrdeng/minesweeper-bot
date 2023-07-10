package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        int[][] expectedGrid1 = {{-3, -3,  0,  0},
                                 {-3, -3,  0,  0},
                                 {-3, -3, -3, -3},
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
        int[][] expectedGrid2 = {{-3, -3,  0,  0},
                                 {-3, -3,  0,  0},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
    }

    @Test
    public void testClickZeroTwoZones() {
        Game game = new Game("beginner");
        Grid grid = game.getGrid();
        grid.setAnswerGrid(answerGrid2);
        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        grid.setUserGrid(userGrid);

        int c1 = game.click(0, 3);
        int[][] expectedGrid1 = {{-3, -3,  0,  0},
                                 {-3, -3,  0,  0},
                                 {-3, -3, -3, -3},
                                 {-3, -3, -3, -3}};
        assertEquals(0, c1);
        assertTrue(grid.sameGrids(expectedGrid1, userGrid));

        int c2 = game.click(2, 0);
        int[][] expectedGrid2 = {{-3, -3,  0,  0},
                                 {-3, -3,  0,  0},
                                 { 0,  0, -3, -3},
                                 { 0,  0, -3, -3}};
        assertEquals(0, c2);
        assertTrue(grid.sameGrids(expectedGrid2, userGrid));
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
        
    }

    @Test //all mines found
    public void testCompleteGame() {
        
    }
}