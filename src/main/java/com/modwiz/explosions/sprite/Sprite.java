/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.sprite;

import com.modwiz.explosions.Game;
import com.modwiz.explosions.level.Level;
import com.modwiz.explosions.util.BoundingAABB;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author starbuck
 */
public abstract class Sprite {
    private BoundingAABB boundingBox;
    private BufferedImage storedImage;
    private Level level;
    private boolean isAlive;
    
    private double velX;
    private double velY;
    
    public Sprite(double x, double y, double width, double height) {
        this.boundingBox = new BoundingAABB(width, height);
        this.boundingBox.update(x, y);
        this.level = null;
        this.velX = 0;
        this.velY = 0;
    }
    
    public boolean isColliding(Sprite target) {
        return target.boundingBox.checkCollision(boundingBox);
    }
    
    
    public void update(Game game) {
        // Level will handle killed sprites
        if (isAlive) {
            double x = boundingBox.getX();
            double y = boundingBox.getY();
            
            x += velX;
            y += velY;
            
            if (!level.isOnGround(this)) {
                velY -= level.getGravity();
            }
            
            boundingBox.update(x, y);
        }
    }
    
    public void setVelocity(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }
    
    public double getVelX() {
        return this.velX;
    }
    
    public double getVelY() {
        return this.velY;
    }
    
    public void render(Graphics g) {
        if (storedImage == null || isImageUpdated()) {
            this.storedImage = getImage();
        }
        g.drawImage(storedImage, (int) boundingBox.getX(), (int) boundingBox.getY(), null);
    }
    
    public final boolean isAlive() {
        return isAlive;
    }
    
    public final void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    
    
    public double getX() {
        return boundingBox.getX();
    }
    
    public double getY() {
        return boundingBox.getY();
    }
    
    public Level getLevel() {
        return level;
    }
    
    public double getWidth() {
        return boundingBox.getWidth();
    }
    
    public double getHeight() {
        return boundingBox.getHeight();
    }
    
    public int getTextureWidth() {
        return getImage().getWidth();
    }
    
    public int getTextureHeight() {
        return getImage().getHeight();
    }
    
    public boolean isImageUpdated() {
        return false;
    }
    
    public abstract void handleCollision(Sprite target);
    
    public void setLocation(double x, double y, Level level) {
        if (this.level != null) {
            this.level.removeSprite(this);
        }
        this.level = level;
        this.level.addSprite(this);
    }
        
    public void setLocation(double x, double y) {
        this.boundingBox.update(x, y);
    }
    
    // TODO: Would the caching ever be unwanted?
    public final BufferedImage getImage() {
        if (storedImage == null || isImageUpdated()) {
            this.storedImage = getImage0();
        }
        return storedImage;
    }

    protected abstract BufferedImage getImage0();
}
