package com.yk.training.sudoku.utils;

import static com.yk.training.sudoku.core.BackTrackingSudokuSolver.ZERO;
import static com.yk.training.sudoku.core.SudokuValidator.BLOCK_SIZE;
import static com.yk.training.sudoku.core.SudokuValidator.SUDOKU_SIZE;

/**
 * Matrix utils related to sudoku.
 */
public class SudokuUtils {

    private SudokuUtils() {
        throw new IllegalStateException("Instanced not allowed.");
    }

    public static int countNonZeroElementsInRow(final int[][] matrix, final int row) {
        int counter = 0;
        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (matrix[row][j] != ZERO) {
                counter++;
            }
        }

        return counter;
    }

    public static int countNonZeroElementsInColumn(final int[][] matrix, final int column) {
        int counter = 0;
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (matrix[i][column] != ZERO) {
                counter++;
            }
        }

        return counter;
    }

    public static int countNonZeroElementsInBlock(final int[][] matrix,
                                                  final int blockStartRow,
                                                  final int blockStartColumn) {
        int counter = 0;

        final int iEnd = blockStartRow + BLOCK_SIZE;
        final int jEnd = blockStartColumn + BLOCK_SIZE;

        for (int i = blockStartRow; i < iEnd; i++) {
            for (int j = 0; j < jEnd; j++) {
                if (matrix[i][j] != ZERO) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
