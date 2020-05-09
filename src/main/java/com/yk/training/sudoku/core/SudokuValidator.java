package com.yk.training.sudoku.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Validates sudoku 9 *9.
 * <p>
 * Each row has unique elements.
 * Each column has unique elements.
 * Each block 3 *3 has unique elements.
 */
public class SudokuValidator {

    public static int SUDOKU_SIZE = 9;
    public static int BLOCK_SIZE = 3;
    public static int BLOCKS_COUNT = 3;

    /**
     * @return {@code true} if sudoku is valid.
     */
    public static boolean isValid(final int[][] sudoku) {
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
    private static boolean nonZeroElementsInRowUnique(final int[][] sudoku, final int row) {
        final Set<Integer> rowElements = new HashSet<>();
        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (sudoku[row][j] != 0 && !rowElements.add(sudoku[row][j])) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return {@code true} if all column non-zero elements are unique.
     */
    private static boolean nonZeroElementsInColumnUnique(final int[][] sudoku, final int column) {
        final Set<Integer> columnElements = new HashSet<>();
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (sudoku[i][column] != 0 && !columnElements.add(sudoku[i][column])) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return {@code true} if all block non-zero elements are unique.
     */
    private static boolean nonZeroElementsInBlockUnique(final int[][] sudoku,
                                                        final int blockStartRow,
                                                        final int blockStartColumn) {
        final Set<Integer> blockElements = new HashSet<>();
        final int iEnd = blockStartRow + BLOCK_SIZE;
        final int jEnd = blockStartColumn + BLOCK_SIZE;

        for (int i = blockStartRow; i < iEnd; i++) {
            for (int j = blockStartColumn; j < jEnd; j++) {
                if (sudoku[i][j] != 0 && !blockElements.add(sudoku[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return {@code true} if elements from given row, column, block are unique.
     */
    public static boolean nonZeroElementsInRowColumnBlockUnique(final int[][] sudoku,final int row, final int column) {
        if (!nonZeroElementsInRowUnique(sudoku, row)) {
            return false;
        }

        if (!nonZeroElementsInColumnUnique(sudoku, column)) {
            return false;
        }

        int blockStartRow = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int blockStartColumn = (column / BLOCK_SIZE) * BLOCK_SIZE;
        return nonZeroElementsInBlockUnique(sudoku, blockStartRow, blockStartColumn);
    }
}
