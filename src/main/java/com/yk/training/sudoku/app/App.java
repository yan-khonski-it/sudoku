package com.yk.training.sudoku.app;

import com.yk.training.sudoku.core.BackTrackingSudokuSolver;
import com.yk.training.sudoku.core.BackTrackingWeightedSudokuSolver;
import com.yk.training.sudoku.core.SudokuValidator;
import com.yk.training.sudoku.utils.MatrixUtils;

import static com.yk.training.sudoku.utils.MatrixUtils.readMatrix;

public class App {

    public void execute() {
        final int[][] easy1 = readMatrix("easy1.txt");
        final BackTrackingSudokuSolver easy1Solver = new BackTrackingSudokuSolver();

        final int[][] easy2 = readMatrix("easy2.txt");
        final BackTrackingSudokuSolver easy2Solver = new BackTrackingSudokuSolver();

        final int[][] hard2 = readMatrix("hard2.txt");
        final BackTrackingSudokuSolver hard2Solver = new BackTrackingSudokuSolver();

        final int[][] evil4 = readMatrix("evil4.txt");
        final BackTrackingSudokuSolver evil4Solver = new BackTrackingSudokuSolver();

        final int[][] evil5 = readMatrix("evil5.txt");
        final BackTrackingSudokuSolver evil5Solver = new BackTrackingSudokuSolver();

        final int[][] easy1Copy = readMatrix("easy1.txt");
        final BackTrackingSudokuSolver easy1SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] easy2Copy = readMatrix("easy2.txt");
        final BackTrackingSudokuSolver easy2SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] hard2Copy = readMatrix("hard2.txt");
        final BackTrackingSudokuSolver hard2SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] evil4Copy = readMatrix("evil4.txt");
        final BackTrackingSudokuSolver evil4SolverImproved = new BackTrackingWeightedSudokuSolver();

        final int[][] evil5Copy = readMatrix("evil5.txt");
        final BackTrackingSudokuSolver evil5SolverImproved = new BackTrackingWeightedSudokuSolver();

        executeMeasuredAndCompare(() -> easy1Solver.solve(easy1), () -> easy1SolverImproved.solve(easy1Copy), "easy1");
        executeMeasuredAndCompare(() -> easy2Solver.solve(easy2), () -> easy2SolverImproved.solve(easy2Copy), "easy2");
        executeMeasuredAndCompare(() -> hard2Solver.solve(hard2), () -> hard2SolverImproved.solve(hard2Copy), "hard2");
        executeMeasuredAndCompare(() -> evil5Solver.solve(evil5), () -> evil5SolverImproved.solve(evil5Copy), "evil5");

        // Don't do it. evil4Solver.solve(evil4) will not finish quickly.
        //  executeMeasuredAndCompare(() -> evil4Solver.solve(evil4), () -> evil4SolverImproved.solve(evil4Copy), "evil4");
        executeAndMeasure(() -> evil4SolverImproved.solve(evil4Copy), "evil4");

        // Validate all solutions
        sudokuIsValidAndSolved(easy1);
        sudokuIsValidAndSolved(easy2);
        sudokuIsValidAndSolved(hard2);
        sudokuIsValidAndSolved(evil5);

        sudokuIsValidAndSolved(easy1Copy);
        sudokuIsValidAndSolved(easy2Copy);
        sudokuIsValidAndSolved(hard2Copy);
        sudokuIsValidAndSolved(evil5Copy);

        sudokuIsValidAndSolved(evil4Copy);
    }

    private void executeMeasuredAndCompare(final Runnable task, final Runnable improvedTask, final String name) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        long firstTaskTotal = (end - start);

        start = System.nanoTime();
        improvedTask.run();
        end = System.nanoTime();
        long secondTaskTotal = end - start;
        long delta = firstTaskTotal - secondTaskTotal;
        float times = ((float) firstTaskTotal) / secondTaskTotal;
        System.out.println(name + " " + convertToMs(firstTaskTotal) + " " + convertToMs(secondTaskTotal) + " " + delta + " " + times);
    }

    private void executeAndMeasure(final Runnable task, final String name) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        long firstTaskTotal = end - start;
        System.out.println(name + " " + convertToMs(firstTaskTotal));
    }

    private float convertToMs(final long ns) {
        return (float) ns / 1000000;
    }

    private void sudokuIsValidAndSolved(final int[][] sudoku) {
        if (!SudokuValidator.isValid(sudoku)) {
            throw new RuntimeException("Sudoku is not valid.");
        }

        if (MatrixUtils.countZeros(sudoku) > 0) {
            throw new RuntimeException("Sudoku is not solved.");
        }
    }
}
