/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.expansion;

import com.modwiz.explosions.level.Level;
import com.modwiz.explosions.menu.Menu;
import com.modwiz.explosions.player.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 *
 * @author starbuck
 */
public interface Expansion {
    /**
     * Get all the available levels in this expansions
     * @return All available levels in the expansion
     */
    Set<String> availableLevels();
    
    /**
     * Get a named level in this expansion
     * @param name The name of the level to get
     * @return The level object, or null if the name wasn't found.
     */
    Level getLevel(String name);
    
    /**
     * Get the name of the expansion
     * @return The expansion name
     */
    String getName();
    
    /**
     * Get the playable character, used by total conversion expansions exclusively
     * @return The player for total conversion expansions, null for normal expansions
     */
    Player getPlayer();
    
    /**
     * For normal expansions, this allows them to redefine the main menu background.
     * note that most expansions will probably find this unhelpful, as the rest of the styles
     * can only be modified by total conversion expansions.
     * 
     * @return The main menu's new background 
     */
    BufferedImage getBackground();
    
    /**
     * Called when the {@link ExpansionManager} has loaded this expansion and created the instance,
     * this doesn't mean the game has been launched with this expansion, only that the expansion has
     * been created.
     */
    void onLoad();
    
    /**
     * In total conversion expansion, the main menu will be overriden likely
     * @return The main menu
     */
    Menu getMainMenu();
    
    /**
     * This will only be called on total conversion expansions to handle the game rendering.
     */
    void render(Graphics graphics);
   
    /*
    These methods are on hold because it might make more sense to use a seperate
    subinterface to represent the additional power of a full expansion.
    
    */
    
    /**
     * This means the expansion should be loaded solo as it completely redefines the
     * base gameplay with completely new mechanics. Total conversion expansions are required
     * to redefine the main menu, however they can just instantiate the original main menu
     * if no custom functionality is required.
     * 
     * @return 
     */
    boolean isTotalConversion();
    
    
    /**
     * This overrides the exclusion from the {@link #isTotalConversion()} method.
     * Allowing an expansion to mod an existing total conversion.
     * 
     * @return 
     *//*
    boolean isTotalConversionExpansion();
    */
}
