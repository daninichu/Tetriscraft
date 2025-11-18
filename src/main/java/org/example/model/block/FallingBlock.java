package org.example.model.block;

public class FallingBlock extends Block {
    public double yOffset;
    public double velocity;

    public FallingBlock(String type){
        super(type);
    }
}