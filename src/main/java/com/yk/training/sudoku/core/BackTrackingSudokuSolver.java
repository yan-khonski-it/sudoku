package com.yk.training.sudoku.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static com.yk.training.sudoku.core.SudokuValidator.SUDOKU_SIZE;

/**
 * Related links
 * <p>
 * https://leetcode.com/problems/sudoku-solver/
 * <p>
 * https://stackoverflow.com/questions/42320739/sudoku-solver-program
 */
public class BackTrackingSudokuSolver {

    public static final int ZERO = 0;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 9;

    protected final LinkedList<Step> stack = new LinkedList<>();

    protected int currentRow = 0;
    protected int currentColumn = 0;
    protected Step currentStep = null;
    protected int[][] sudoku;

    public void solve(final int[][] sudoku) {
        this.sudoku = sudoku;

        int nextValue;
        while (true) {
            // Nothing to fill.
            if (navigateToZero()) {
                return;
            }

            if (currentStep == null) {
                nextValue = getNextPossibleValue();
            } else {
                nextValue = getNextPossibleValue(currentStep.excludedValues);
            }

            if (nextValue == ZERO) {
                // Back track - revert last step.
                currentStep = stack.pop();
                currentRow = currentStep.row;
                currentColumn = currentStep.column;
                sudoku[currentRow][currentColumn] = ZERO;
            } else {
                if (currentStep == null) {
                    currentStep = new Step(currentRow, currentColumn);
                }

                // Perform next step.
                currentStep.excludedValues.add(nextValue);
                stack.push(currentStep);
            }
        }
    }

    /**
     * Navigates to the next {@code ZERO} location in sudoku.
     * It moves {@code currentStep}.
     */
    protected boolean navigateToZero() {
        if (sudoku[currentRow][currentColumn] == ZERO) {
            return false;
        }

        currentStep = null; // Reset current step.

        for (; currentRow < SUDOKU_SIZE; currentRow++) {
            for (; currentColumn < SUDOKU_SIZE; currentColumn++) {
                if (sudoku[currentRow][currentColumn] == ZERO) {
                    return false;
                }
            }
            currentColumn = 0;
        }

        return true;
    }

    /**
     * @return value to be inserted in current location.
     * {@code ZERO} - means nothing can be inserted; invalid sudoku.
     */
    protected int getNextPossibleValue() {
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            sudoku[currentRow][currentColumn] = i;
            if (SudokuValidator.nonZeroElementsInRowColumnBlockUnique(sudoku, currentRow, currentColumn)) {
                return i;
            }
        }

        sudoku[currentRow][currentColumn] = ZERO;
        return ZERO;
    }

    private int getNextPossibleValue(final Set<Integer> excludedValues) {
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            if (!excludedValues.contains(i)) {
                sudoku[currentRow][currentColumn] = i;
                if (SudokuValidator.nonZeroElementsInRowColumnBlockUnique(sudoku, currentRow, currentColumn)) {
                    return i;
                }
            }
        }

        sudoku[currentRow][currentColumn] = ZERO;
        return ZERO;
    }

    // Represents back-tracking iteration.
    static class Step {

        // Position of the element which we have inserted at given step.
        int row;
        int column;

        // Values that we tried in this step. We should not try them again.
        Set<Integer> excludedValues = new HashSet<>();

        public Step(final int row, final int column) {
            this.row = row;
            this.column = column;
        }
    }
}
