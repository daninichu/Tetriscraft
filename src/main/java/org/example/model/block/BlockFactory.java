package org.example.model.block;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class BlockFactory {
    static Random rand = new Random();
    private HashMap<String, Function<String, Block>> blocks = new HashMap<>();

    public BlockFactory() {
        Stream.of(
                "dirt",
                "stone"
        ).forEach(blockType -> blocks.put(blockType, Block::new));

        Stream.of(
                "sand",
                "gravel"
        ).forEach(blockType -> blocks.put(blockType, FallingBlock::new));
    }

    public Block createRandomBlock(){
        String blockType = "sand";
        Function<String, Block> constructor = blocks.get(blockType);
        if(constructor == null){
            throw new NoSuchElementException("No such block type: " + blockType);
        }
        return constructor.apply(blockType);
    }
}