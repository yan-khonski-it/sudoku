package com.yk.training.sudoku.app;

import com.yk.training.sudoku.core.SudokuSolver;
import com.yk.training.sudoku.core.SudokuValidator;
import com.yk.training.sudoku.utils.MatrixUtils;

public class SudokuTask implements Runnable {

    private final int[][] sudoku;
    private final SudokuSolver sudokuSolver;
    private String name;
    private long executionNs;

    private SudokuTask(final int[][] sudoku, final String name, final SudokuSolver sudokuSolver) {
        this.sudoku = sudoku;
        this.sudokuSolver = sudokuSolver;
    }

    public static SudokuTask of(final int[][] sudoku, final String name, final SudokuSolver sudokuSolver) {
        return new SudokuTask(MatrixUtils.copyMatrix(sudoku), name, sudokuSolver);
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        sudokuSolver.solve(sudoku);
        long end = System.nanoTime();
        this.executionNs = end - start;

        validateSolution();
    }

    public float getExecutionMs() {
        return ((float) executionNs) / 1000000;
    }

    public String getName() {
        return name;
    }

    private void validateSolution() {
        if (!SudokuValidator.isValid(sudoku)) {
            throw new RuntimeException(String.format("Sudoku %s is not valid", name));
        }

        if (MatrixUtils.containsZeros(sudoku)) {
            final String errorMessage = String.format("Sudoku %s is not solved. Solver: %s.", name,
                    sudokuSolver.getClass().getSimpleName());
            throw new RuntimeException(errorMessage);
        }
    }
}
