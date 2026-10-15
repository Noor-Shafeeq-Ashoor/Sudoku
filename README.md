# Sudoku Spring Boot Mini Project Documentation


## 1. Project Workflow & Architecture

The application is structured using robust Object-Oriented Programming (OOP) and architectural principles separation within a **Spring Boot** environment.

```text
src/main/java/com/ga/sudoku/
├── exception/
│   ├── SudokuFileNotFoundException.java
│   └── InvalidCharacterException.java
├── model/
│   ├── SudokuCell.java
│   └── Sudoku.java
├── Service/
│   └── SudokuSolver.java
└── SudokuApplication.java (Main)
```

### Executive Flow
1. **File Processing Phase:** The application uses an `InputStreamReader` to fetch target puzzles directly from the project's internal classpath resources.
2. **Validation & Mapping:** The raw structural tokens (`|`, `-`) are stripped out. The data engine verifies text configurations line by line. Any bad character automatically trips an exception. Valid entries populate a native matrix array.
3. **Interactive Processing Loop:** The framework displays an option menu to play.

---

## 2.Solving Algorithm: Recursive Backtracking
The engine implements a **Depth-First Search (DFS) Backtracking Algorithm** to automatically solve the matrix. 

```text
Algorithm: solveBoard(row, col)
----------------------------------------
1. If col == 9: Move to next line -> set row = row + 1, col = 0
2. If row == 9: The matrix is successfully solved -> Return TRUE
3. If board[row][col] is a fixed Starting Hint:
       Return solveBoard(row, col + 1)
4. For each Digit from 1 to 9:
       If isValidPlacement(row, col, Digit):
           Set board[row][col] = Digit
           If solveBoard(row, col + 1) == TRUE:
               Return TRUE
           Set board[row][col] = 0 (BACKTRACK: reset and try next digit)
5. Return FALSE (Trigger upstream matrix value re-evaluation)
```

---

## 3. Implemented Interactive Bonus Features

To expand the baseline utility of the system, several interactive behaviors were integrated directly into the terminal environment:

*   **Difficulty Scaling:** Puzzles are cataloged into **Easy** (Puzzle 1), **Medium** (Puzzle 2), and **Hard** (Puzzle 3) profiles depending on the total concentration of baseline starting clues.
*   **Active Runtime Engine Timer:** Uses Unix timestamp deltas (`System.currentTimeMillis()`) to track exactly how long it takes to interact with or clear a loaded matrix layout.
*   **Dynamic Performance Scoring:** Computes an execution grade for completed boards.
*   **Automated Solution File Exporter:** Formats the computed solution array and writes a clean ASCII file named `puzzleX.solution.txt` back to the root folder directory upon a successful run.
