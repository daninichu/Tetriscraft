package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(20, 10);
    }

    @Test
    void testCorrectDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Board(1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 20));
        assertEquals(20, board.rows());
        assertEquals(10, board.cols());
        for (int row = 0; row < board.rows(); row++)
            for(int col = 0; col < board.cols(); col++)
                assertTrue(board.withinBounds(row, col));
    }

    @Test
    void testEmpty() {
        for (int row = 0; row < board.rows(); row++){
            assertTrue(board.rowIsEmpty(row));
            for(int col = 0; col < board.cols(); col++)
                assertNull(board.get(row, col));
        }
    }
}