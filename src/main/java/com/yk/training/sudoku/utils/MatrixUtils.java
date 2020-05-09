package com.yk.training.sudoku.utils;

import java.util.List;

/**
 * Utils to work with matrix.
 */
public final class MatrixUtils {

    private MatrixUtils() {
        throw new IllegalStateException("It is not allowed to create instance.");
    }

    public static int[][] readMatrix(final String filename) {
        return readMatrix(filename, MatrixUtils.class);
    }

    public static int[][] readMatrix(final String filename, final Class<?> clazz) {
        final List<String> lines = FileUtils.readResourceFile(filename, clazz);
        return buildMatrix(lines);
    }

    public static int[][] buildMatrix(final List<String> lines) {
        final int size = lines.size();
        final int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            final String line = lines.get(i);

            final String[] numbers = line.split(" ");
            if (numbers.length != size) {
                throw new RuntimeException(
                        String.format("Invalid matrix. We expect a square matrix of size %s.", size));
            }

            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        return matrix;
    }

    public static int[][] copyMatrix(final int[][] matrix) {
        int n = matrix.length;
        int[][] res = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, res[i], 0, n);
        }

        return res;
    }

    public static String matrixToString(final int[][] matrix) {
        final int n = matrix.length;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                sb.append("\n");
            }

            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    sb.append(" ");
                }

                sb.append(matrix[i][j]);
            }
        }

        return sb.toString();
    }
}
