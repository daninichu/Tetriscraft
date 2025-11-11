package org.example.model;

import org.example.model.block.Block;
import org.example.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class Board extends Grid<Block>{
    public Board(int rows, int cols){
        super(rows, cols);
    }

    public List<Integer> checkRows(){
        List<Integer> clearedRows = new ArrayList<>();
        for(int row = 0; row < rows(); row++){
            if (!rowIsEmpty(row)) {
                clearRow(row);
                shiftRows(row);
                clearedRows.add(row);
            }
        }
        return clearedRows;
    }

    public boolean rowIsEmpty(int row) {
        for (int col = 0; col < cols(); col++)
            if(grid[row][col] != null)
                return false;
        return true;
    }

    public void clearRow(int row){
        for(int col = 0; col < cols(); col++)
            set(row, col, null);
    }

    public void shiftRows(int lowestRow){
        for(int row = rows() - 1; row > lowestRow; row--){
//            grid[row - 1] = grid[row];
        }
    }
}