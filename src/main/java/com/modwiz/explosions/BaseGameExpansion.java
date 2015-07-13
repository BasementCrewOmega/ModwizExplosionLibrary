/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions;

import com.modwiz.explosions.expansion.Expansion;
import com.modwiz.explosions.expansion.ExpansionManager;
import com.modwiz.explosions.level.Level;
import com.modwiz.explosions.menu.DeveloperMenu;
import com.modwiz.explosions.menu.Menu;
import com.modwiz.explosions.player.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 *
 * @author starbuck
 */
public class BaseGameExpansion implements Expansion {

    @Override
    public Set<String> availableLevels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Level getLevel(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return "Base Game";
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public BufferedImage getBackground() {
        return null;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public Menu getMainMenu() {
        return new DeveloperMenu(ExpansionManager.getInstance().game());
    }

    @Override
    public boolean isTotalConversion() {
        return true;
    }

    @Override
    public void render(Graphics graphics) {
        Game game = ExpansionManager.getInstance().game();
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0, 0, game.width, game.height);
    }
    
}
