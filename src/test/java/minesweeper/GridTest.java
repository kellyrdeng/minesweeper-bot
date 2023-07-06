package minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.awt.Point;
import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GridTest {
    
    @Test //test proper size, assert userGrid is all 0s
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
                assertEquals(0, userGrid[i][j]);
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
                assertEquals(0, userGrid[i][j]);
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
                assertEquals(0, userGrid[i][j]);
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
                if (beginnerAnsGrid[i][j] == -1) {
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
                if (intermediateAnsGrid[i][j] == -1) {
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
                if (expertAnsGrid[i][j] == -1) {
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
                if (answerGrid[i][j] == -1) {
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
                if (answerGrid[i][j] == -1) {
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
}