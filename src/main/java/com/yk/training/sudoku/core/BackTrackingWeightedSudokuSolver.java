package com.yk.training.sudoku.core;

import com.yk.training.sudoku.utils.SudokuUtils;

import java.util.HashSet;
import java.util.Set;

import static com.yk.training.sudoku.core.SudokuValidator.BLOCK_SIZE;
import static com.yk.training.sudoku.core.SudokuValidator.SUDOKU_SIZE;

/**
 * Improved version of {@link BackTrackingSudokuSolver}.
 * Before performing each step,
 * {@link BackTrackingSudokuSolver#navigateToZero()} returns the square that is the best to fill.
 */
public class BackTrackingWeightedSudokuSolver extends BackTrackingSudokuSolver {

    private static final int MAX_POSSIBLE_WEIGHT = 8;
    private static final int RETURN_IMMEDIATELY_WEIGHT = 999;

    @Override
    protected boolean navigateToZero() {
        if (sudoku[currentRow][currentColumn] == ZERO) {
            return false;
        }

        currentStep = null; // Reset current step.

        // Location with largest weight.
        Location topLocation = null;

        findLocationWithMaxWeightLabel:
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                if (sudoku[i][j] == ZERO) {
                    final int rowWeight = SudokuUtils.countNonZeroElementsInRow(sudoku, i);
                    if (rowWeight == MAX_POSSIBLE_WEIGHT) {
                        topLocation = new Location(i, j, MAX_POSSIBLE_WEIGHT);
                        break findLocationWithMaxWeightLabel;
                    }

                    final int columnWeight = SudokuUtils.countNonZeroElementsInColumn(sudoku, j);
                    if (columnWeight == MAX_POSSIBLE_WEIGHT) {
                        topLocation = new Location(i, j, MAX_POSSIBLE_WEIGHT);
                        break findLocationWithMaxWeightLabel;
                    }

                    final int blockWeight = calculateBlockWeight(i, j);
                    if (blockWeight == MAX_POSSIBLE_WEIGHT) {
                        topLocation = new Location(i, j, MAX_POSSIBLE_WEIGHT);
                        break findLocationWithMaxWeightLabel;
                    }

                    final int weight = rowWeight + columnWeight + blockWeight;

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

    @Override
    protected int getNextPossibleValue() {
        final Set<Integer> nonZeros = new HashSet<>();

        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (sudoku[currentRow][j] != ZERO) {
                nonZeros.add(sudoku[currentRow][j]);
            }
        }

        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (sudoku[i][currentColumn] != ZERO) {
                nonZeros.add(sudoku[i][currentColumn]);
            }
        }

        // Collect elements from current block.
        int blockStartRow = (currentRow / BLOCK_SIZE) * BLOCK_SIZE;
        int blockStartColumn = (currentColumn / BLOCK_SIZE) * BLOCK_SIZE;

        final int iEnd = blockStartRow + BLOCK_SIZE;
        final int jEnd = blockStartColumn + BLOCK_SIZE;

        for (int i = blockStartRow; i < iEnd; i++) {
            for (int j = blockStartColumn; j < jEnd; j++) {
                if (sudoku[i][j] != ZERO) {
                    nonZeros.add(sudoku[i][j]);
                }
            }
        }

        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            if (!nonZeros.contains(i)) {
                sudoku[currentRow][currentColumn] = i;
                return i;
            }
        }

        return ZERO;
    }

    @Override
    protected int getNextPossibleValue(Set<Integer> excludedValues) {
        final Set<Integer> nonZeros = new HashSet<>();

        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (sudoku[currentRow][j] != ZERO) {
                nonZeros.add(sudoku[currentRow][j]);
            }
        }

        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (sudoku[i][currentColumn] != ZERO) {
                nonZeros.add(sudoku[i][currentColumn]);
            }
        }

        // Collect elements from current block.
        int blockStartRow = (currentRow / BLOCK_SIZE) * BLOCK_SIZE;
        int blockStartColumn = (currentColumn / BLOCK_SIZE) * BLOCK_SIZE;

        final int iEnd = blockStartRow + BLOCK_SIZE;
        final int jEnd = blockStartColumn + BLOCK_SIZE;

        for (int i = blockStartRow; i < iEnd; i++) {
            for (int j = blockStartColumn; j < jEnd; j++) {
                if (sudoku[i][j] != ZERO) {
                    nonZeros.add(sudoku[i][j]);
                }
            }
        }

        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            if (!excludedValues.contains(i) && !nonZeros.contains(i)) {
                sudoku[currentRow][currentColumn] = i;
                return i;
            }
        }

        return ZERO;
    }

    /**
     * Calculates weight of the current cell.
     * <p>
     * Improvement: if a row has {@code MAX_POSSIBLE_WEIGHT}, it means, we can find missing value immediately.
     * Therefore, we do not need to check other weights and then other cells weight.
     * Instead, we can populate this cell immediately.
     * <p>
     * Same applies to column and block.
     */
    private int calculateWeight(final int i, final int j) {
        final int rowWeight = SudokuUtils.countNonZeroElementsInRow(sudoku, i);
        if (rowWeight == MAX_POSSIBLE_WEIGHT) {
            return RETURN_IMMEDIATELY_WEIGHT;
        }

        final int columnWeight = SudokuUtils.countNonZeroElementsInColumn(sudoku, j);
        if (columnWeight == MAX_POSSIBLE_WEIGHT) {
            return RETURN_IMMEDIATELY_WEIGHT;
        }

        final int blockWeight = calculateBlockWeight(i, j);
        if (blockWeight == MAX_POSSIBLE_WEIGHT) {
            return RETURN_IMMEDIATELY_WEIGHT;
        }

        return rowWeight + columnWeight + blockWeight;
    }

    private int calculateBlockWeight(final int row, final int column) {
        int blockStartRow = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int blockStartColumn = (column / BLOCK_SIZE) * BLOCK_SIZE;

        return SudokuUtils.countNonZeroElementsInBlock(sudoku, blockStartRow, blockStartColumn);
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
