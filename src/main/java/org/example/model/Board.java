package org.example.model;

import org.example.model.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board{
    final Block[][] grid;

    public Board(int rows, int cols){
        if (rows < 1 || cols < 1)
            throw new IllegalArgumentException("rows and cols must be greater than 0");
        grid = new Block[rows][cols];
    }

    public int rows() {
        return grid.length;
    }

    public int cols() {
        return grid[0].length;
    }

    public boolean withinBounds(int row, int col) {
        return row >= 0 && row < rows() && col >= 0 && col < cols();
    }

    public List<Block[]> getClearedRows(){
        List<Block[]> clearedRows = new ArrayList<>();
        for(int row = 0; row < rows(); row++){
            if(rowIsFull(row)){
                clearedRows.add(Arrays.copyOf(grid[row], cols()));
                clearRow(row);
                shiftRows(row);
            }
        }
        return clearedRows;
    }

    public boolean rowIsFull(int row){
        for(int col = 0; col < cols(); col++)
            if(grid[row][col] == null)
                return false;
        return true;
    }

    public void clearRow(int row){
        grid[row] = new Block[cols()];
    }

    public void shiftRows(int lowestRow){
        for(int row = rows() - 1; row > lowestRow; row--){
            grid[row - 1] = grid[row];
        }
        clearRow(rows() - 1);
    }
}