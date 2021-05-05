package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    // uf[N*N]: top site uf[N*N+1]: bottom site
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf_check_full;
    private int topSite;
    private int bottomSite;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int numSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N should be greater than zero.");
        }

        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N*N + 2);
        uf_check_full = new WeightedQuickUnionUF(N*N + 2);
        topSite = N * N;
        bottomSite = N * N + 1;
        numSites = 0;
    }

    // convert the 2D coordinate to 1D index
    private int pos2index(int row, int col) {
        return row * grid.length + col;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid.length;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new java.lang.IndexOutOfBoundsException("index is out of bounds");
        }
        grid[row][col] = 1;
        numSites += 1;
        if (row == 0) {
            uf.union(pos2index(row, col), topSite);
            uf_check_full.union(pos2index(row, col), topSite);
        }
        // take care of the problem of backwash
        if (row == grid.length - 1) {
            uf.union(pos2index(row, col), bottomSite);
        }
        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (inArea(newRow, newCol) && grid[newRow][newCol] == 1) {
                uf.union(pos2index(row, col), pos2index(newRow, newCol));
                uf_check_full.union(pos2index(row, col), pos2index(newRow, newCol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col] != 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check connectivity between the top site
        return uf_check_full.connected(pos2index(row, col), topSite);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }

}
