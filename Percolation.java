/*
Authors: Zeke Palmer and Siobhan Lounsbury
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    private int length;

    // top of the grid is always 1 more than n * n
    private int top;

    // bottom of the grid is always 2 more than n * n
    private int bottom;
    //private WeightedQuickUnionUF WQU;
    private WeightedQuickUnionUF WQU;

    // keeps track of the number of open sites
    private int numOpen = 0;

    // Helper function; index's each site in the grid to keep track of which are open and which are not
    private int SiteIndex(int row, int col) {
        int index;
        index = (row * length) + col;
        return index;
    }

    private void CornerCaseCheck(int row, int col) {

        // Corner Cases
        if (row < 0) {
            throw new IllegalArgumentException("Row is out of bounds");
        }
        if (row >= length) {
            throw new IllegalArgumentException("Row is out of bounds");
        }
        if (col < 0) {
            throw new IllegalArgumentException("Col is out of bounds");
        }
        if (col >= length) {
            throw new IllegalArgumentException("Col is out of bounds");
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // Corner Case
        if (n <= 0) throw new IllegalArgumentException();

        // Creates a boolean grid where false is a blocked site and true is an open site
        grid = new boolean[n][n];
        length = n;

        // Top is one higher than the highest index of sites
        top = (n * n) + 1;

        // Bottom is two higher than the highest index of sites
        bottom = (n * n) + 2;

        // Creates a new Weighted Quick Union of n * n and plus three more to include index up to 102
        WQU = new WeightedQuickUnionUF(n * n + 3);
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        CornerCaseCheck(row, col);

        // Counts the number of open sites without counting the same twice
        if (!isOpen(row, col)) {
            numOpen++;
        }

        grid[row][col] = true;

        // If site is in row 1, connect it to the top
        if (row == 0) {
            WQU.union(SiteIndex(row, col), top);
        }

        // If site is in the last row, connect it to the top
        if (row == length - 1) {
            WQU.union(SiteIndex(row, col), bottom);
        }

        if (row < length - 1 && isOpen(row + 1, col)) {
            WQU.union(SiteIndex(row, col), SiteIndex(row + 1, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            WQU.union(SiteIndex(row, col), SiteIndex(row - 1, col));
        }
        if (col < length - 1 && isOpen(row, col + 1)) {
            WQU.union(SiteIndex(row, col), SiteIndex(row, col + 1));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            WQU.union(SiteIndex(row, col), SiteIndex(row, col - 1));
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        CornerCaseCheck(row, col);

        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        CornerCaseCheck(row, col);

        if (WQU.connected(SiteIndex(row, col), top)) {
            return true;
        } else {
            return false;
        }

    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        // Number of open sites being counted in numOpen
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {

        // Is the top connected to the bottom?
        return WQU.connected(top, bottom);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(0, 0);
        p.open(0, 1);
        p.open(0, 2);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        p.open(4, 2);
        p.open(5, 2);
        p.open(5, 3);
        p.open(5, 4);
        p.open(6, 4);
        p.open(7, 4);
        p.open(8, 4);
        p.open(8, 3);
        p.open(8, 2);
        p.open(9, 2);
        p.open(9, 1);
        p.open(9, 0);
        p.open(5, 8);
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isOpen(5, 2));
        StdOut.println(p.isOpen(1, 9));
        StdOut.println(p.isFull(4, 2));
        StdOut.println(p.isFull(5, 8));
        StdOut.println(p.isFull(3, 10));

    }
}
