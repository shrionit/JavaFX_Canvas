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
    private double width = 1000;
    private double height = 700;
    private GraphicsContext gc;
    private Canvas canvas;
    private double mouseX;
    private double mouseY;
    private double t = 0;
    private Pane pane;
    private List<Particle> particles = new ArrayList<>();

    //randomIntGenerator
    private int randomInt(int min, int max){
        Random rand = new Random();
        int out = rand.nextInt((max-min)+1)+min;
        return out;
    }
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
        
        gc.setFill(Color.rgb(0, 0, 0, 0.33));
        gc.fillRect(0, 0, width, height);
        gc.fill();
        
    }
    
    //setup
    private void setup(){
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();        
    }
    
    
    
    //draw()
    private void draw(){
        update();
        
        
        canvas.setOnMouseClicked(e -> {
            particles.add(new Particle(gc, mouseX, mouseY, Color.rgb(randomInt(100, 255), randomInt(155, 200), randomInt(100, 200))));
        });
        
        for(int i=0;i<=1;i++){
            particles.add(new Particle(gc, Math.random()*width, Math.random()*height, Color.rgb(randomInt(100, 255), randomInt(155, 200), randomInt(100, 200))));
        }
        
        particles.removeIf(p -> p.isDead());
        particles.forEach(p -> p.render());
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
