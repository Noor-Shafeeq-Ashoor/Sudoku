// to solve the sudoku I will use backtracking algorithm.

package com.ga.sudoku.Service;
import com.ga.sudoku.model.Sudoku;
import com.ga.sudoku.model.SudokuCell;

public class SudokuSolver {
    // the method is Recursive --> call it's self from inside + set Backtracking to move and try numbers btw الخانات
    public boolean solve(Sudoku sudoku) {
        return solveBoard(sudoku, 0, 0);
    }
    private boolean solveBoard(Sudoku sudoku, int row, int col) {
        // move down to the beginning of the next row
        if (col == 9) {
            row++;
            col = 0;
        }

        if (row == 9) {
            return true;
        }
        SudokuCell currentCell = sudoku.getCell(row, col);
        if (currentCell != null && currentCell.isHint()) {
            return solveBoard(sudoku, row, col + 1);
        }

        // Try placing digits from 1 to 9 in the empty space
        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(sudoku, row, col, num)) {
                sudoku.setCell(row, col, new SudokuCell(num));

                if (solveBoard(sudoku, row, col + 1)) {
                    return true;
                }

                // BACKTRACK --> If the choice failed down the line, reset the cell back to empty (0)
                sudoku.setCell(row, col, new SudokuCell(0));
            }
        }
        return false;
    }


    private boolean isValidPlacement(Sudoku sudoku, int row, int col, int num) {
        // Look across the entire row
        for (int c = 0; c < 9; c++) {
            SudokuCell cell = sudoku.getCell(row, c);
            if (cell != null && cell.getValue() == num) {
                return false;
            }
        }

        // Look down the entire column
        for (int r = 0; r < 9; r++) {
            SudokuCell cell = sudoku.getCell(r, col);
            if (cell != null && cell.getValue() == num) {
                return false;
            }
        }


        int subGridRowStart = (row / 3) * 3;
        int subGridColStart = (col / 3) * 3;

        for (int r = subGridRowStart; r < subGridRowStart + 3; r++) {
            for (int c = subGridColStart; c < subGridColStart + 3; c++) {
                SudokuCell cell = sudoku.getCell(r, c);
                if (cell != null && cell.getValue() == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
