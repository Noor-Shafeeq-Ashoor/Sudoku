package com.ga.sudoku.model;

public class Sudoku {
    private final SudokuCell[][] board = new SudokuCell[9][9];

    public SudokuCell getCell(int row, int col){
        return board[row][col];
    }

    public void setCell(int row, int col, SudokuCell cell){
        this.board[row][col] = cell;
    }

    public void printBoard(){
        for (int i = 0;i<9; i++ ){
            if(i%3==0 && i !=0){
                System.out.println("----------------------------------");
            }
            for (int j = 0 ; j < 9 ; j++){
                if (j % 3 == 0 && j !=0 ){
                    System.out.println("|  ");
                }
                int val = (board[i][j] != null) ? board[i][j].getValue() : 0;
                System.out.print(val + "  ");
            }
            System.out.println();
        }

    }
}
