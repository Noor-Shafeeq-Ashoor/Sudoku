package com.ga.sudoku;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;
import com.ga.sudoku.model.Sudoku;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuApplicationTest {
    @Test
    void testFileNotFoundException() {
        Sudoku sudoku = new Sudoku();
        assertThrows(SudokuFileNotFoundException.class, () -> {
            sudoku.loadPuzzle("non_existent_file.txt");
        });
    }

    @Test
    void testInvalidCharacterException() {
        Sudoku sudoku = new Sudoku();
        assertThrows(InvalidCharacterException.class, () -> {
            sudoku.loadPuzzle("bad_puzzle.txt");
        });
    }

}