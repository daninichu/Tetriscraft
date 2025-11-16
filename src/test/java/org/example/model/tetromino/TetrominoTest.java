package org.example.model.tetromino;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TetrominoTest{
    @Test
    void testRotateO(){
        boolean[][] rotation = new boolean[][]{
                {true, true},
                {true, true}
        };
        testRotateHelper(new Tetromino('O', null), rotation, rotation, rotation, rotation);
    }

    @Test
    void testRotateT(){
        boolean[][] rotation0 = new boolean[][]{
                {false, false, false},
                { true,  true,  true},
                {false,  true, false}
        };
        boolean[][] rotation1 = new boolean[][]{
                {false, true, false},
                {false, true,  true},
                {false, true, false}
        };
        boolean[][] rotation2 = new boolean[][]{
                {false,  true, false},
                { true,  true,  true},
                {false, false, false}
        };
        boolean[][] rotation3 = new boolean[][]{
                {false, true, false},
                { true, true, false},
                {false, true, false}
        };
        testRotateHelper(new Tetromino('T', null), rotation0, rotation1, rotation2, rotation3);
    }

    @Test
    void testRotateI(){
        boolean[][] rotation0 = new boolean[][]{
                {false, false, false, false},
                { true,  true,  true,  true},
                {false, false, false, false},
                {false, false, false, false}
        };
        boolean[][] rotation1 = new boolean[][]{
                {false, true, false, false},
                {false, true, false, false},
                {false, true, false, false},
                {false, true, false, false}
        };
        boolean[][] rotation2 = new boolean[][]{
                {false, false, false, false},
                {false, false, false, false},
                { true,  true,  true,  true},
                {false, false, false, false}
        };
        boolean[][] rotation3 = new boolean[][]{
                {false, false, true, false},
                {false, false, true, false},
                {false, false, true, false},
                {false, false, true, false}
        };
        testRotateHelper(new Tetromino('I', null), rotation0, rotation1, rotation2, rotation3);
    }

    void testRotateHelper(Tetromino t, boolean[][] rotation0, boolean[][] rotation1, boolean[][] rotation2, boolean[][] rotation3){
        assertTrue(Arrays.deepEquals(rotation0, t.shape));

        t.rotate(false);
        assertTrue(Arrays.deepEquals(rotation1, t.shape));
        t.rotate(false);
        assertTrue(Arrays.deepEquals(rotation2, t.shape));
        t.rotate(false);
        assertTrue(Arrays.deepEquals(rotation3, t.shape));
        t.rotate(false);
        assertTrue(Arrays.deepEquals(rotation0, t.shape));

        t.rotate(true);
        assertTrue(Arrays.deepEquals(rotation3, t.shape));
        t.rotate(true);
        assertTrue(Arrays.deepEquals(rotation2, t.shape));
        t.rotate(true);
        assertTrue(Arrays.deepEquals(rotation1, t.shape));
        t.rotate(true);
        assertTrue(Arrays.deepEquals(rotation0, t.shape));
    }
}