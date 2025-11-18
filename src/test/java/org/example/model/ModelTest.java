package org.example.model;

import org.example.model.tetromino.Tetromino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {
    @Test
    void testMoveTetromino() {
        Board board = new Board(5, 3);
        Model model = new Model(board, () -> new Tetromino('T', null));

        assertEquals(String.join("\n",
                "[ *** ]",
                "[  *  ]",
                "[     ]"
        ), model.toString());

        model.moveTetromino(-1, 0);
        assertEquals(String.join("\n",
                "[***  ]",
                "[ *   ]",
                "[     ]"
        ), model.toString());

        model.moveTetromino(0, 1);
        assertEquals(String.join("\n",
                "[     ]",
                "[***  ]",
                "[ *   ]"
        ), model.toString());

        model.moveTetromino(1, 0);
        assertEquals(String.join("\n",
                "[     ]",
                "[ *** ]",
                "[  *  ]"
        ), model.toString());
    }

    @Test
    void testTetrominoInsideBoard() {
        Board board = new Board(5, 3);
        Model model = new Model(board, () -> new Tetromino('S', null));

        model.moveTetromino(-1, 0);
        assertEquals(String.join("\n",
                "[ **  ]",
                "[**   ]",
                "[     ]"
        ), model.toString());

        model.moveTetromino(-1, 0);
        assertEquals(String.join("\n",
                "[ **  ]",
                "[**   ]",
                "[     ]"
        ), model.toString());

        model.moveTetromino(2, 0);
        assertEquals(String.join("\n",
                "[   **]",
                "[  ** ]",
                "[     ]"
        ), model.toString());

        model.moveTetromino(1, 0);
        assertEquals(String.join("\n",
                "[   **]",
                "[  ** ]",
                "[     ]"
        ), model.toString());
    }

    @Test
    void testRotateTetromino() {
        Board board = new Board(5, 4);
        Model model = new Model(board, () -> new Tetromino('S', null));

        model.moveTetromino(0, 1);
        assertEquals(String.join("\n",
                "[     ]",
                "[  ** ]",
                "[ **  ]",
                "[     ]"
        ), model.toString());

        model.rotateTetromino(false);
        assertEquals(String.join("\n",
                "[  *  ]",
                "[  ** ]",
                "[   * ]",
                "[     ]"
        ), model.toString());

        model.rotateTetromino(false);
        assertEquals(String.join("\n",
                "[  ** ]",
                "[ **  ]",
                "[     ]",
                "[     ]"
        ), model.toString());

        model.rotateTetromino(false);
        assertEquals(String.join("\n",
                "[ *   ]",
                "[ **  ]",
                "[  *  ]",
                "[     ]"
        ), model.toString());
    }

    @Test
    void testWallKick() {
        Board board = new Board(5, 4);
        Model model = new Model(board, () -> new Tetromino('I', null));

        assertEquals(String.join("\n",
                "[**** ]",
                "[     ]",
                "[     ]",
                "[     ]"
        ), model.toString());

        model.rotateTetromino(false);
        assertEquals(String.join("\n",
                "[ *   ]",
                "[ *   ]",
                "[ *   ]",
                "[ *   ]"
        ), model.toString());

        model.moveTetromino(-1, 0);
        assertEquals(String.join("\n",
                "[*    ]",
                "[*    ]",
                "[*    ]",
                "[*    ]"
        ), model.toString());

        model.rotateTetromino(false);
        assertEquals(String.join("\n",
                "[     ]",
                "[     ]",
                "[**** ]",
                "[     ]"
        ), model.toString());
    }
}