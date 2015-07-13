/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.expansion;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

/**
 *
 * @author starbuck
 */
public class ExpansionClassLoader extends URLClassLoader {
    private List<URL> expansionPaths;
    
    public ExpansionClassLoader() {
        super(new URL[0]);
        this.expansionPaths = new ArrayList<URL>();
    }
    
    public JarFile addJar(File expansionJar) {
        try {
            JarFile jarFile = new JarFile(expansionJar);
            super.addURL(expansionJar.toURI().toURL());
            return jarFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
