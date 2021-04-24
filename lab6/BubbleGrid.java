
public class BubbleGrid {
    private int[][] grid;

    public BubbleGrid(int[][] g) {
        grid = g;
    }

    public int[] popBubbles(int[][] darts) {
        UnionFind uf = new UnionFind(grid.length * grid[0].length + 1);

        return null;
    }

    public static void main(String[] args) {
        int[][] g = new int[][]{{1, 1, 0},{1, 0, 0},{1, 1, 0},{1, 1, 1}};
        int[][] darts = new int[][]{{2, 2}, {2, 0}};
        BubbleGrid bg = new BubbleGrid(g);
        System.out.println(bg.popBubbles(darts));

    }
}
