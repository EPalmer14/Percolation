# Percolation
Percolation project for Algorithms class


Simulates an N-by-N grid of sites that are either blocked or open. Full sites are open sites that are connected to an open site at the top of the grid. This can only be connected by a chain of open sites either above, below, or to the left and right of the full site. The system is considered percolated when there is an open site at the bottom of the grid that is considered full due to a connected chain of open sites up to the top of the grid. This program creates an input grid of size NxN and randomly opens sites until the grid percolates. This can be used to find a value p* such that when p<p* the grid rarely ever percolates and when p>p* the grid almost always percolates with p being the number of open sites. N must be sufficiently large.
