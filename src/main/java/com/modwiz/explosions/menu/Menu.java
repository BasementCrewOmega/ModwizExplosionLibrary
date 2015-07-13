/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.menu;

import com.modwiz.explosions.Game;
import java.awt.Graphics;

/**
 *
 * @author starbuck
 */
public abstract class Menu {
    protected Game game;
    
    public Menu(Game game) {
        this.game = game;
    }
    
    /**
     * The menu should hold state and wait until update is called to perform
     * a transition into a new menu or the game proper.
     * @param g 
     */
    public abstract void update();
    
    public abstract void render(Graphics g);
    
    public abstract void mouseMove(int x, int y);
    
    public abstract void mousePressed(int x, int y, int button);
   
    public abstract void mouseReleased(int x, int y, int button);
    
    public abstract void keyPressed(int keycode);
    
    public abstract void keyReleased(int keycode);
}
