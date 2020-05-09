package com.yk.training.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharSudokuValidatorTest {

    private static final char[][] VALID_SUDOKU = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    private static final char[][] INVALID_SUDOKU = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    @Test
    public void shouldValidate() {
        assertTrue(CharSudokuValidator.isValid(VALID_SUDOKU));
        assertFalse(CharSudokuValidator.isValid(INVALID_SUDOKU));

        // Row failed
        VALID_SUDOKU[0][7] = '3';
        assertFalse(CharSudokuValidator.isValid(VALID_SUDOKU));
        VALID_SUDOKU[0][7] = '.';

        // Column failed
        VALID_SUDOKU[2][0] = '7';
        assertFalse(CharSudokuValidator.isValid(VALID_SUDOKU));
        VALID_SUDOKU[2][0] = '.';

        // Block failed
        VALID_SUDOKU[2][0] = '3';
        assertFalse(CharSudokuValidator.isValid(VALID_SUDOKU));
        VALID_SUDOKU[2][0] = '.';
    }
}