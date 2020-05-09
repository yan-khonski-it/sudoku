package com.yk.training.sudoku.app;

import com.yk.training.sudoku.core.BackTrackingSudokuSolver;
import com.yk.training.sudoku.core.BackTrackingWeightedSudokuSolver;

import static com.yk.training.sudoku.utils.MatrixUtils.readMatrix;

public class App {

    public void execute() {
        System.out.println("Simple back-tracking.");
        final int[][] easy1 = readMatrix("easy1.txt");
        final BackTrackingSudokuSolver easy1Solver = new BackTrackingSudokuSolver();

        final int[][] easy2 = readMatrix("easy3.txt");
        final BackTrackingSudokuSolver easy2Solver = new BackTrackingSudokuSolver();

        final int[][] hard2 = readMatrix("hard2.txt");
        final BackTrackingSudokuSolver hard2Solver = new BackTrackingSudokuSolver();

        final int[][] evil4 = readMatrix("evil4.txt");
        final BackTrackingSudokuSolver evil4Solver = new BackTrackingSudokuSolver();

        final int[][] evil5 = readMatrix("evil5.txt");
        final BackTrackingSudokuSolver evil5Solver = new BackTrackingSudokuSolver();

        executeMeasured(() -> easy1Solver.solve(easy1), "easy1");
        executeMeasured(() -> easy2Solver.solve(easy2), "easy3");
        executeMeasured(() -> hard2Solver.solve(hard2), "hard2");
        executeMeasured(() -> evil5Solver.solve(evil5), "evil5");

        // Don't do it.
        // executeMeasured(() -> evil4Solver.solve(evil4), "evil4");

        System.out.println("\n\nImproved version, same sudoku.");
        final int[][] easy1Copy = readMatrix("easy1.txt");
        final BackTrackingSudokuSolver easy1SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] easy2Copy = readMatrix("easy3.txt");
        final BackTrackingSudokuSolver easy2SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] hard2Copy = readMatrix("hard2.txt");
        final BackTrackingSudokuSolver hard2SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] evil4Copy = readMatrix("evil4.txt");
        final BackTrackingSudokuSolver evil4SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] evil5Copy = readMatrix("evil5.txt");
        final BackTrackingSudokuSolver evil5SolverImproved = new BackTrackingWeightedSudokuSolver();

        executeMeasured(() -> easy1SolverImproved.solve(easy1Copy), "easy1");
        executeMeasured(() -> easy2SolverImproved.solve(easy2Copy), "easy3");
        executeMeasured(() -> hard2SolverImproved.solve(hard2Copy), "hard2");
        executeMeasured(() -> evil5SolverImproved.solve(evil5Copy), "evil5");

        // We can do it now.
      //  executeMeasured(() -> evil4SolverImproved.solve(evil4Copy), "evil4");
    }

    private void executeMeasured(final Runnable task, final String name) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        long total = end - start;
        System.out.println(name + ": " + total + " ns.");
    }
}
