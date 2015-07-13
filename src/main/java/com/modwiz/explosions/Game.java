/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions;

import com.modwiz.explosions.expansion.Expansion;
import com.modwiz.explosions.expansion.ExpansionManager;
import com.modwiz.explosions.menu.Menu;
import com.modwiz.explosions.player.Player;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author starbuck
 */
public class Game implements Runnable {
    private Map<String, Level> loadedLevels;
    private Set<String> availableLevels;
    private Player thePlayer;
    
    private File expansionDirectory = new File("expansions");
    
    private ExpansionManager expansionManager;
    private Expansion totalConversionExpansion;
    
    private Menu currentMenu;
    
    public final int width;
    public final int height;
    
    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.expansionManager = new ExpansionManager(this);
        this.expansionManager.loadExpansion(new BaseGameExpansion(), null);
        if (!expansionDirectory.exists()) {
            this.expansionDirectory.mkdir();
        } else {
            this.expansionManager.loadExpansions(expansionDirectory);
        }
    }
    
    public void start() {
        final JFrame frame = new JFrame("Select an extension");
        final JList<String> expansionList = new JList<>(new AbstractListModel<String>() {

            @Override
            public int getSize() {
                return expansionManager.getTotalConversionExpansion().size();
            }

            @Override
            public String getElementAt(int index) {
                return expansionManager.getTotalConversionExpansion().get(index).getName();
            }
        });
        JButton launchGame = new JButton(new AbstractAction("Start Expansion"){

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedExpansion = expansionList.getSelectedValue();
                if (selectedExpansion == null) {
                    JOptionPane.showMessageDialog(null, "You must select an expansion to launch the game", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    totalConversionExpansion = expansionManager.getExpansion(selectedExpansion);
                    JOptionPane.showMessageDialog(null, "Selected expansion " + selectedExpansion, "", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    frame.dispose();
                    run();
                }
            }
        });
        frame.setLayout(new GridLayout(2, 1));
        frame.add(expansionList);
        frame.add(launchGame);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(320, 480));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void run() {
        this.currentMenu = totalConversionExpansion.getMainMenu();
        this.thePlayer = totalConversionExpansion.getPlayer();
        JFrame mainFrame = new JFrame(totalConversionExpansion.getName());
        mainFrame.setMaximumSize(new Dimension(800, 600));
        ComponentDisplay componentDisp = new ComponentDisplay(this);
        mainFrame.add(componentDisp);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public Set<String> getAvailableLevels() {
        return this.totalConversionExpansion.availableLevels();
    }
    
    public Player getPlayer() {
        return this.thePlayer;
    }
    
    public void render(Graphics g) {
        if (currentMenu != null) {
            currentMenu.render(g);
        } else {
            this.totalConversionExpansion.render(g);
        }
    }
    
    public Menu getCurrentMenu() {
        return this.currentMenu;
    }
    
    public static void main(String[] args) {
        // somehow get display resolution paramaters
        int width = 800;
        int height = 600;
        Game game = new Game(width, height);
        game.start();
    }
}
