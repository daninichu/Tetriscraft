package org.example.view;

import org.example.controller.Controller;
import org.example.model.Board;
import org.example.model.Model;
import org.example.model.block.Block;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel{
    public static final int FPS = 60;
    public static final int CELL_SIZE = 32;
    private int boardX, boardY, boardWidth, boardHeight;
    private final ViewableModel model;

    public GamePanel(){
        Board board = new Board(10, 20);
        Model model = new Model(board);
        model.launch(this);
        this.model = model;

        addKeyListener(new Controller(model));
        setFocusable(true);

        Dimension dimension = new Dimension(1280, 720);
        setPreferredSize(dimension);
        setBackground(Color.BLACK);

        boardWidth = board.cols() * CELL_SIZE;
        boardHeight = board.rows() * CELL_SIZE;
        boardX = dimension.width / 2 - boardWidth / 2;
        boardY = dimension.height / 2 - boardHeight / 2;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);

        g2.setColor(Color.RED);
        for(Block block : model.getBlocks()){
            drawBlock(g2, block);
        }
    }

    private void drawBoard(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.drawRect(boardX, boardY, boardWidth, boardHeight);
    }

    private void drawBlock(Graphics2D g2, Block block){
        int x = block.position.x * CELL_SIZE + boardX;
        int y = block.position.y * CELL_SIZE + boardY;
        g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }
}