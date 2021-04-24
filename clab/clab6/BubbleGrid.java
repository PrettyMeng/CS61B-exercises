public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] hitNumbers = new int[darts.length];
        int numRows = grid.length;
        int numCols = grid[0].length;
        // 0 is the sentinel root
        for (int dartIdx = 0; dartIdx < darts.length; dartIdx++) {
            int dartRow = darts[dartIdx][0];
            int dartCol = darts[dartIdx][1];
            // if hits an empty space, nothing changes
            if (grid[dartRow][dartCol] == 0) {
                hitNumbers[dartIdx] = 0;
                continue;
            }
            int bubbleNum = 0;
            // calculate the number of bubbles in the grid, should be recalculated after every iteration
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    bubbleNum += grid[i][j];
                }
            }
            int[][] newGrid = new int[numRows][numCols];
            UnionFind uf = new UnionFind(numRows * numCols + 1);
            for (int i = 0; i < numCols; i++) {
                if (grid[0][i] == 1 && (i != dartCol || dartRow != 0)) {
                    uf.union(i + 1, 0);
                    newGrid[0][i] = 1;
                }
            }
            for (int i = 1; i < numRows; i ++) {
                for (int j = 0; j < numCols; j++) {
                    // if it is not the hitting position, union with the rule
                    if (!(i == dartRow && j == dartCol)) {
                        // always check the upward position
                        int upPos = (i - 1) * numCols + j + 1;
                        int selfPos = i * numCols + j + 1;
                        if (uf.connected(0, upPos) && grid[i][j] == 1) {
                            uf.union(selfPos, 0);
                            newGrid[i][j] = 1;
                            continue;
                        }
                        // if not the left most, check the left side as well
                        if (j != 0) {
                            int leftPos = selfPos - 1;
                            if (uf.connected(0, leftPos) && grid[i][j] == 1) {
                                uf.union(selfPos, 0);
                                newGrid[i][j] = 1;
                            }
                        }
                    }
                }
                // should iterate in another direction as well
                for (int p = numCols - 1; p >= 0; p--) {
                    // if it is not the hitting position, union with the rule
                    if (!(i == dartRow && p == dartCol)) {
                        // always check the upward position
                        int upPos = (i - 1) * numCols + p + 1;
                        int selfPos = i * numCols + p + 1;
                        if (uf.connected(0, upPos) && grid[i][p] == 1) {
                            uf.union(selfPos, 0);
                            newGrid[i][p] = 1;
                            continue;
                        }
                        // if not the right most, check the right side as well
                        if (p != numCols - 1) {
                            int rightPos = selfPos + 1;
                            if (uf.connected(0, rightPos) && grid[i][p] == 1) {
                                uf.union(selfPos, 0);
                                newGrid[i][p] = 1;
                            }
                        }
                    }
                }
            }
            hitNumbers[dartIdx] = bubbleNum - (uf.sizeOf(0) - 1) - 1;

            // update the grid
            grid = newGrid;

        }
        return hitNumbers;
    }
}
