/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.menu;

import com.modwiz.explosions.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * A sample menu that can be used while developing the api
 * @author starbuck
 */
public class DeveloperMenu extends Menu{

    private int lastX;
    private int lastY;
    private int lastButton;
    
    public DeveloperMenu(Game game) {
        super(game);
        lastX = -1;
        lastY = -1;
        lastButton = -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.width, game.height);
        g.setColor(Color.RED);
        g.drawString("Developer menu", 10, 25);
        g.drawString("X: " + lastX, 10, 50);
        g.drawString("Y: " + lastY, 10, 75);
        g.drawString("Button: " + lastButton, 10, 100);
    }

    @Override
    public void mouseMove(int x, int y) {
        if (lastButton != -1) {
            lastX = x;
            lastY = y;
        }
    }

    @Override
    public void mousePressed(int x, int y, int button) {
        lastX = x;
        lastY = y;
        lastButton = button;
    }

    @Override
    public void mouseReleased(int x, int y, int button) {
        if (lastButton == button) {
            lastButton = -1;
        }
    }

    @Override
    public void keyPressed(int keycode) {
    }

    @Override
    public void keyReleased(int keycode) {
    }

    @Override
    public void update() {
    }
    
}
