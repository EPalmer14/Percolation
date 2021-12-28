/*
Authors: Zeke Palmer and Siobhan Lounsbury
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    // Creates an integer to keep track of the number of tests to take
    // Creates an array of doubles to place the fraction of the grid that is open
    // upon percolation in after each test
    Stopwatch watchy = new Stopwatch();

    int tests;
    double[] siteFraction;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // Corner cases
        if (n <= 0) {
            throw new IllegalArgumentException("n is less than or equal to 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials is less than or equal to 0");
        }


        // sets integer tests to the given number of trials and creates
        // a new array of doubles to place the fractions
        tests = trials;
        siteFraction = new double[tests];

        // for loop iterates for the number of given tests
        for (int i = 0; i < tests; i++) {

            // Creates a new percolation grid of n * n size
            Percolation PercStats = new Percolation(n);

            // Each test sets the number of open sites to 0 and increases the count
            // after each time a site is opened
            int numOpen = 0;

            // Sets row and col to random numbers between 1 and n + 1 and opens that row
            // col pairing until the grid percolates
            while (!PercStats.percolates()) {
                int row = StdRandom.uniform(0, n);
                int col = StdRandom.uniform(0, n);
                if (!PercStats.isOpen(row, col)) {
                    PercStats.open(row, col);
                    numOpen++;
                }
            }
            // Evaluates the fraction of the grid that is open upon percolation and
            // puts that number into the array in the index of the test number
            siteFraction[i] = (double) numOpen / (n * n);
        }

        StdOut.println(watchy.elapsedTime());

    }

    // sample mean of percolation threshold
    public double mean() {
        double mean;

        // Finds the mean
        mean = StdStats.mean(siteFraction);

        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double stddev;

        // Finds the standard deviation
        stddev = StdStats.stddev(siteFraction);

        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double conLow;

        // Equation for finding the low endpoint for 95% confidence
        conLow = mean() - ((1.96 * stddev()) / Math.sqrt(tests));

        return conLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double conHigh;

        // Equation for finding the high endpoint for 95% confidence
        conHigh = mean() + ((1.96 * stddev()) / Math.sqrt(tests));

        return conHigh;
    }

    // test client (see below)
    public static void main(String[] args) {

        // First test
        PercolationStats testing = new PercolationStats(10, 1000);
        StdOut.println(testing.mean());
        StdOut.println(testing.stddev());
        StdOut.println(testing.confidenceLow());
        StdOut.println(testing.confidenceHigh());

        // Second test
        /*PercolationStats testing2 = new PercolationStats(100, 300);
        StdOut.println(testing2.mean());
        StdOut.println(testing2.stddev());
        StdOut.println(testing2.confidenceLow());
        StdOut.println(testing2.confidenceHigh());*/
    }
}
