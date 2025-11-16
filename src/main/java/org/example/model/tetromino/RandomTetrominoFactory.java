package org.example.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory{
    private static char[] shapes = new char[]{'O', 'T', 'S', 'Z', 'L', 'J', 'I'};
    private String[] blockTypes;

    public RandomTetrominoFactory(String[] blockTypes) {
        this.blockTypes = blockTypes;
    }

    @Override
    public Tetromino createTetromino(){
        char shape = shapes[new Random().nextInt(shapes.length)];
        String blockType = blockTypes == null? null : blockTypes[new Random().nextInt(blockTypes.length)];
        return new Tetromino(shape, blockType);
    }
}