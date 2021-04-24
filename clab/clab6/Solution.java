public class Solution {

    private int rows;
    private int cols;

    public static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.rows = grid.length;
        this.cols = grid[0].length;

        // should not destroy the original input array
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        // pop all the hitted bubble before getting started
        for (int[] hit: hits) {
            copy[hit[0]][hit[1]] = 0;
        }

        int size = rows * cols;
        UnionFind uf = new UnionFind(size + 1);

        // union the top row with the sentinel
        for (int j = 0; j < cols; j++) {
            if (copy[0][j] == 1) {
                uf.union(j, size);
            }
        }

        // construct the original disjoint set
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (copy[i][j] == 1) {
                    if (copy[i - 1][j] == 1) {
                        uf.union(getIndex(i - 1, j), getIndex(i, j));
                    }
                    if (j > 0 && copy[i][j - 1] == 1) {
                        uf.union(getIndex(i, j - 1), getIndex(i, j));
                    }
                }
            }
        }

        int hitsNum = hits.length;
        int[] bubbleNumbers = new int[hitsNum];
        for (int i = hitsNum - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];
            if (grid[x][y] == 0) {
                continue;
            }
            int originalNumber = uf.getSize(size);
            if (x == 0) {
                uf.union(y, size);
            }

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (inArea(newX, newY) && copy[newX][newY] == 1) {
                    uf.union(getIndex(x, y), getIndex(newX, newY));
                }
            }

            bubbleNumbers[i] = Math.max(0, uf.getSize(size) - originalNumber - 1);

            copy[x][y] = 1;
        }
        return bubbleNumbers;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * convert to disjoint sets index
     *
     * @param x
     * @param y
     * @return
     */
    private int getIndex(int x, int y) {
        return x * cols + y;
    }

    private class UnionFind {

        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Path Compression, saving efforts to maintain size
         *
         * @param x
         * @return
         */
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            // maintain size array
            size[rootY] += size[rootX];
        }

        public int getSize(int x) {
            int root = find(x);
            return size[root];
        }

    }
}