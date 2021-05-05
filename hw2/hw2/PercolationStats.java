package hw2;
import java.util.Random;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private static Random rand = new Random();
    private double[] estimatedThresholds;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T <= 0) {
            throw new IllegalArgumentException("should simulate at least once!");
        }
        estimatedThresholds = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int[][] openedSites = new int[N][N];
            while (!p.percolates()) {
                int randRow = rand.nextInt(N);
                int randCol = rand.nextInt(N);
                if (openedSites[randRow][randCol] == 0) {
                    p.open(randRow, randCol);
                    openedSites[randRow][randCol] = 1;
                }
            }
            estimatedThresholds[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double s = 0;
        for (double threshold : estimatedThresholds) {
            s += threshold;
        }
        return s / estimatedThresholds.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double miu = mean();
        double s = 0;
        for (double threshold : estimatedThresholds) {
            s += Math.pow((threshold - miu), 2);
        }
        return s / (estimatedThresholds.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double std = Math.pow(stddev(), 0.5);
        return mean() - (1.96 * std) / Math.pow(estimatedThresholds.length, 0.5);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double std = Math.pow(stddev(), 0.5);
        return mean() + (1.96 * std) / Math.pow(estimatedThresholds.length, 0.5);
    }
    
}
