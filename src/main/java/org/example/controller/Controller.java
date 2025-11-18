package org.example.controller;

import org.example.model.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter{
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT -> model.moveTetromino(-1, 0);
            case KeyEvent.VK_RIGHT -> model.moveTetromino(1, 0);
            case KeyEvent.VK_UP -> model.rotateTetromino(true);
            case KeyEvent.VK_DOWN -> model.softDrop();
            case KeyEvent.VK_SPACE -> model.hardDrop();
            case KeyEvent.VK_X -> model.rotateTetromino(true);
            case KeyEvent.VK_Z -> model.rotateTetromino(false);
        }
    }
}
