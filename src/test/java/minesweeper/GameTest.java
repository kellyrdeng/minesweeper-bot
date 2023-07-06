package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTest {
    static int[][] answerGrid = {{ 1, 1, 0, 0},
                                 {-1, 1, 0, 0},
                                 { 2, 3, 2, 1},
                                 { 1,-1,-1, 1}};
    
    /*@Test
    public void testClickNumber() {
        Game game = new Game("beginner");
        int[][] userGrid = {{0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}};

        int c1 = game.click(0, 0);
        int[][] expectedGrid1 = {{1, 0, 0, 0},
                                 {0, 0, 0, 0},
                                 {0, 0, 0, 0},
                                 {0, 0, 0, 0}};
        assertEquals(0, c1);
        assertEquals(expectedGrid1, userGrid);

        int c2 = game.click(2, 1);
        int[][] expectedGrid2 = {{1, 0, 0, 0},
                                 {0, 0, 0, 0},
                                 {0, 3, 0, 0},
                                 {0, 0, 0, 0}};
        assertEquals(0, c2);
        assertEquals(expectedGrid2, userGrid);
    }

    @Test
    public void testClickZero() {
        Game game = new Game("beginner");
        int[][] userGrid = {{0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}};

        int c1 = game.click(0, 3);
        int[][] expectedGrid1 = {{1, 0, 0, 0},
                                 {0, 0, 0, 0},
                                 {0, 0, 0, 0},
                                 {0, 0, 0, 0}};
        assertEquals(0, c1);
        assertEquals(expectedGrid1, userGrid);
    }

    @Test
    public void testClickBomb() {
        
    }

    @Test
    public void testClickOutOfBounds() {
        
    }

    @Test //all mines found
    public void testCompleteGame() {
        
    }*/
}
