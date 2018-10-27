/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvasanimation;
import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
/**
 *
 * @author Shri
 */
public class Box {
    private double xSpeed = 1, ySpeed = 0;
    private double X = 0, Y = 0;
    private double side = 15;
    private double scl = 5;
    private GraphicsContext gc;
    private int total = 0;
    private Paint p = Color.WHITE;

    private List<Point2D> tailPoint = new ArrayList<Point2D>();    
    
    private void log(Object o){System.out.println(o);}
    
    public boolean eat(Point2D p){
           double d = Math.abs(p.getX() - this.X) + Math.abs(p.getY() - this.Y);
           
           if(d<15){
               this.total++;
               return true;
           }
           else{
               return false;
           }
    }
    
    private void tail(double x, double y){
        Point2D P;
        for(int i=0;i<total;i++){
            P = new Point2D(x, y);
            if(!tailPoint.contains(P)){
                tailPoint.add(i, P);
            }
        }
        for(int i=total;i<tailPoint.size();i++){
            tailPoint.remove(i);
        }
//        log("------------------");
//        log(tailPoint);
    }
    
    public void update(){
        tail(this.X, this.Y);
        this.X = this.X + this.xSpeed*scl;
        this.Y = this.Y + this.ySpeed*scl;        
        if(this.X+15 < 0){this.X = 785;}
        if(this.X > 785){this.X = 0;}
        if(this.Y+15 < 0){this.Y = 585;}
        if(this.Y > 585){this.Y = 0;}
        
        
    }
    
    public void changeColor(Paint paint){
        this.p = paint;
    }
    
    public void show(GraphicsContext gc){
        this.gc = gc;
        this.gc.setFill(this.p);
        this.gc.fillRect(this.X, this.Y, this.side, this.side);
        for(Point2D p : tailPoint){
            this.gc.fillRect(p.getX(), p.getY(), this.side, this.side);
        }
        this.gc.fill();
    }
    
    public void dir(double x, double y){
        this.xSpeed = x;
        this.ySpeed = y;
    }
}
