/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.util;

/**
 *
 * @author starbuck
 */
public final class BoundingAABB {
    private double x;
    private double y;
    private final double width;
    private final double height;
    
    public BoundingAABB(double width, double height) {
        this.x = -1;
        this.y = -1;
        this.width = width;
        this.height = height;
    }
    
    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public final double getX() {
        return this.x;
    }
    
    public final double getY() {
        return this.y;
    }
    
    public final double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public boolean checkCollision(BoundingAABB colliding) {
        if (this.x < 0 || this.y < 0) {
            throw new IllegalStateException("This bounding box never got initialized");
        }
        
        if (this.x > (colliding.x + colliding.width)) {
            return false;
        } else if ((this.x + this.width) < colliding.x) {
            return false;
        } else if (this.y > (colliding.y + colliding.height)) {
            return false;
        } else if ((this.y + this.height) < colliding.y) {
            return false;
        }
        
        return true;
    }
}
