package org.example.model;

import org.example.model.block.Block;
import org.example.model.block.BlockFactory;
import org.example.model.block.FallingBlock;
import org.example.model.block.TNTBlock;
import org.example.model.tetromino.Tetromino;
import org.example.model.tetromino.TetrominoFactory;
import org.example.view.GamePanel;
import org.example.view.ViewableModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Model implements ViewableModel{
    private Board board;
    private TetrominoFactory tetrominoFactory;
    private BlockFactory blockFactory = new BlockFactory();
    private Tetromino tetromino;
    private List<FallingBlock> fallingBlocks = new ArrayList<>();
    private List<TNTBlock> tntBlocks = new ArrayList<>();
    private int tick;
    private int floorKickCount;
    private int score;

    public Model(Board board, TetrominoFactory tetrominoFactory){
        this.board = board;
        this.tetrominoFactory = tetrominoFactory;
        spawnTetromino();
    }

    public void launch(GamePanel gamePanel){
        new GameThread(gamePanel, this).start();
    }

    void update(){
            updateGravity();
        if(!fallingBlocks.isEmpty()){
            return;
        }
        if(tick++ < 60){
            return;
        }
        softDrop();
    }

    private void updateGravity(){
        fallingBlocks.sort(Comparator.comparingInt((b) -> -b.position.y));
        double gravity = 1;
        for(FallingBlock block : fallingBlocks){
            Point original = new Point(block.position);
            Point below = new Point(block.position.x, block.position.y + 1);

            if(!board.withinBounds(below) || board.get(below) != null){
                block.yOffset = 0;
                block.velocity = 0;
                continue;
            }
            block.velocity = Math.min(block.velocity + gravity, 10);
            block.yOffset += block.velocity;

            if (block.yOffset >= Block.SIZE) {
                board.set(below, block);
                board.set(original, null);
                block.yOffset -= Block.SIZE;
            }
        }
    }

    private void spawnTetromino(){
        floorKickCount = 0;
        tetromino = tetrominoFactory.createTetromino();
        tetromino.position = new Point((board.cols() - tetromino.size)/2, 0);
        while(moveTetromino(0, -1)){}
    }

    public void rotateTetromino(boolean clockwise){
        tetromino.rotate(clockwise);
        if(validPosition(tetromino)){
            return;
        }
        List<Point> offsets = new ArrayList<>();
        for(int i = 1; i <= tetromino.size/2; i++){
            offsets.add(new Point(0, i));
            offsets.add(new Point(clockwise? i : -i, 0));
            offsets.add(new Point(clockwise? -i : i, 0));
        }
        if(wallKick(offsets)){
            return;
        }
        offsets.clear();
        for(int i = 1; i <= tetromino.size/2; i++){
            offsets.add(new Point(0, -i));
        }
        if(floorKickCount < 5 && wallKick(offsets)){
            floorKickCount++;
            return;
        }
        tetromino.rotate(!clockwise);
    }

    private boolean wallKick(Iterable<Point> offsets){
        Point original = new Point(tetromino.position);
        for(Point offset : offsets){
            tetromino.position.setLocation(original.x + offset.x, original.y + offset.y);
            if(validPosition(tetromino))
                return true;
        }
        tetromino.position = original;
        return false;
    }

    public void softDrop(){
        tick = 0;
        if(!moveTetromino(0, 1)){
            lockTetromino();
            spawnTetromino();
        }
    }

    public void hardDrop(){
        tick = 0;
        while(moveTetromino(0, 1)){}
        lockTetromino();
        spawnTetromino();
    }

    private void lockTetromino(){
        for(Point cell : tetromino)
            board.set(cell, new FallingBlock("sand"));
        for(Block block : board){
            if(block instanceof FallingBlock fallingBlock){
                fallingBlocks.add(fallingBlock);
            }
        }
//        board.getClearedRows();
    }

    public boolean moveTetromino(int dx, int dy){
        tetromino.position.translate(dx, dy);
        if(validPosition(tetromino))
            return true;
        tetromino.position.translate(-dx, -dy);
        return false;
    }

    private boolean validPosition(Tetromino tetromino){
        for(Point cell : tetromino)
            if(!board.withinBounds(cell) || board.get(cell) != null)
                return false;
        return true;
    }

    private static int rowsClearedScore(int n){
        return 100*(1 + n*n/2);
    }

    private static int blocksBlownScore(int n){
        return 20*(1 + (n - 1)*n);
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public Iterable<Block> getBlocks(){
        List<Block> list = new ArrayList<>();
        for(Point cell : tetromino){
            Block block = new FallingBlock("sand");
            block.position = cell;
            list.add(block);
        }
        for(Block block : board)
            list.add(block);
        return list;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(board.toString());
        for(Point p : tetromino)
            sb.setCharAt(1 + p.x + p.y*(board.cols() + 3), '*');
        return sb.toString();
    }
}