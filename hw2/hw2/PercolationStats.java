package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] openNums;
    private int T;
    private int N;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        this.T = T;
        this.N = N;
        openNums = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation ufs = pf.make(N);
            while(!ufs.percolates()) {
                ufs.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
            }
            openNums[i] = 1.0 * ufs.numberOfOpenSites() / N / N;
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        double sum = 0;
        for (int i = 0; i < T ; i++) {
            sum += openNums[i];
        }
        return sum / T;
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0.0;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return 0.0;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return 0.0;
    }
}
