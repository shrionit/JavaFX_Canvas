/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvasanimation;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.util.*;

/**
 *
 * @author Shri
 */
public class Particle {
    private double life = 1.0;
    private GraphicsContext gc;
    private Color c;
    private double X, Y;
    private long startTime = 0;
    private long passedTime = 0;
    private final double R = 5;
    private double Xspeed=1, Yspeed=0;
    double alpha = 1.0;
    private static double m = -1;
    
    public Particle(GraphicsContext g, double x, double y, Color p){
        this.gc = g;
        this.c = p;
        this.X = x;
        this.Y = y;
        
         
        startTime = System.nanoTime();
    }
    
    public boolean isDead(){
        return life == 0; 
    }
    
    public void render(){
        if(!isDead()){
            
            update(System.nanoTime());
            passedTime = System.nanoTime();
            
            long elapsedTime = passedTime - startTime;
            double interval= (double)elapsedTime/1000000000.0;
            
            if(interval > 1){
                this.life = 0;
            }
        }
        
        
    }
    
    public void update(long t){
        gc.setFill(c);
        double RX = X-(R/2);
        double RY = Y-(R/2); 
        gc.fillOval(RX, RY, R, R);
        gc.fill();
        Y += -2;
    }
    
}
