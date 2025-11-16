package org.example.model;

import org.example.model.tetromino.Tetromino;
import org.junit.jupiter.api.Test;

public class ModelTest {
    @Test
    void testMoveTetromino() {
        Board board = new Board(5, 10);
        Model model = new Model(board, () -> new Tetromino('T', null));


    }
}