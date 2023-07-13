package minesweeper;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Game {
    private Grid grid;
    static final int MINE = -1;
    static final int FLAG = -2;
    static final int BLANK = -3;
    static final int[][] OFFSET = {{-1, -1}, {-1, 0}, {-1, 1},
                                   { 0, -1},          { 0, 1},
                                   { 1, -1}, { 1, 0}, { 1, 1}};

    public Game(String difficulty) {
        this.grid = new Grid(difficulty);
    }

    public Grid getGrid() {
        return this.grid;
    }

    //return 0 on success, -1 if mine hit, -2 if out of bounds
    public int click(int row, int column) {
        //check if out of bounds
        if (row < 0 || column < 0 || row >= grid.getAnswerGrid().length || column >= grid.getAnswerGrid().length) {
            return -2;
        }

        int cellValue = grid.getAnswerGrid()[row][column];

        switch (cellValue) {
            case MINE:
                grid.setUserGridCell(row, column, MINE);
                return -1;

            case 0:
                blankCellBFS(row, column, grid);
                return 0;

            case FLAG:
                grid.setUserGridCell(row, column, BLANK);
                return 0;

            default: //1, 2, 3...
                if (grid.getUserGrid()[row][column] == cellValue) { //already been clicked/revealed
                    chord(row, column, cellValue);
                } else {
                    grid.setUserGridCell(row, column, cellValue);
                }
                return 0;
        }
    }

    //when clicking a cell whose minecount has already been revealed, you can:
    //a) reveal minecounts of neighboring cells if all neighboring mines have already been found
    //b) do nothing (maybe flash cells in future implementation) if not all mines have been found yet
    public void chord(int i, int j, int minecount) {
        int[][] userGrid = grid.getUserGrid();
        int foundMines = grid.countMines(i, j, userGrid);

        if (foundMines != minecount) { //mines not all found so can't chord (do nothing)
            return;
        }

        //mines all found, click all other neighbor cells (reveal nonzeros, do bfs for 0s)
        for (int[] neighbor : OFFSET) {
            int row = i + neighbor[0];
            int column = j + neighbor[1];

            if (row < 0 || column < 0 || row >= userGrid.length || column >= userGrid.length) { //neighbor is out of boundaries
                continue;
            }

            if (userGrid[row][column] == BLANK) {
                click(row, column);
            }
        }
    }

    //if a 0 is clicked, it reveals all adjacent 0s
    public HashSet<String> blankCellBFS(int i, int j, Grid grid) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> revealMinecounts = new HashSet<String>();
        int[][] answerGrid = grid.getAnswerGrid();

        queue.add(new int[]{i, j});

        while (queue.size() > 0) {
            //pop top cell and visit (check if 0 in answerGrid, if yes, reveal on userGrid), mark as visited
            int[] curCell = queue.poll();
            String coordStr = curCell[0] + "," + curCell[1]; //unique key to store in HashSet
            visited.add(coordStr);

            if (answerGrid[curCell[0]][curCell[1]] == 0) { //if 0, set userGrid and add neighbors
                grid.setUserGridCell(curCell[0], curCell[1], 0);

                //for each 0, add its neighbors (if in bounds, not already in queue, and they haven't already been visited)
                for (int[] point : OFFSET) {
                    int row = curCell[0] + point[0];
                    int column = curCell[1] + point[1];

                    if (row < 0 || column < 0 || row >= answerGrid.length || column >= answerGrid.length) { //neighbor is out of boundaries
                        continue;
                    }

                    int[] neighbor = new int[]{row, column};
                    String neighborStr = row + "," + column;

                    if (answerGrid[row][column] == 0 && !queue.contains(neighbor) && !visited.contains(neighborStr)) {
                        queue.add(neighbor);
                    }

                    if (answerGrid[row][column] != 0) {
                        revealMinecounts.add(neighborStr);
                    }
                }
            }
        }
        revealBFSMinecounts(revealMinecounts, grid.getUserGrid(), grid.getAnswerGrid());
        return revealMinecounts;
    }

    //reveals minecounts of cells adjacent to 0s revealed by BFS
    //convert each string to a coord
    //reveal it on user grid
    public void revealBFSMinecounts(HashSet<String> visited, int[][] userGrid, int[][] answerGrid) {
        for (String cell : visited) {
            String[] coords = cell.split(",");
            int row = Integer.valueOf(coords[0]);
            int column = Integer.valueOf(coords[1]);
            userGrid[row][column] = answerGrid[row][column];
        }
    }

    public static void main(String[] args) {
        Game game = new Game("beginner");

        int[][] answerGrid = {{ 1, 1, 0, 0},
                              {-1, 1, 0, 0},
                              { 2, 3, 2, 1},
                              { 1,-1,-1, 1}};
        game.getGrid().setAnswerGrid(answerGrid);

        int[][] userGrid = {{-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3},
                            {-3, -3, -3, -3}};
        game.getGrid().setUserGrid(userGrid);

        int c1 = game.click(0, 2);
        game.getGrid().printGrid(userGrid);
    }
}
