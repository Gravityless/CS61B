package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF WQU;
    private int[] openSites;
    private int N;
    private int openNums;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        this.N = N;
        openNums = 0;
        openSites = new int[N * N];
        WQU = new WeightedQuickUnionUF(N * N + 2);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        int m = row * N + col;
        if (!isOpen(row, col)) {
            openSites[m] = 1;
            openNums++;
            unionOpen(m);
        }
    }

    // uion blocks if needed
    public void unionOpen(int m){
        if (m < N && !WQU.connected(m, N * N)) {
            WQU.union(m, N * N);
        }
        if (m >= N * N - N && !WQU.connected(m, N * N + 1)) {
            WQU.union(m, N * N + 1);
        }
        if (m - N >= 0 && openSites[m - N] == 1) {
            WQU.union(m, m - N);
        }
        if (m - 1 >= 0 && openSites[m - 1] == 1) {
            WQU.union(m, m - 1);
        }
        if (m + N <= N * N - 1 && openSites[m + N] == 1) {
            WQU.union(m, m + N);
        }
        if (m + 1 <= N * N - 1 && openSites[m + 1] == 1) {
            WQU.union(m, m + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int m = row * N + col;
        return openSites[m] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int m = row * N + col;
        return WQU.connected(N * N, m);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openNums;
    }

    // does the system percolate?
    public boolean percolates(){
        return WQU.connected(N * N, N * N + 1);
    }

    // use for unit testing (not required)
    public static void main(String[] args){

    }
}
