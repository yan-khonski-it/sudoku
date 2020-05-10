package com.yk.training.sudoku.core;

public class CharBackTrackingRecursiveSolver {

    private static final char ZERO = '.';
    private static final char MIN = '1';
    private static final char MAX = '9';

    boolean canInsert(final char nextElement, final char[][] sudoku, final int row, final int column) {
        for (int t = 0; t < 9; t++) {
            if (sudoku[t][column] == nextElement) {
                return false;
            }

            if (sudoku[row][t] == nextElement) {
                return false;
            }

            if (sudoku[(row / 3) * 3 + t / 3][(column / 3) * 3 + t % 3] == nextElement) {
                return false;
            }
        }
        return true;
    }

    boolean sudoku(final char[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == ZERO) {
                    for (char nextElement = MIN; nextElement <= MAX; nextElement++) {
                        if (canInsert(nextElement, sudoku, i, j)) {
                            sudoku[i][j] = nextElement;

                            if (sudoku(sudoku)) {
                                return true;
                            }
                        }
                    }

                    sudoku[i][j] = ZERO;
                    return false;
                }
            }
        }

        return true;
    }

    public void solve(final char[][] sudoku) {
        sudoku(sudoku);
    }
}
