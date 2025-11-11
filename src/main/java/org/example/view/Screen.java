package org.example.view;

import org.example.model.Model;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel{
    public Screen(Model model){
        setPreferredSize(new Dimension(640, 480));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    }
}