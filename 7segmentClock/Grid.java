package grid;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author shripc
 */
public class Grid extends Application{

    private double W = 1366, H = 768;
    private Stage window;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Time time;
    private int[] key = new int[6];
    private int spacing = 60;
    
    
    private void update(){
        gc.setFill(Color.rgb(0, 0, 0));
        gc.fillRect(0, 0, W, H);
        gc.fill();
    }
    
    
    
    private void draw(){
        time = new Time();
        gc.setStroke(Color.YELLOW);
        gc.strokeText("H: "+time.getHour()+" M: "+time.getMinute()+" S: "+time.getSecond(), 10, 10);
        
        bindTime(time.getHour(), time.getMinute(), time.getSecond());
        int a = 100;
        
        new Digit(gc, key[0], Color.ROYALBLUE, a, 200, 130, 400);
        new Digit(gc, key[1], Color.ROYALBLUE, a+190, 200, 130, 400);
        Colon(a+190+130, 200, 60, 400,  Color.GREEN);
        new Digit(gc, key[2], Color.ROYALBLUE, a+190+130+110, 200, 130, 400);
        new Digit(gc, key[3], Color.ROYALBLUE, a+190+130+140+160, 200, 130, 400);
        Colon(a+190+130+140+160+130, 200, 60, 400, Color.GREEN);
        new Digit(gc, key[4], Color.ROYALBLUE, a+190+130+140+130+130+140, 200, 130, 400);
        new Digit(gc, key[5], Color.ROYALBLUE, a+190+130+140+130+130+170+160, 200, 130, 400);
    }
    
    private void Colon(double x, double y, double w, double h, Color color){
        
        gc.setFill(color);
        gc.fillRect(x+30, y+80, 40, 40);
        gc.fillRect(x+30, y+240, 40, 40);
        gc.fill();
    }
    
    private void bindTime(int h, int m, int s){
        if(h < 10){
            key[0] = 0;
            key[1] = h;
        }
        if(m < 10){
            key[2] = 0;
            key[3] = m;
        }
        if(s < 10){
            key[4] = 0;
            key[5] = s;
        }
        
        key[0] = h/10;
        key[1] = h%10;
        
        key[2] = m/10;
        key[3] = m%10;
        
        key[4] = s/10;
        key[5] = s%10;
    }
    
    private Parent getContent(){
        Pane pane = new Pane();
        canvas = new Canvas(W, H);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        
        AnimationTimer timer = new AnimationTimer(){
            public void handle(long now){
                update();
                draw();
            }
        };
        timer.start();
        pane.getChildren().add(canvas);
        return pane;
        
    }
    
    public void start(Stage stage){
        window = stage;
        scene = new Scene(getContent(), W, H);
        window.setTitle("Grid");
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) {launch(args);}
    
}
