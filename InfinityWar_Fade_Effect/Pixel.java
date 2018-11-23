/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageanimation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author shripc
 */
public class Pixel {
    
    private Point2D velocity  = Point2D.ZERO;
    private Color color;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double life = 1.0;
    private boolean active = false;
    
    public Pixel(double x, double y, Color color){
        this.x.set(x);
        this.y.set(y);
        this.color = color;
    }
    
    public double getx(){
        return x.get();
    }
    
    public double gety(){
        return y.get();
    }
    
    public boolean isDead(){
        return life == 0;
    }
    
    public boolean isActive(){
        return active;
    }
    
    public void activate(Point2D velocity){
        active = true;
        this.velocity = velocity;
    }
    
    public void update(){
        if(!active)return;
        
        life -= 0.017 * 0.75;
        
        if(life <= 0)life=0;
        
        this.x.set(getx()+velocity.getX());
        this.y.set(gety()+velocity.getY());
    }
    
    public void draw(GraphicsContext g){
        g.setFill(color);
        g.setGlobalAlpha(life);
        g.fillOval(x.get(), y.get(), 1, 1);
    }
    
}
