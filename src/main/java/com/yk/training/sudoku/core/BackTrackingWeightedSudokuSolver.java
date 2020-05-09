package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.SudokuUtils;

import static com.yk.training.sudoku.core.SudokuValidator.BLOCK_SIZE;
import static com.yk.training.sudoku.core.SudokuValidator.SUDOKU_SIZE;

/**
 * Improved version of {@link BackTrackingSudokuSolver}.
 * Before performing each step,
 * {@link BackTrackingSudokuSolver#navigateToZero()} returns the square that is the best to fill.
 */
public class BackTrackingWeightedSudokuSolver extends BackTrackingSudokuSolver {

    @Override
    protected boolean navigateToZero() {
        currentStep = null; // Reset current step.

        // Location with largest weight.
        Location topLocation = null;

        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                if (sudoku[i][j] == ZERO) {
                    int weight = calculateWeight(i, j);
                    if (topLocation == null) {
                        topLocation = new Location(i, j, weight);
                    } else if (topLocation.weight < weight) {
                        topLocation = new Location(i, j, weight);
                    }
                }
            }
        }

        if (topLocation == null) {
            return true;
        }

        // Set current position in sudoku as the location with largest weight.
        currentRow = topLocation.row;
        currentColumn = topLocation.column;

        return false;
    }

    private int calculateWeight(final int row, final int column) {
        final int rowWeight = SudokuUtils.countNonZeroElementsInRow(sudoku, row);
        final int columnWeight = SudokuUtils.countNonZeroElementsInColumn(sudoku, column);

        int blockStartRow = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int blockStartColumn = (column / BLOCK_SIZE) * BLOCK_SIZE;

        final int blockWeight = SudokuUtils.countNonZeroElementsInBlock(sudoku, blockStartRow, blockStartColumn);
        return rowWeight + columnWeight + blockWeight;
    }

    /**
     * Represent location in sudoku to be filled.
     */
    static class Location {
        int row;
        int column;
        int weight;

        public Location(final int row, final int column, final int weight) {
            this.row = row;
            this.column = column;
            this.weight = weight;
        }
    }
}
