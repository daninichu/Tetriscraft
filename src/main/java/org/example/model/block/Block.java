package org.example.model.block;

import java.awt.*;

public class Block {
    public final String type;
    public Point position;

    public Block(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type + "(" + position.x + "," + position.y + ")";
    }
}