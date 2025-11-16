package org.example.model;

import org.example.model.block.Block;
import org.example.model.block.FallingBlock;
import org.example.model.tetromino.Tetromino;
import org.example.view.GamePanel;
import org.example.view.ViewableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements ViewableModel{
    private Board board;
    private Tetromino tetromino;
    private List<FallingBlock> fallingBlocks = new ArrayList<>();
    private int tick;
    private int score;

    public Model(GamePanel gamePanel, Board board){
        this.board = board;
        spawnTetromino();

        new Thread(() -> {
            double frameTime = 1000000000.0 / GamePanel.FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            while(true){
                long currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / frameTime;
                lastTime = currentTime;
                if(delta >= 1){
                    update();
                    gamePanel.repaint();
                    delta--;
                }
            }
        }).start();
    }

    private void update(){
        if(tick < 60){
            tick++;
            return;
        }
        softDrop();
    }

    private void spawnTetromino(){
        tetromino  = new Tetromino('I', null);
        tetromino.position = new Point((board.cols() - tetromino.size) / 2, 0);
    }

    public void rotateTetromino(boolean clockwise){
        tetromino.rotate(clockwise);
    }

    public void softDrop(){
        moveTetromino(0, 1);
        tick = 0;
    }

    public void moveTetromino(int dx, int dy){
        Tetromino newTetromino = new Tetromino(tetromino);
        newTetromino.position.translate(dx, dy);
        if(validPosition(newTetromino)){
            tetromino = newTetromino;
        }
    }

    private boolean validPosition(Tetromino tetromino){
        for(Point cell : tetromino)
            if(!board.withinBounds(cell.x, cell.y))
                return false;
        return true;
    }

    private void rowsClearedScore(int rowCleared){
        switch(rowCleared){
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }
    }

    private void blocksBlownScore(int blocksBlown){
        score += 10 * (blocksBlown - 1) * blocksBlown;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public Iterable<Block> getBlocks(){
        List<Block> list = new ArrayList<>();
        for(Point cell : tetromino)
            list.add(new Block(cell, tetromino.blockType));
        return list;
    }
}