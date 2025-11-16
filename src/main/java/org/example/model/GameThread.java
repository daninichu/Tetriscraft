package org.example.model;

import org.example.view.GamePanel;

public class GameThread extends Thread {
    private final GamePanel gamePanel;
    private final Model model;
    boolean terminate = false;

    GameThread(GamePanel gamePanel, Model model) {
        this.gamePanel = gamePanel;
        this.model = model;
    }

    @Override
    public void run(){
        double frameTime = 1000000000.0 / GamePanel.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        while(!terminate){
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / frameTime;
            lastTime = currentTime;
            if(delta >= 1){
                model.update();
                gamePanel.repaint();
                delta--;
            }
        }
    }
}