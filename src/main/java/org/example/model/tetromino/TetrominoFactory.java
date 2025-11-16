package org.example.model.tetromino;

@FunctionalInterface
public interface TetrominoFactory{
    Tetromino createTetromino();
}