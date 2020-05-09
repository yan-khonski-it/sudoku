package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.MatrixUtilsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuValidatorTest {

    @Test
    public void shouldValidate() {
        int[][] validSudoku = MatrixUtilsTest.getMatrix();

        assertTrue(SudokuValidator.isValid(validSudoku));

        validSudoku[0][0] = 6;
        assertTrue(SudokuValidator.isValid(validSudoku));

        // Row failed
        validSudoku[0][0] = 5;
        assertFalse(SudokuValidator.isValid(validSudoku));

        // Column failed
        validSudoku[0][0] = 4;
        assertFalse(SudokuValidator.isValid(validSudoku));

        // Block failed
        validSudoku[0][0] = 2;
        assertFalse(SudokuValidator.isValid(validSudoku));
    }
}