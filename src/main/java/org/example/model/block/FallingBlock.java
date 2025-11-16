package org.example.model.block;

import java.awt.*;

public class FallingBlock extends Block {
    public double yOffset;

    public FallingBlock(Point cell, String type){
        super(cell, type);
    }
}