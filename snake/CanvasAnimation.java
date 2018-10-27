/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvasanimation;

import java.util.*;
import java.util.Random;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.input.*;
import javafx.scene.shape.*;

/**
 *
 * @author Shri
 */
public class CanvasAnimation extends Application{
    private double width = 800;
    private double height = 600;
    private GraphicsContext gc;
    private Canvas canvas;
    private double mouseX;
    private double mouseY;
    private double t = 0;
    private Box b = new Box();
    private Pane pane;
    private Point2D food;
    private int score = 0;
    private Paint snakeC;
    private Paint p;
    
    //randomIntGenerator
//    private int randomInt(int min, int max){
//        Random rand = new Random();
//        int out = rand.nextInt((max-min)+1)+min;
//        return out;
//    }
    //update
    private void update(){
        canvas.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
        
        canvas.setOnMouseDragged(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
        
        gc.setFill(Color.rgb(0, 0, 0, 0.23));
        gc.fillRect(0, 0, width, height);
        gc.fill();
        
    }
    
    //setup
    private void setup(){
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        pickLocation();
        p = Color.rgb((int)(1+Math.random()*254), (int)(1+Math.random()*254), (int)(1+Math.random()*254));
    }
    
    
    //physics
    private void physics(){
        canvas.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){b.dir(0, -1);}
            if(e.getCode() == KeyCode.DOWN){b.dir(0, 1);}
            if(e.getCode() == KeyCode.LEFT){b.dir(-1, 0);}
            if(e.getCode() == KeyCode.RIGHT){b.dir(1, 0);}
        });
    }
  
    private void pickLocation(){
        food = new Point2D(1+Math.random()*785, 1+Math.random()*585);
        p = Color.rgb((int)(1+Math.random()*254), (int)(1+Math.random()*254), (int)(1+Math.random()*254));
        
    }
    
    //draw()
    private void draw(){
        update();
        physics();      
        b.show(gc);
        b.update();
        if(b.eat(food)){pickLocation();b.changeColor(p);score++;}
        gc.setStroke(Color.RED);
        gc.strokeText("Score: "+score, 750, 10);
        gc.setFill(p);
        gc.fillRect(food.getX(), food.getY(), 15, 15);
        gc.fill();
    }

    
    private Parent getParent(){
        pane = new Pane();
        setup();
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                t+=0.07;
                draw();
            }
        };
        timer.start();
        pane.getChildren().add(canvas);
        return pane;
    }
    
    //start method
    public void start(Stage stage) throws Exception{
        stage.setScene(new Scene(getParent(), width, height));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
