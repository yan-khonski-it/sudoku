package com.yk.training.sudoku.core;

import java.util.HashSet;
import java.util.Set;

import static com.yk.training.sudoku.core.SudokuValidator.BLOCKS_COUNT;
import static com.yk.training.sudoku.core.SudokuValidator.BLOCK_SIZE;
import static com.yk.training.sudoku.core.SudokuValidator.SUDOKU_SIZE;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class CharSudokuValidator {

    private static final char ZERO = '.';

    public static boolean isValid(final char[][] sudoku) {
        // All rows are valid.
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (!nonZeroElementsInRowUnique(sudoku, i)) {
                return false;
            }
        }

        // All columns are valid
        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (!nonZeroElementsInColumnUnique(sudoku, j)) {
                return false;
            }
        }

        // All blocks are valid
        for (int i = 0; i < BLOCKS_COUNT; i++) {
            for (int j = 0; j < BLOCKS_COUNT; j++) {
                final int blockRow = i * BLOCK_SIZE;
                final int blockColumn = j * BLOCK_SIZE;
                if (!nonZeroElementsInBlockUnique(sudoku, blockRow, blockColumn)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return {@code true} if all row non-zero elements are unique.
     */
    private static boolean nonZeroElementsInRowUnique(final char[][] sudoku, final int row) {
        final Set<Character> rowElements = new HashSet<>();
        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (sudoku[row][j] != ZERO && !rowElements.add(sudoku[row][j])) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return {@code true} if all column non-zero elements are unique.
     */
    private static boolean nonZeroElementsInColumnUnique(final char[][] sudoku, final int column) {
        final Set<Character> columnElements = new HashSet<>();
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (sudoku[i][column] != ZERO && !columnElements.add(sudoku[i][column])) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return {@code true} if all block non-zero elements are unique.
     */
    private static boolean nonZeroElementsInBlockUnique(final char[][] sudoku,
                                                        final int blockStartRow,
                                                        final int blockStartColumn) {
        final Set<Character> blockElements = new HashSet<>();
        final int iEnd = blockStartRow + BLOCK_SIZE;
        final int jEnd = blockStartColumn + BLOCK_SIZE;

        for (int i = blockStartRow; i < iEnd; i++) {
            for (int j = blockStartColumn; j < jEnd; j++) {
                if (sudoku[i][j] != ZERO && !blockElements.add(sudoku[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
