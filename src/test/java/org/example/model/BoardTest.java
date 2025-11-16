package org.example.model;

import org.example.model.block.Block;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void testCorrectDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 0));
        Board board = new Board(10, 20);
        assertEquals(20, board.rows());
        assertEquals(10, board.cols());
    }

    @Test
    void testEmpty() {
        Board board = new Board(10, 20);
        for (int row = 0; row < board.rows(); row++){
            assertFalse(board.rowIsFull(row));
            for(int col = 0; col < board.cols(); col++)
                assertNull(board.get(new Point(col, row)));
        }
    }

    @Test
    void testBounds(){
        Board board = new Board(10, 20);
        // Inside board
        for(int row = 0; row < board.rows(); row++){
            for(int col = 0; col < board.cols(); col++)
                assertTrue(board.withinBounds(col, row));
        }
        // Above board
        for(int row = -board.rows(); row < 0; row++){
            for(int col = 0; col < board.cols(); col++)
                assertFalse(board.withinBounds(col, row));
        }
        // Below board
        for(int row = board.rows(); row < 2 * board.rows(); row++){
            for(int col = 0; col < board.cols(); col++)
                assertFalse(board.withinBounds(col, row));
        }
        // Left of board
        for(int row = 0; row < board.rows(); row++){
            for(int col = -board.cols(); col < 0; col++)
                assertFalse(board.withinBounds(col, row));
        }
        // Right of board
        for(int row = 0; row < board.rows(); row++){
            for(int col = board.cols(); col < 2 * board.cols(); col++)
                assertFalse(board.withinBounds(col, row));
        }
    }

    static Board createFromString(String str) {
        String[] lines = str.split("\n");
        for (int i = 0; i < lines.length; i++)
            lines[i] = lines[i].substring(1, lines[i].length() - 1);

        Board board = new Board(lines[0].length(), lines.length);
        for (int row = 0; row < board.rows(); row++)
            for(int col = 0; col < board.cols(); col++)
                if(lines[row].charAt(col) == '*')
                    board.set(new Point(col, row), new Block(null));
        return board;
    }

    @Test
    void testClearRows() {
        BiConsumer<String, String> clearingTester = (beforeClearing, afterClearing) -> {
            Board board = createFromString(beforeClearing);
            assertEquals(beforeClearing, board.toString());

            board.getClearedRows();
            assertEquals(afterClearing, board.toString());
        };
        clearingTester.accept(String.join("\n",
                "[    *]",
                "[    *]",
                "[    *]",
                "[    *]",
                "[*****]"
        ), String.join("\n",
                "[     ]",
                "[    *]",
                "[    *]",
                "[    *]",
                "[    *]"
        ));

        clearingTester.accept(String.join("\n",
                "[ ****]",
                "[*****]",
                "[ ****]",
                "[*****]",
                "[ ****]"
        ), String.join("\n",
                "[     ]",
                "[     ]",
                "[ ****]",
                "[ ****]",
                "[ ****]"
        ));

        clearingTester.accept(String.join("\n",
                "[**** ]",
                "[*****]",
                "[***  ]",
                "[*****]",
                "[**   ]",
                "[*****]",
                "[*    ]"
        ), String.join("\n",
                "[     ]",
                "[     ]",
                "[     ]",
                "[**** ]",
                "[***  ]",
                "[**   ]",
                "[*    ]"
        ));
    }
}