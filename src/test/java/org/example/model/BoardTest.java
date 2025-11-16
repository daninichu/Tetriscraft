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
        assertEquals(20, board.rows());
        assertEquals(10, board.cols());
    }

    @Test
    void testEmpty() {
        for (int row = 0; row < board.rows(); row++){
            assertFalse(board.rowIsFull(row));
            for(int col = 0; col < board.cols(); col++)
                assertNull(board.grid[row][col]);
        }
    }

    @Test
    void testBounds(){
        for(int row = 0; row < board.rows(); row++){
            for(int col = 0; col < board.cols(); col++)
                assertTrue(board.withinBounds(row, col));
        }
        // Above board
        for(int row = -board.rows(); row < 0; row++){
            for(int col = 0; col < board.cols(); col++)
                assertFalse(board.withinBounds(row, col));
        }
        // Below board
        for(int row = board.rows(); row < 2 * board.rows(); row++){
            for(int col = 0; col < board.cols(); col++)
                assertFalse(board.withinBounds(row, col));
        }
        // Left of board
        for(int row = 0; row < board.rows(); row++){
            for(int col = -board.cols(); col < 0; col++)
                assertFalse(board.withinBounds(row, col));
        }
        // Right of board
        for(int row = 0; row < board.rows(); row++){
            for(int col = board.cols(); col < 2 * board.cols(); col++)
                assertFalse(board.withinBounds(row, col));
        }
    }
}