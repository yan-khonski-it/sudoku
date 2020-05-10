package com.yk.training.sudoku.core;

/**
 * https://stackoverflow.com/questions/42320739/sudoku-solver-program
 */
public class BackTrackingRecursiveSolver implements SudokuSolver {

    boolean canInsert(final int nextElement, final int[][] sudoku, final int row, final int column) {
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

    boolean sudoku(final int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    for (int nextElement = 1; nextElement <= 9; nextElement++) {
                        if (canInsert(nextElement, sudoku, i, j)) {
                            sudoku[i][j] = nextElement;
                            if (sudoku(sudoku)) {
                                return true;
                            }
                        }
                    }

                    sudoku[i][j] = 0;
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void solve(final int[][] sudoku) {
        sudoku(sudoku);
    }
}
