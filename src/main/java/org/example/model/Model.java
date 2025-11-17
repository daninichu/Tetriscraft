package org.example.model;

import org.example.model.block.Block;
import org.example.model.block.BlockFactory;
import org.example.model.block.FallingBlock;
import org.example.model.tetromino.TetrominoFactory;
import org.example.model.tetromino.Tetromino;
import org.example.view.GamePanel;
import org.example.view.ViewableModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Model implements ViewableModel{
    private Board board;
    private TetrominoFactory tetrominoFactory;
    private BlockFactory blockFactory = new BlockFactory();
    private Tetromino tetromino;
    private List<FallingBlock> fallingBlocks = new ArrayList<>();
    private int tick;
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
        if(tick < 60){
            tick++;
            return;
        }
        softDrop();
    }

    private void spawnTetromino(){
        tetromino = tetrominoFactory.createTetromino();
        tetromino.position = new Point((board.cols() - tetromino.size) / 2, 0);
        while(moveTetromino(0, -1)){}
    }

    public void rotateTetromino(boolean clockwise){
        tetromino.rotate(clockwise);
        if(!validPosition(tetromino)){
            tetromino.rotate(!clockwise);
        }
    }

    public boolean softDrop(){
        System.out.println(this);
        System.out.println();
        tick = 0;
        if(!moveTetromino(0, 1)){
            lockTetromino();
            spawnTetromino();
            return false;
        }
        return true;
    }

    public void hardDrop(){
        while(softDrop()){}
    }

    private void lockTetromino(){
        for(Point cell : tetromino)
            board.set(cell, blockFactory.createBlock(tetromino.blockType));
        board.getClearedRows();
    }

    public boolean moveTetromino(int dx, int dy){
        Tetromino newTetromino = new Tetromino(tetromino);
        newTetromino.position.translate(dx, dy);
        if(validPosition(newTetromino)){
            tetromino = newTetromino;
            return true;
        }
        return false;
    }

    private boolean validPosition(Tetromino tetromino){
        for(Point cell : tetromino){
            if(!board.withinBounds(cell.x, cell.y))
                return false;
            if(board.get(cell) != null)
                return false;
        }
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
        for(Point cell : tetromino){
            Block block = new Block(tetromino.blockType);
            block.position = cell;
            list.add(block);
        }
        for(Block block : board)
            list.add(block);
        return list;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        List<Point> tetrominoCells = new ArrayList<>();
        for(Point cell : tetromino){
            tetrominoCells.add(cell);
        }
        for(int row = 0; row < board.rows(); row++){
            sb.append("[");
            for(int col = 0; col < board.cols(); col++){
                Point cell = new Point(col, row);
                if(tetrominoCells.contains(cell))
                    sb.append("#");
                else
                    sb.append(board.get(cell) == null? " " : "*");
            }
            sb.append("]\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}