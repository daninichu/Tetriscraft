package org.example.model.tetrimino;

import java.awt.*;

public class Tetrimino {
    public enum Shape{
        T, S, Z, L, J, O, I
    }
    public final String block;
    private final Shape shape;
    private Point position;

    public Tetrimino(Shape shape, String block) {
        this.shape = shape;
        this.block = block;
    }
}