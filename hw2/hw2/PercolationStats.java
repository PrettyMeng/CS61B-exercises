package hw2;
import java.util.Random;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private Random rand = new Random();
    private int[] estimatedThresholds;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T <= 0) {
            throw new IllegalArgumentException("should simulate at least once!");
        }
        estimatedThresholds = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int numSites = 0;
            while (!p.percolates()) {
                int randRow = rand.nextInt(N);
                int randCol = rand.nextInt(N);
                p.open(randRow, randCol);
                numSites++;
            }
            estimatedThresholds[i] = numSites / N / N;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double s = 0;
        for (int threshold : estimatedThresholds) {
            s += threshold;
        }
        return s / estimatedThresholds.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double miu = mean();
        double s = 0;
        for (int threshold : estimatedThresholds) {
            s += Math.pow((threshold - miu), 2);
        }
        return s / (estimatedThresholds.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * Math.pow(stddev(), 0.5)) / Math.pow(estimatedThresholds.length, 0.5);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * Math.pow(stddev(), 0.5)) / Math.pow(estimatedThresholds.length, 0.5);
    }
}
