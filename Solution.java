import java.util.LinkedList;

public class Solution {

    public int[][] grid1;
    public int[][] grid2;
    public int[][] moves = {
        {-1, 0},//up
        {1, 0},//down
        {0, -1},//left
        {0, 1},//right
    };

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;

        int counter_subIslands = 0;
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[0].length; c++) {
                if (grid2[r][c] == 1) {
                    boolean subisland = breadthFirstSearch(r, c);
                    counter_subIslands += subisland ? 1 : 0;
                }
            }
        }
        return counter_subIslands;
    }

    public boolean breadthFirstSearch(int row, int column) {
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(new Point(row, column));
        grid2[row][column] = 0;
        boolean subIsland = grid1[row][column] == 1;

        while (!queue.isEmpty()) {
            Point p = queue.removeFirst();

            for (int i = 0; i < moves.length; i++) {
                int new_r = p.row + moves[i][0];
                int new_c = p.column + moves[i][1];

                if (isInGrid(new_r, new_c) && grid2[new_r][new_c] == 1) {
                    if (grid1[new_r][new_c] == 0) {
                        subIsland = false;
                    }
                    queue.add(new Point(new_r, new_c));
                    grid2[new_r][new_c] = 0;
                }

            }
        }
        return subIsland;
    }

    public boolean isInGrid(int row, int column) {
        return row >= 0 && row < grid1.length && column >= 0 && column < grid1[0].length;
    }

}

class Point {

    int row;
    int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
