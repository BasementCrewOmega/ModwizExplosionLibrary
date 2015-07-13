
import java.awt.Color;
import java.awt.Graphics;
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
public class Particle {
    private static final int MAX_RADIUS = 40;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_SPEED = 7;
    
    private double decay;
    private boolean isAlive;
    private double x;
    private double y;
    private double vX;
    private double vY;
    private double radius;
    private int red,green,blue,alpha;
    
    private int ticks;
    
    private Random r;
    private Color color;
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    
    public Particle(double x, double y, Random r) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.radius = r.nextInt(MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
        
        this.vX = r.nextDouble() * (MAX_SPEED + 1) - 1;
        this.vY = r.nextDouble() * (MAX_SPEED + 1) - 1;
        
        this.vX = r.nextBoolean() ? -vX : vX;
        this.vY = r.nextBoolean() ? -vY : vY;
        
        if ((vX * vX + vY * vY) > MAX_SPEED * MAX_SPEED) {
            this.vX *= 0.65;
            this.vY *= 0.65;
        }
        // balance the reds more
        //this.red = r.nextInt(95) + 160;
        this.red = r.nextInt(50) + 205;
        this.green = r.nextInt(100) + (255 - 150);
        this.blue = 10;
        this.alpha = r.nextInt(55) + 200;
        
        this.color = new Color(red, green, blue, alpha);
        this.ticks = 0;
    }
    
    private long lastTime = System.nanoTime();
    public void update() {
        long diff = System.nanoTime() - lastTime;
        double seconds = diff / 1000000000.0;
        double units = 1.0 / seconds;
        System.out.println(String.format("it's been %f intervals of a second.", units));
        lastTime = System.nanoTime();
    }
    /*
    public void update() {
        if (isAlive) {
            if (r.nextBoolean()) {
                this.x += this.vX - decay / 3.0;
            } else {
                this.x += this.vX + decay / 3.0;
            }

            if (r.nextBoolean()) {
                this.y += this.vY - decay / 4.5;
            } else {
                this.y += this.vY + decay / 4.5;
            }
            if (ticks >= 300) {
                this.vX += (this.vX > 0) ? -.1 : .1;
                this.vY += (this.vY > 0) ? -.1 : .1;
                this.alpha -= (int) decay >> 2;
            } else {
                this.alpha -= (int) decay >> 3;
            }
            
            if (alpha <= 100) {
                this.isAlive = false;
            }
            this.radius = Math.min(MAX_RADIUS, this.radius+(ticks >> 8));
            color = new Color(red, green, blue, alpha);
            decay += r.nextDouble();
            ticks++;
        }
    }
    */
    
    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillOval((int)this.x, (int)this.y, (int) this.radius, (int) this.radius);
    }
}
