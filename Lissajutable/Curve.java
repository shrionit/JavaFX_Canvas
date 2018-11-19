/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lissajutable;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

/**
 *
 * @author shripc
 */
public class Curve {
    private List<Point2D> path;
    private Point2D current;
    public double XX = 0, YY = 0;
    public static GraphicsContext gc;
    
    public Curve(GraphicsContext gc){
        this.gc = gc;
        path = new ArrayList<>();
        current = new Point2D(0, 0);
    }
    
    public void setX(double x){
        XX = x;
    }
    
    public void setY(double y){
        YY = y;
    }
   
    
    
    public void addPoint(){
        current = new Point2D(XX, YY);
        path.add(current);
        
    }
    
    public void show(){
        gc.setStroke(Color.CADETBLUE);
        gc.beginPath();
        gc.moveTo(current.getX(), current.getY());
        for(Point2D p : path){
            current = new Point2D(p.getX(), p.getY());
            gc.moveTo(current.getX(), current.getY());
            gc.lineTo(p.getX(), p.getY());
        }
        gc.stroke();
        current = new Point2D(0, 0);
        gc.closePath();
        
    }
}
