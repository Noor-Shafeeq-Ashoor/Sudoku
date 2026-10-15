package com.ga.sudoku;

import com.ga.sudoku.model.Sudoku;
import com.ga.sudoku.model.SudokuCell;
import com.ga.sudoku.Service.SudokuSolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SudokuApplication {

    public static void main(String[] args) {
        SpringApplication.run(SudokuApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            Sudoku sudoku = new Sudoku();
            SudokuSolver solver = new SudokuSolver();

            System.out.println("=================================================");
            System.out.println("      Welcome to Interactive Spring Sudoku!      ");
            System.out.println("=================================================");

            while (true) {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("1. Load Easy Puzzle (Puzzle 1)");
                System.out.println("2. Load Medium Puzzle (Puzzle 2)");
                System.out.println("3. Load Hard Puzzle (Puzzle 3)");
                System.out.println("4. Let the AI Solve Current Board");
                System.out.println("5. Exit Game");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    sudoku.loadPuzzle("puzzle1.txt");
                    sudoku.startTimer();
                    System.out.println("\nEasy Puzzle Loaded!");
                    sudoku.printBoard();
                }
                else if (choice.equals("2")) {
                    sudoku.loadPuzzle("puzzle2.txt");
                    sudoku.startTimer();
                    System.out.println("\nMedium Puzzle Loaded!");
                    sudoku.printBoard();
                }
                else if (choice.equals("3")) {
                    sudoku.loadPuzzle("puzzle3.txt");
                    sudoku.startTimer();
                    System.out.println("\nHard Puzzle Loaded!");
                    sudoku.printBoard();
                }
                else if (choice.equals("4")) {
                    System.out.println("\n--- AI Solver Activated ---");
                    boolean success = solver.solve(sudoku);
                    if (success) {
                        sudoku.printBoard();
                        System.out.println("AI Completed the Board in " + sudoku.getElapsedTimeInSeconds() + " seconds!");
                        System.out.println("Final Calculated Score: " + sudoku.calculateScore());
                    } else {
                        System.out.println("No solution possible from this state.");
                    }
                }
//                else if (choice.equals("5")) {
//                    // Manual Input & Validation Workflow
//                    System.out.print("Enter row (1-9): ");
//                    int row = Integer.parseInt(scanner.nextLine().trim()) - 1;
//                    System.out.print("Enter column (1-9): ");
//                    int col = Integer.parseInt(scanner.nextLine().trim()) - 1;
//                    System.out.print("Enter number (1-9) or 0 to clear: ");
//                    int val = Integer.parseInt(scanner.nextLine().trim());
//
//                    // Input Validation
//                    if (val < 0 || val > 9 || row < 0 || row > 8 || col < 0 || col > 8) {
//                        System.out.println(" Invalid parameters! Numbers must be 1-9.");
//                        continue;
//                    }

//                    try {
//                        sudoku.setCell(row, col, new SudokuCell(val));
//                        System.out.println("\nMove registered successfully!");
//                        sudoku.printBoard();
//                        System.out.println("Time Elapsed: " + sudoku.getElapsedTimeInSeconds() + " seconds");
//                    } catch (IllegalStateException e) {
//                        System.out.println(" Move Failed: " + e.getMessage());
//                    }
//                }
                else if (choice.equals("5")) {
                    System.out.println("Thanks for playing!");
                    break;
                }
                else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            scanner.close();
        };
    }
}
