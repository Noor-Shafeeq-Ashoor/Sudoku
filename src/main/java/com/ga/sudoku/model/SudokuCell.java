package com.ga.sudoku.model;

public class SudokuCell {
    private int value;
    private final boolean isHint;

    public SudokuCell(int value){
        this.value=value;
        this.isHint=(value!=0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (isHint) {
            throw new IllegalStateException("Cannot change a starting hint cell!");
        }
        this.value = value;
    }

    public boolean isHint() {
        return isHint;
    }
}
