package com.yk.training.sudoku.app;

import com.yk.training.sudoku.core.BackTrackingRecursiveSolver;
import com.yk.training.sudoku.core.BackTrackingSudokuSolver;
import com.yk.training.sudoku.core.BackTrackingWeightedSudokuSolver;
import com.yk.training.sudoku.core.SudokuSolver;

import java.util.List;

import static com.yk.training.sudoku.utils.MatrixUtils.readMatrix;

public class App {

    public void execute() {
        final int[][] easy1 = readMatrix("easy1.txt");
        final int[][] easy2 = readMatrix("easy2.txt");
        final int[][] hard2 = readMatrix("hard2.txt");
        final int[][] evil4 = readMatrix("evil4.txt");
        final int[][] evil5 = readMatrix("evil5.txt");
        final int[][] test1 = readMatrix("test1.txt");

        compareSolutions(easy1, "easy1");
        compareSolutions(easy2, "easy2");
        compareSolutions(hard2, "hard2");
        compareSolutions(evil5, "evil5");

        compareSolutions(evil4, "evil4",
                // BackTrackingSudokuSolver will not solve it.
                // BackTrackingRecursiveSolver - 17 seconds
                List.of(new BackTrackingRecursiveSolver(), new BackTrackingWeightedSudokuSolver()));


        compareSolutions(test1, "test1");
    }

    private void compareSolutions(final int[][] sudoku, final String name) {
        final List<SudokuSolver> sudokuSolvers = List.of(new BackTrackingSudokuSolver(),
                new BackTrackingRecursiveSolver(), new BackTrackingWeightedSudokuSolver());
        compareSolutions(sudoku, name, sudokuSolvers);
    }

    private void compareSolutions(final int[][] sudoku, final String name, final List<SudokuSolver> sudokuSolvers) {
        System.out.println(name + ":");
        for (SudokuSolver sudokuSolver : sudokuSolvers) {
            final SudokuTask sudokuTask = SudokuTask.of(sudoku, name, sudokuSolver);
            sudokuTask.run();
            System.out.println(sudokuSolver.getClass().getSimpleName() + ": " + sudokuTask.getExecutionMs());
        }

        System.out.println("\n");
    }
}
