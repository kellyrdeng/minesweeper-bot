package minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.awt.Point;

public class GridTest {
    static char[][] testGrid = {{'0', '0', '0', '0'},
                                {'0', '0', '0', '0'},
                                {'0', '0', '0', '0'},
                                {'0', '0', '0', '0'}};

    public static char[][] generateBombs(char[][] answerGrid, int bombs) {
        Random rand = new Random();
        Set<Integer> seenBefore = new HashSet<>();
        int totalCells = (int)Math.pow(answerGrid.length, 2); //81 for b

        //randomly place bombs
        while (seenBefore.size() < bombs) {
            int i = rand.nextInt(totalCells);
            int row = i / answerGrid.length;
            int column = i % answerGrid.length;
            seenBefore.add(i);
            answerGrid[row][column] = '*';
        }

        return answerGrid;
    }
    
    public static List<Point> getNeighbors(int i, int j, char[][] answerGrid) {
        List<Point> neighbors = new ArrayList<Point>();
        //add the points which are immediate neighbors to (i,j):
        //(i-1, j-1), (i-1, j), (i-1, j+1),
        //(i, j-1), ===, (i, j+1),
        //(i+1, j-1), (i+1, j), (i+1, j+1)

        for (int k = i - 1; k < i + 2; k++) {
            for (int m = j - 1; m < j + 2; m++) {
                if (k == i && m == j) {
                    continue;
                }
                neighbors.add(new Point(k, m));
            }
        }

        List<Point> validNeighbors = new ArrayList<Point>();
        //remove indexes outside of grid and store in validNeighbors
        for (Point p : neighbors) {

            if (p.getX() >= 0 && p.getY() >= 0 && p.getX() < answerGrid.length && p.getY() < answerGrid.length) {
                validNeighbors.add(p);
            }
        }
        
        return validNeighbors;
    }

    public static char[][] fillMinecount(char[][] answerGrid) {
        for (int i = 0; i < answerGrid.length; i ++) {
            for (int j = 0; j < answerGrid.length; j++) {
                if (answerGrid[i][j] == '*') {
                    continue;
                }
                List<Point> neighbors = getNeighbors(i, j, answerGrid);
                int minecount = 0;

                for (Point n : neighbors) {
                    if (answerGrid[(int)n.getX()][(int)n.getY()] == '*') {
                        minecount++;
                    }
                }
                answerGrid[i][j] = (char)(minecount + '0');
            }
        }
        return answerGrid;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        /*for (Point p : getNeighbors(0, 2, testGrid)) {
            System.out.print((int)p.getX() + ", ");
            System.out.print((int)p.getY());
            System.out.print("\n");
        }*/
        System.out.println("mines:");
        char[][] bombGrid = {{'0', '*', '0', '0'},
                             {'0', '0', '0', '0'},
                             {'*', '0', '0', '0'},
                             {'0', '*', '0', '*'}};
        printGrid(bombGrid);
        System.out.println("minecounts:");
        char[][] countGrid = fillMinecount(bombGrid);
        printGrid(countGrid);
    }
    
}