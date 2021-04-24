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
        int bubbleNum = 0;
        // calculate the number of bubbles in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                bubbleNum += grid[i][j];
            }
        }
        // 0 is the sentinel root
        for (int dartIdx = 0; dartIdx < darts.length; dartIdx++) {
            int dartRow = darts[dartIdx][0];
            int dartCol = darts[dartIdx][1];
            // if hits the top row or hit an empty space, nothing changes
            if (dartRow == 0 || grid[dartRow][dartCol] == 0) {
                hitNumbers[dartIdx] = 0;
                continue;
            }
            UnionFind uf = new UnionFind(numRows * numCols + 1);
            for (int i = 0; i < numCols; i++) {
                if (grid[0][i] == 1) {
                    uf.union(0, i + 1);
                }
            }
            for (int i = 1; i <= dartRow; i ++) {
                for (int j = 0; j <= dartCol; j++) {
                    // two side, just check the upward position
                    int upPos = (i-1) * numCols + j + 1;
                    int selfPos = i * numCols + j + 1;
                    if (uf.connected(0, upPos) && grid[i][j] == 1) {
                        uf.union(0, selfPos);
                        continue;
                    }
                    // middle, check the left position as well
                    if (j != 0 && j != dartCol) {
                        int leftPos = i * numCols + j;
                        if (uf.connected(0, leftPos) && grid[i][j] == 1) {
                            uf.union(0, selfPos);
                        }
                    }

                }
            }
            hitNumbers[dartIdx] = bubbleNum - uf.sizeOf(0) + 1;

        }
        return hitNumbers;
    }
}
