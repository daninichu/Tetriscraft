package org.example.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Textures {
    private static HashMap<String, BufferedImage> textures = new HashMap<>();

    private static void load(String fileName){
        try{
            textures.put(fileName, ImageIO.read(new File("src/main/resources/blocks/" + fileName + ".png")));
        } catch(IOException e){
            throw new RuntimeException("Failed to load texture: " + fileName, e);
        }
    }

    static BufferedImage get(String fileName){
        if(!textures.containsKey(fileName))
            load(fileName);
        return textures.get(fileName);
    }
}