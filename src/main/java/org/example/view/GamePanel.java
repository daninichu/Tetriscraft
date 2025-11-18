package org.example.view;

import org.example.controller.Controller;
import org.example.model.Board;
import org.example.model.Model;
import org.example.model.block.Block;
import org.example.model.tetromino.RandomTetrominoFactory;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel{
    public static final int FPS = 60;
    public static final int BLOCK_SIZE = 32;
    private int boardX, boardY, boardWidth, boardHeight;
    private ViewableModel model;

    public GamePanel(){
        Board board = new Board(10, 20);
        String[] blockTypes = new String[]{"dirt", "stone", "stone", "sand", "obsidian", "planks_oak", "planks_oak"};
        Model model = new Model(board, new RandomTetrominoFactory(blockTypes));
        model.launch(this);
        this.model = model;

        addKeyListener(new Controller(model));
        setFocusable(true);

        Dimension dimension = new Dimension(1280, 720);
        setPreferredSize(dimension);
        setBackground(Color.LIGHT_GRAY);

        boardWidth = board.cols()*BLOCK_SIZE;
        boardHeight = board.rows()*BLOCK_SIZE;
        boardX = (dimension.width - boardWidth)/2;
        boardY = (dimension.height - boardHeight)/2;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);

        for(Block block : model.getBlocks()){
            drawBlock(g2, block);
        }
        g2.dispose();
    }

    private void drawBoard(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.drawRect(boardX, boardY, boardWidth, boardHeight);
    }

    private void drawBlock(Graphics2D g2, Block block){
        int x = block.position.x*BLOCK_SIZE + boardX;
        int y = block.position.y*BLOCK_SIZE + boardY;
        g2.drawImage(Textures.get(block.type), x, y, BLOCK_SIZE, BLOCK_SIZE, null);
    }
}