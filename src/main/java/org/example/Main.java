package org.example;

import org.example.model.Board;
import org.example.model.Model;
import org.example.view.GamePanel;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setName("Tetriscraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}