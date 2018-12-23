/**
 *
 * @author shri
 */
package clock;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.List;

public class ClockSCkock extends Application{

    private double W = 1076, H = 768;
    private double mouseX = W/2, mouseY = H/2;
    private double r = 50;
    private double t = 0;
    
    private Canvas canvas;
    private GraphicsContext gc;
    private Scene scene;
    private Time time;
    
    private List<SegClock> clocks = new ArrayList<>();
    
    private void setup(){
        
        canvas = new Canvas(W, H);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        
        for(int i=0;i<5;i++){
            clocks.add(new SegClock((215*i), 0, 215*(i+1), 768));
        }
        
        SegClock.setContext(gc);
        
    }
    
    private void setFill(Color color){gc.setFill(color);}
    private void setStroke(Color color){gc.setStroke(color);}
    private void log(Object o){System.out.print(o);}
    
    private void update(){
        time = new Time();
        setFill(Color.rgb(255, 255, 255));
        gc.fillRect(0, 0, W, H);
        canvas.setOnMouseMoved(e -> {
            mouseX = e.getX()-r/2;
            mouseY = e.getY()-r/2;
        });
        
        canvas.setOnKeyPressed(k -> {
            if(k.getCode() == KeyCode.UP){
                t += 1;
            }
            if(k.getCode() == KeyCode.DOWN){
                t -= 1;
            }
        });
        
    }
    
    private void draw(){
        setStroke(Color.rgb(25, 36, 125));
        
        //clocks.get(4).setSpeed((int)t);
        //clocks.get(5).setSpeed((int)t);
        
        int h1 = time.getHour()/10;
        int h2 = time.getHour()%10;
        int m1 = time.getMinute()/10;
        int m2 = time.getMinute()%10;
        //int s1 = time.getSecond()/10;
        //int s2 = time.getSecond()%10;
        
        clocks.forEach(c -> c.set());
        clocks.get(0).setNum(h1);
        clocks.get(1).setNum(h2);
        clocks.get(2).setNum(10);
        clocks.get(3).setNum(m1);
        clocks.get(4).setNum(m2);
        
    }
    
    private Parent getContent(){
        Pane pane = new Pane();
        setup();
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
        stage.setTitle("Clock");
        scene = new Scene(getContent(), W, H);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {launch(args);}
    
}
