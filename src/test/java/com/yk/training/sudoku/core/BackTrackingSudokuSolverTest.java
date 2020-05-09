package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.FileUtils;
import com.yk.training.sudoku.utils.MatrixUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BackTrackingSudokuSolverTest {

    @Test
    public void shouldSolve() {
        List<String> lines = FileUtils.readResourceFile("sudoku/easy1.txt", BackTrackingSudokuSolverTest.class);
        int[][] sudoku = MatrixUtils.buildMatrix(lines);
        BackTrackingSudokuSolver sudokuSolver = new BackTrackingSudokuSolver();

        sudokuSolver.solve(sudoku);
        assertTrue(SudokuValidator.isValid(sudoku));
    }
}