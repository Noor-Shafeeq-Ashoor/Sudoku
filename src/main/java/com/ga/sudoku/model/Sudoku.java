package com.ga.sudoku.model;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;

import java.io.*;

public class Sudoku {
    private long startTime;
    private int hintsUsed = 0;
    private final SudokuCell[][] board = new SudokuCell[9][9];

    public SudokuCell getCell(int row, int col){
        return board[row][col];
    }

    public void setCell(int row, int col, SudokuCell cell){
        this.board[row][col] = cell;
    }


   // 2- READ THE TEXT FILE and map it to the cells grid
   public void loadPuzzle(String filePath) {
       File file = new File(filePath);
       if (!file.exists()) {
           throw new SudokuFileNotFoundException("The game file at '" + filePath + "' could not be found.");
       }

       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
           String line;
           int gridRow = 0;

           while ((line = reader.readLine()) != null && gridRow < 9) {
               line = line.trim();

               if (line.isEmpty() || line.startsWith("-")) {
                   continue;
               }

               String cleanedLine = line.replace("|", "").replaceAll("\\s+", "");

               if (cleanedLine.length() != 9) {
                   continue;
               }

               for (int gridCol = 0; gridCol < 9; gridCol++) {
                   char ch = cleanedLine.charAt(gridCol);

                   if (!Character.isDigit(ch)) {
                       throw new InvalidCharacterException("Illegal character '" + ch + "' detected in puzzle file.");
                   }

                   int numericValue = Character.getNumericValue(ch);
                   board[gridRow][gridCol] = new SudokuCell(numericValue);
               }
               gridRow++;
           }
       }
       catch (IOException e) {
           throw new RuntimeException("An error occurred while reading the file: " + e.getMessage(), e);
       }
   }


    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|  ");
                }
                int val = (board[i][j] != null) ? board[i][j].getValue() : 0;
                System.out.print(val + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void saveSolution(String targetFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath))) {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i != 0) {
                    writer.write("----------------------------------");
                    writer.newLine();
                }
                StringBuilder rowString = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j != 0) {
                        rowString.append("|  ");
                    }
                    int val = (board[i][j] != null) ? board[i][j].getValue() : 0;
                    rowString.append(val).append("  ");
                }
                writer.write(rowString.toString().trim());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while exporting the solution file: " + e.getMessage(), e);
        }
    }
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public long getElapsedTimeInSeconds() {
        return (System.currentTimeMillis() - this.startTime) / 1000;
    }

    public int calculateScore() {
        long seconds = getElapsedTimeInSeconds();
        // Base score minus penalties for time and hints used
        int score = 2000 - (int)(seconds * 2) - (hintsUsed * 150);
        return Math.max(score, 100); // Floor it at a minimum of 100 points
    }

    public void incrementHints() {
        this.hintsUsed++;
    }
}
