package org.example.model.block;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

public class BlockFactory {
    private static HashMap<String, Function<String, Block>> blocks = new HashMap<>();
    static {
        Stream.of(
                "dirt",
                "stone"
        ).forEach(blockType -> blocks.put(blockType, Block::new));

        Stream.of(
                "sand",
                "gravel"
        ).forEach(blockType -> blocks.put(blockType, FallingBlock::new));
    }

    public Block createBlock(String blockType) {
        Function<String, Block> constructor = blocks.get(blockType);
        if(constructor == null){
            throw new NoSuchElementException("No such block type: " + blockType);
        }
        return constructor.apply(blockType);
    }
}