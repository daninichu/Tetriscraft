package org.example.model.tetromino;

import org.example.model.block.BlockFactory;

import java.util.Random;

public class TetrominoFactory {
    private static char[] shapes = new char[]{'O', 'T', 'S', 'Z', 'L', 'J', 'I'};
    private BlockFactory blockFactory;

    public TetrominoFactory(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    public Tetromino createRandomTetromino(){
        int randomIndex = new Random().nextInt(shapes.length);
        return new Tetromino(shapes[randomIndex], blockFactory.createRandomBlock().type);
    }
}