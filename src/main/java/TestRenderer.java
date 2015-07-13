
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author starbuck
 */
public class TestRenderer implements KeyListener{
    private Frame mainFrame;
    private Random random = new Random();
    
    boolean wantsOut = false;
    boolean restart = false;
    
    public TestRenderer(int numBufs, GraphicsDevice device) {
        try {
            GraphicsConfiguration gc = device.getDefaultConfiguration();
            mainFrame = new Frame(gc);
            mainFrame.setUndecorated(true);
            mainFrame.setIgnoreRepaint(true);
            mainFrame.addKeyListener(this);
            device.setFullScreenWindow(mainFrame);
            if (device.isDisplayChangeSupported()) {
                chooseBestDisplayMode(device);
            }
            Rectangle bounds = mainFrame.getBounds();
            System.out.println(bounds);
            mainFrame.createBufferStrategy(numBufs);
            BufferStrategy bs = mainFrame.getBufferStrategy();
            mainFrame.setAlwaysOnTop(true);
            System.out.println(bs.getCapabilities().isPageFlipping() + ":" + bs.getCapabilities().isMultiBufferAvailable());
            long lastTime;
            Particle[] particles=  new Particle[200];
            Random r = new Random();
            
            int explosionX = r.nextInt(bounds.width);
            int explosionY = r.nextInt(bounds.height);
            for (int i = 0; i < particles.length; i++) {
                particles[i] = new Particle(explosionX, explosionY, r);
            }
            
            while (!wantsOut) {
                boolean isDead = true;
                
                for (int i = 0; i < particles.length; i++) {
                    if (particles[i] != null) {
                        particles[i].update();
                        if (!particles[i].isAlive()) {
                            particles[i] = null;
                        } else {
                            isDead = false;
                        }
                    }
                }
                
                if (isDead || restart) {
                    if (restart) {
                        explosionX = r.nextInt(bounds.width);
                        explosionY = r.nextInt(bounds.height);
                    }

                    for (int i = 0; i < particles.length; i++) {
                        particles[i] = new Particle(explosionX, explosionY, r);
                    }
                }
                
                lastTime = System.nanoTime();
                Graphics g = bs.getDrawGraphics();
                if (!bs.contentsLost()) {
                    System.out.println("Rendering");
                    g.setColor(Color.black);
                    g.fillRect(0,0,bounds.width, bounds.height);
                    for (int j = 0; j < particles.length; j++) {
                        if (particles[j] != null) {
                            particles[j].render(g);
                        }
                    }
                    bs.show();
                } else {
                    System.out.println("Contents lost");
                }
                
                if (wantsOut) {
                    System.out.println("User hit escape to exit.");
                    break;
                }
                while ((System.nanoTime() - lastTime) < 1000000000.0 / 60) {
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            device.setFullScreenWindow(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            wantsOut = true;
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            restart = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            restart = false;
        }
    }

    private void chooseBestDisplayMode(GraphicsDevice device) {
        for (DisplayMode gc : device.getDisplayModes()) {
            System.out.println(gc.getWidth() + "x" + gc.getHeight() + ":" + gc.getRefreshRate());
            if (gc.getWidth() == 1400 && gc.getHeight() == 1050 && gc.getRefreshRate() == 75) {
                device.setDisplayMode(gc);
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            int numBuffers = 3;
            if (args != null && args.length > 0) {
                numBuffers = Integer.parseInt(args[0]);
            }
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = env.getDefaultScreenDevice();
            TestRenderer test = new TestRenderer(numBuffers, device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
}
