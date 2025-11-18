package org.example.model.tetromino;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tetromino implements Iterable<Point> {
    boolean[][] shape;
    public final String blockType;
    public final int size;
    public Point position = new Point();

    public Tetromino(boolean[][] shape, String blockType) {
        size = shape.length;
        for(boolean[] row : shape)
            if(row.length != size)
                throw new IllegalArgumentException("Shape size mismatch");
        this.shape = shape;
        this.blockType = blockType;
    }

    public Tetromino(char shapeType, String blockType) {
        this(makeShape(shapeType), blockType);
    }

    private static boolean[][] makeShape(char shapeType){
        return switch(shapeType){
            case 'O' -> new boolean[][]{
                    { true,  true},
                    { true,  true},
            };
            case 'T' -> new boolean[][]{
                    {false, false, false},
                    { true,  true,  true},
                    {false,  true, false}
            };
            case 'S' -> new boolean[][]{
                    {false, false, false},
                    {false,  true,  true},
                    { true,  true, false}
            };
            case 'Z' -> new boolean[][]{
                    {false, false, false},
                    { true,  true, false},
                    {false,  true,  true}
            };
            case 'L' -> new boolean[][]{
                    {false, false, false},
                    { true,  true,  true},
                    { true, false, false}
            };
            case 'J' -> new boolean[][]{
                    {false, false, false},
                    { true,  true,  true},
                    {false, false,  true}
            };
            case 'I' -> new boolean[][]{
                    {false, false, false, false},
                    { true,  true,  true,  true},
                    {false, false, false, false},
                    {false, false, false, false}
            };
            default -> throw new IllegalArgumentException("Unexpected value: " + shapeType);
        };
    }

    public void rotate(boolean clockwise){
        boolean[][] newShape = new boolean[size][size];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if (clockwise)
                    newShape[size - 1 - row][size - 1 - col] = shape[col][size - 1 - row];
                else
                    newShape[row][col] = shape[col][size - 1 - row];
            }
        }
        shape = newShape;
    }

    @Override
    public Iterator<Point> iterator(){
        List<Point> cells = new ArrayList<>();
        for(int row = 0; row < size; row++)
            for(int col = 0; col < size; col++)
                if(shape[row][col])
                    cells.add(new Point(position.x + col, position.y + row));
        return cells.iterator();
    }
}