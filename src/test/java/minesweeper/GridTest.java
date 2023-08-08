package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GridTest {
    public static final int M =  -1;  //MINE
    public static final int F =  -2;  //FLAG
    public static final int B =  -3;  //BLANK
    
    @Test //test proper size, assert userGrid is all BLANKs
    public void testCreateBeginnerGrid() {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid = beginner.getAnswerGrid();
        int[][] userGrid = beginner.getUserGrid();

        assertEquals(9, answerGrid.length);
        assertEquals(9, userGrid.length);
        for (int i = 0; i < answerGrid.length; i++) {
            assertEquals(9, answerGrid[i].length);
            assertEquals(9, userGrid[i].length);
        }

        for (int i = 0; i < userGrid.length; i++) {
            for (int j = 0; j < userGrid.length; j++) {
                assertEquals(B, userGrid[i][j]);
            }
        }
    }

    @Test //test proper size and correct number of bombs, assert userGrid is all 0s
    public void testCreateIntermediateGrid() {
        Grid intermediate = new Grid("intermediate");
        int[][] answerGrid = intermediate.getAnswerGrid();
        int[][] userGrid = intermediate.getUserGrid();
        
        assertEquals(16, answerGrid.length);
        assertEquals(16, userGrid.length);
        for (int i = 0; i < answerGrid.length; i++) {
            assertEquals(16, answerGrid[i].length);
            assertEquals(16, userGrid[i].length);
        }

        for (int i = 0; i < userGrid.length; i++) {
            for (int j = 0; j < userGrid.length; j++) {
                assertEquals(B, userGrid[i][j]);
            }
        }
    }

    @Test //test proper size and correct number of bombs, assert userGrid is all 0s
    public void testCreateExpertGrid() {
        Grid expert = new Grid("expert");
        int[][] answerGrid = expert.getAnswerGrid();
        int[][] userGrid = expert.getUserGrid();

        assertEquals(24, answerGrid.length);
        assertEquals(24, userGrid.length);
        for (int i = 0; i < answerGrid.length; i++) {
            assertEquals(24, answerGrid[i].length);
            assertEquals(24, userGrid[i].length);
        }

        for (int i = 0; i < userGrid.length; i++) {
            for (int j = 0; j < userGrid.length; j++) {
                assertEquals(B, userGrid[i][j]);
            }
        }
    }

    @Test //test for correct number of bombs in beginner/intermediate/expert
    public void testGenerateBombs() {
        Grid beginner = new Grid("beginner");
        int[][] beginnerAnsGrid = beginner.getAnswerGrid();

        int bBombs = 0;
        for (int i = 0; i < beginnerAnsGrid.length; i++) {
            for (int j = 0; j < beginnerAnsGrid.length; j++) {
                if (beginnerAnsGrid[i][j] ==  M) {
                    bBombs++;
                }
            }
        }
        assertEquals(10, bBombs);

        Grid intermediate = new Grid("intermediate");
        int[][] intermediateAnsGrid = intermediate.getAnswerGrid();

        int iBombs = 0;
        for (int i = 0; i < intermediateAnsGrid.length; i++) {
            for (int j = 0; j < intermediateAnsGrid.length; j++) {
                if (intermediateAnsGrid[i][j] == M) {
                    iBombs++;
                }
            }
        }
        assertEquals(40, iBombs);

        Grid expert = new Grid("expert");
        int[][] expertAnsGrid = expert.getAnswerGrid();

        int eBombs = 0;
        for (int i = 0; i < expertAnsGrid.length; i++) {
            for (int j = 0; j < expertAnsGrid.length; j++) {
                if (expertAnsGrid[i][j] == M) {
                    eBombs++;
                }
            }
        }
        assertEquals(99, eBombs);
    }

    @Test
    public void testGenerateNoBombs() {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0},
                               {0, 0, 0},
                               {0, 0, 0}};
        beginner.generateBombs(answerGrid, 0);

        int bombs = 0;
        for (int i = 0; i < answerGrid.length; i++) {
            for (int j = 0; j < answerGrid.length; j++) {
                if (answerGrid[i][j] == M) {
                    bombs++;
                }
            }
        }
        assertEquals(0, bombs);
    }

    @Test
    public void testGenerateGridOfAllBombs() {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0},
                               {0, 0, 0},
                               {0, 0, 0}};
        beginner.generateBombs(answerGrid, 9);

        int bombs = 0;
        for (int i = 0; i < answerGrid.length; i++) {
            for (int j = 0; j < answerGrid.length; j++) {
                if (answerGrid[i][j] == M) {
                    bombs++;
                }
            }
        }
        assertEquals(9, bombs);
    }

    /*@Test
    public void testGenerateTooManyBombs() throws RuntimeException{
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0},
                               {0, 0, 0},
                               {0, 0, 0}};
        Exception e = assertThrows(beginner.generateBombs(answerGrid, 10));
        assertEquals(e.getMessage(), "Number of mines exceeds number of cells");
    }*/

    //@Test
    public void testcountMinesOrFlags() {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0},
                               {0, 0, 0},
                               {0, 0, 1}};
        
        assertEquals(0, beginner.countTarget(0, 0, answerGrid, M));
        assertEquals(1, beginner.countTarget(1, 1, answerGrid, M));
        assertEquals(0, beginner.countTarget(2, 2, answerGrid, M));
    }

    @Test
    public void testFillMinecount() {
        Grid beginner = new Grid("beginner");
        int[][] answerGrid =  {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        for (int m = 0; m < 100; m++) {
            beginner.generateBombs(answerGrid, 10);
            beginner.fillMinecount(answerGrid);

            for (int i = 0; i < answerGrid.length; i++) {
                for (int j = 0; j < answerGrid.length; j++) {
                    int cellValue = answerGrid[i][j];
                    if (cellValue == M) { //MINE
                        continue;
                    }
                    assertEquals(cellValue, beginner.countTarget(i, j, answerGrid, M));
                }
            }
        }
    }
}