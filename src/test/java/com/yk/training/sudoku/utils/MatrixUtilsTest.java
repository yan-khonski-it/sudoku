package com.yk.training.sudoku.utils;

import org.junit.jupiter.api.Test;

import static com.yk.training.sudoku.utils.FileUtilsTest.LINES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixUtilsTest {

    private static final int[][] MATRIX = {
            {0, 1, 3, 8, 0, 0, 4, 0, 5},
            {0, 2, 4, 6, 0, 5, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 9, 3, 0},
            {4, 9, 0, 3, 0, 6, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 5, 0, 0},
            {0, 0, 0, 7, 0, 1, 0, 9, 3},
            {0, 6, 9, 0, 0, 0, 7, 4, 0},
            {0, 0, 0, 2, 0, 7, 6, 8, 0},
            {1, 0, 2, 0, 0, 8, 3, 5, 0}
    };

    public static int[][] getMatrix() {
        return MatrixUtils.copyMatrix(MATRIX);
    }

    @Test
    public void shouldBuildMatrix() {
        final int[][] actualMatrix = MatrixUtils.buildMatrix(LINES);

        assertTrue(matricesEqual(MATRIX, actualMatrix));
    }

    @Test
    public void shouldCopyMatrix() {
        final int[][] copy = MatrixUtils.copyMatrix(MATRIX);
        assertTrue(matricesEqual(MATRIX, copy));
        copy[0][0] = -100;

        // Original matrix was not changed.
        assertEquals(MATRIX[0][0], 0);
    }

    private boolean matricesEqual(int[][] matrix, int[][] actualMatrix) {
        if (matrix.length != actualMatrix.length) {
            return false;
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != actualMatrix[i].length) {
                return false;
            }

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != actualMatrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

}