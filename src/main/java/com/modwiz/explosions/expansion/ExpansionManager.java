/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.expansion;

import com.modwiz.explosions.Game;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;

/**
 *
 * @author starbuck
 */
public class ExpansionManager {
    private static ExpansionManager instance;
    private ExpansionClassLoader expansionClassLoader;
    
    private Map<String, Expansion> loadedExpansions;
    private Set<Expansion> conversionExpansions;
    
    private Game game;
    
    public ExpansionManager(Game game) {
        if (instance != null) {
            throw new IllegalStateException("Only one ExpansionManager should be created.");
        }
        this.expansionClassLoader = new ExpansionClassLoader();
        this.loadedExpansions = new HashMap<>();
        this.conversionExpansions = new HashSet<>();
        this.game = game;
        
        ExpansionManager.instance = this;
    }
    
    public Game game() {
        return game;
    }
    
    public List<Expansion> getTotalConversionExpansion() {
        ArrayList<Expansion> list = new ArrayList<>(conversionExpansions.size());
        for (Expansion expansion : conversionExpansions) {
            list.add(expansion);
        }
        return list;
    }
    
    public Expansion getExpansion(String expansionName) {
        return loadedExpansions.get(expansionName);
    }
    
    public Set<String> getLoadedExpansions() {
        return loadedExpansions.keySet();
    }
    
    public void loadExpansion(Expansion expansion, String fileName) {
        if (loadedExpansions.containsKey(expansion.getName())) {
            if (fileName == null) {
                fileName = "basearchive";
            }
            System.err.println("Unable to load expansion jar + " + fileName + " \"" + expansion.getName() + "\" is the same as an existing expansion.");
        } else {
            loadedExpansions.put(expansion.getName(), expansion);
            expansion.onLoad();
            if (expansion.isTotalConversion()) {
                conversionExpansions.add(expansion);
            }
        }
    }
    
    public void loadExpansions(File expansionDir) {
        for (String fileName : expansionDir.list()) {
            if (fileName.endsWith(".jar")) {
                try {
                    JarFile expansionJar = expansionClassLoader.addJar(new File(expansionDir, fileName));
                    if (expansionJar == null) {
                        System.err.println("Unable to load expansion jar " + fileName + " into a JarFile, skipping entry.");
                    } else {
                        if (expansionJar.getManifest() == null) {
                            System.err.println("Unable to load expansion jar " + fileName + "the file is missing the manifest.");
                        } else if (!expansionJar.getManifest().getEntries().containsKey("Main-Class")) {
                            System.err.println("Unable to load expansion jar " + fileName + "the manifest doesn't have a Main-Class entry.");
                        } else {
                            try {
                                Class<?> expansionClass = expansionClassLoader.loadClass(fileName);
                                if (Expansion.class.isAssignableFrom(expansionClass)) {
                                    Class<? extends Expansion> expansionCast = (Class<? extends Expansion>) expansionClass;
                                    Constructor<? extends Expansion> ctor = expansionCast.getConstructor();
                                    Expansion expansion = ctor.newInstance();
                                    loadExpansion(expansion, fileName);
                                } else {
                                    System.err.println("Unable to load expansion jar " + fileName + " the Main-Class entry wasn't an instance of Expansion");
                                }
                            } catch (ClassNotFoundException e) {
                                System.err.println("Unable to load expansion jar " + fileName + "the Main-Class entry wasn't found");
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("An exception occured while loading " + fileName + " as an expansion.");
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static ExpansionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Should always have a valid expansion manager instance.");
        }
        return instance;
    }
}
