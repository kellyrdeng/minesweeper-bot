package Test;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class GridTest {
    static char[][] testGrid = {{'0', '0', '0', '0'},
                                {'0', '0', '0', '0'},
                                {'0', '0', '0', '0'},
                                {'0', '0', '0', '0'}};
    
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

    public static void main(String[] args) {
        for (Point p : getNeighbors(3, 3, testGrid)) {
            System.out.print((int)p.getX() + ", ");
            System.out.print((int)p.getY());
            System.out.print("\n");
        }
    }
    
}
