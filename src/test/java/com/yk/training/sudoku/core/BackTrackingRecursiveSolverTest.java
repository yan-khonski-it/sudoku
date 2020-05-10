package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.FileUtils;
import com.yk.training.sudoku.utils.MatrixUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackTrackingRecursiveSolverTest {

    @Test
    public void shouldSolve() {
        List<String> lines = FileUtils.readResourceFile("sudoku/easy1.txt",
                BackTrackingRecursiveSolverTest.class);
        int[][] sudoku = MatrixUtils.buildMatrix(lines);
        BackTrackingRecursiveSolver sudokuSolver = new BackTrackingRecursiveSolver();

        sudokuSolver.solve(sudoku);
        System.out.println(MatrixUtils.matrixToString(sudoku));
        assertTrue(SudokuValidator.isValid(sudoku));
        assertFalse(MatrixUtils.containsZeros(sudoku));
    }
}