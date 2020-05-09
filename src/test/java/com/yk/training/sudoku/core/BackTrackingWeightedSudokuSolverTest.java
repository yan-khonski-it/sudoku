package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.FileUtils;
import com.yk.training.sudoku.utils.MatrixUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BackTrackingWeightedSudokuSolverTest {

    @Test
    public void shouldSolve() {
        List<String> lines = FileUtils.readResourceFile("sudoku/hard2.txt",
                BackTrackingWeightedSudokuSolverTest.class);
        int[][] sudoku = MatrixUtils.buildMatrix(lines);
        BackTrackingSudokuSolver sudokuSolver = new BackTrackingWeightedSudokuSolver();

        sudokuSolver.solve(sudoku);
        assertTrue(SudokuValidator.isValid(sudoku));
    }
}