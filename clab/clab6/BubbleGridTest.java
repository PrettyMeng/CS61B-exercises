import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);

        int[][] grid2 = {{1, 1, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 1, 1}};
        int[][] darts2 = {{2, 2}, {2, 0}};
        int[] expected2 = {0, 4};

        validate(grid2, darts2, expected2);

        int[][] grid3 = {{1, 0, 0, 0},
                        {1, 1, 0, 0}};
        int[][] darts3 = {{1, 1}, {1, 0}};
        int[] expected3 = {0, 0};

        validate(grid3, darts3, expected3);

        int[][] grid4 = {{1, 0, 1}, {1, 1, 1}};
        int[][] darts4 = {{0, 0}, {0, 2}, {1, 1}};
        int[] expected4 = {0, 3, 0};

        validate(grid4, darts4, expected4);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
}
