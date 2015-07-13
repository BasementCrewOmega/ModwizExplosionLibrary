/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions;

import com.modwiz.explosions.menu.Menu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

/**
 *
 * @author starbuck
 */
public class ComponentDisplay extends JComponent implements KeyListener, MouseMotionListener, MouseListener{
    private final Game myGame;
    
    public ComponentDisplay(Game game) {
        this.myGame = game;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    
    // A paint repaint loop isn't the proper way to handle component drawing
    // but this is just for testing.
    
    @Override
    public void paintComponent(Graphics g) {
        myGame.render(g);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.keyReleased(e.getKeyCode());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.mouseMove(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.mouseMove(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.mousePressed(e.getX(), e.getY(), e.getButton());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Menu currentMenu = myGame.getCurrentMenu();
        if (currentMenu != null) {
            currentMenu.mouseReleased(e.getX(), e.getY(), e.getButton());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
