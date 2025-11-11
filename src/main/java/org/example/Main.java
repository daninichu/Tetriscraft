package org.example;

import org.example.model.Board;
import org.example.model.Model;
import org.example.view.Screen;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        Board board = new Board(20, 10);
        Model model = new Model(board);
        Screen screen = new Screen(model);

        JFrame frame = new JFrame();
        frame.setName("Tetriscraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(screen);
        frame.pack();
        frame.setVisible(true);
    }
}