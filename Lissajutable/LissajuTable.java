/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lissajutable;

import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.stage.Stage;

/**
 *
 * @author shripc
 */
public class LissajuTable extends Application{
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Point2D point;
    private Curve curve[][];
    
    private double W = 800;
    private double H = 800;
    private double w = 80;
    private double cols;
    private double rows;
    private double angle = 0.0;

    
    private void setup(){
        canvas = new Canvas(W, H);
        gc = canvas.getGraphicsContext2D();
        cols = W/w-1;
        rows = H/w-1;
        
        curve = new Curve[(int)rows][(int)cols];
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                curve[i][j] = new Curve(gc);
            }
        }
    }
    
    private void update(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, W, H);
        gc.fill();
    }
    
    private void drawF(){
        double d = w-10;
        double r = d/2;
        
        for(int j=0;j<cols;j++){
            for(int i=0;i<rows;i++){
                double cx = w+w*i + w/2;
                double cy = w/2;
                circle(cx, cy, d, "stroke");
            
                double x = r * Math.cos(angle * (i+1) - Math.PI/2);
                double y = r * Math.sin(angle * (i+1) - Math.PI/2);
                circle(cx+x, cy+y, 5, "fill");
                gc.setStroke(Color.rgb(255, 255, 255, 0.65));
                line(cx+x, 0, cx+x, H);
            
                curve[j][i].setX(cx+x);
            }
            
        }
        
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                double cy = w+w*j + w/2;
                double cx = w/2;
                circle(cx, cy, d, "stroke");
            
                double x = r * Math.cos(angle * (j+1) - Math.PI/2);
                double y = r * Math.sin(angle * (j+1) - Math.PI/2);
                circle(cx+x, cy+y, 5, "fill");
                gc.setStroke(Color.rgb(255, 255, 255, 0.65));
                line(0, cy+y, W, cy+y);
                curve[j][i].setY(cy+y);
            }
            
        }
        
        
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                curve[i][j].addPoint();
                curve[i][j].show();
            }
        }
        
        
    }
    
    private void circle(double x, double y, double r, String mode){
        x -= r/2;
        y -= r/2;
        if(mode.equals("stroke")){
            gc.setStroke(Color.RED);
            gc.strokeOval(x, y, r, r);
        }else if(mode.equals("fill")){
            gc.setFill(Color.GREEN);
            gc.fillOval(x, y, r, r);
            gc.fill();
        }
    }
    
    private void line(double x1, double y1, double x2, double y2){
        gc.strokeLine(x1, y1, x2, y2);
    }
    
    private void draw(){
        update();
        drawF();
    }
    
    private Parent getContent(){
        Pane pane = new Pane();
        setup();
        AnimationTimer timer = new AnimationTimer(){
            public void handle(long now){
                draw();
                angle+=0.01;
            }
        };
        timer.start();
        
        pane.getChildren().add(canvas);
        return pane;
    }
    
    public void start(Stage stage) throws Exception{
        scene = new Scene(getContent(), W, H);
        stage.setTitle("Lissaju Table");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {launch(args);}
    
}
