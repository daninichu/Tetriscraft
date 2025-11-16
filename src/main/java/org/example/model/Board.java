package org.example.model;

import org.example.model.block.Block;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Block> {
    private final Block[][] grid;

    public Board(int cols, int rows){
        if (cols < 1 || rows < 1)
            throw new IllegalArgumentException("cols and rows must be greater than 0");
        grid = new Block[cols][rows];
    }

    public int cols() {
        return grid.length;
    }

    public int rows() {
        return grid[0].length;
    }

    public Block get(Point p){
        return grid[p.x][p.y];
    }

    public void set(Point p, Block block){
        grid[p.x][p.y] = block;
        block.position = p;
    }

    public boolean withinBounds(int col, int row) {
        return (col >= 0 && col < cols()) && (row >= 0 && row < rows());
    }

    public List<Block[]> getClearedRows(){
        List<Block[]> clearedRows = new ArrayList<>();
        for(int row = rows() - 1; row >= 0; row--){
            if(!rowIsFull(row))
                continue;
            Block[] blocks = new Block[cols()];
            for(int col = 0; col < cols(); col++){
                blocks[col] = grid[col][row];
            }
            clearedRows.add(blocks);

            clearRow(row);
            shiftRows(row);
        }
        return clearedRows;
    }

    public boolean rowIsFull(int row){
        for(int col = 0; col < cols(); col++)
            if(grid[col][row] == null)
                return false;
        return true;
    }

    public void clearRow(int row){
        for(int col = 0; col < cols(); col++)
            grid[col][row] = null;
    }

    public void shiftRows(int bottomRow){
        for(int row = bottomRow; row > 0; row--)
            for(int col = 0; col < cols(); col++)
                grid[col][row] = grid[col][row - 1];
        clearRow(0);
    }

    @Override
    public Iterator<Block> iterator(){
        List<Block> list = new ArrayList<>();
        for(int row = 0; row < rows(); row++){
            for(int col = 0; col < cols(); col++){
                Block block = grid[col][row];
                if(block != null){
                    block.position = new Point(col, row);
                    list.add(block);
                }
            }
        }
        return list.iterator();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < rows(); row++){
            sb.append('[');
            for(int col = 0; col < cols(); col++)
                sb.append(grid[col][row] == null? ' ' : '*');
            sb.append("]\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}