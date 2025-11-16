package org.example.view;

import org.example.model.block.Block;

public interface ViewableModel {
    int getScore();

    Iterable<Block> getBlocks();
}