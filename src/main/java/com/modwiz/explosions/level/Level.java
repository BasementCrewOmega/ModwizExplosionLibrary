/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.level;

import com.modwiz.explosions.Game;
import com.modwiz.explosions.sprite.Sprite;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author starbuck
 */
public abstract class Level {
    public abstract String getName();
    
    public abstract void loadLevel();
    
    public abstract void saveLevel();
    
    /**
     * Update the level and handles updates for all the sprites. 
     * @param game The game instance
     */
    public abstract void update(Game game);
    
    /**
     * Get a copy of the sprite list
     * @return A copy of the sprite list
     */
    public abstract List<Sprite> getSprites();
    
    public abstract void render(Graphics graphics);
    
    // The addSprite and removeSprite methods probably require copyi
    public abstract void removeSprite(Sprite sprite);
    
    public abstract void addSprite(Sprite sprite);
    
    public abstract double getGravity();
    
    public abstract boolean isOnGround(Sprite sprite);
}
