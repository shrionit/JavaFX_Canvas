/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageanimation;

/**
 *
 * @author shripc
 */
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.animation.*;
import java.util.*;
import javafx.geometry.Point2D;


public class ImageAnimation extends Application{
	private double W = 1366;
	private double H = 768;
	private Stage window;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Random rand = new Random(System.currentTimeMillis());
	List<Pixel> pixels = new ArrayList<>();
        private double mouseX = 0, mouseY = 0;
        
	public void update(){
            
                canvas.setOnMouseMoved(e -> {
                    mouseX = e.getX();
                    mouseY = e.getY();
                });
		gc.clearRect(0, 0, W, H);
                pixels.removeIf(Pixel::isDead);
                pixels.parallelStream()
                        .filter(p -> !p.isActive())
                        .forEach(p -> {
                            if(Math.sqrt((Math.pow((mouseX-p.getx()), 2)+Math.pow((mouseY-p.gety()), 2))) < 10){
                                p.activate(new Point2D(-Math.random()*10, -Math.random()*10));
                            }
                        }));
                pixels.forEach(p -> {
                    p.update();
                    p.draw(gc);
                });
	}


	public Parent getContent(){
		Pane pane = new Pane();
		canvas = new Canvas(W, H);
		gc = canvas.getGraphicsContext2D();
                
                Image image = new Image(getClass().getResource("pic0.jpg").toExternalForm());
                
                disintigrate(image);
                
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now){
				update();
			}
		};
		timer.start();
		pane.getChildren().add(canvas);
		return pane;
	}
        
        private void disintigrate(Image image){
            PixelReader pr = image.getPixelReader();
            for(int i=0;i<image.getHeight();i++){
                for(int j=0;j<image.getWidth();j++){
                    Color color = pr.getColor(j, i);
                    pixels.add(new Pixel((double)j+10, (double)i+10, color));
                }
            }
        }

	public void start(Stage stage)throws Exception{
		window = stage;
		scene = new Scene(getContent(), W, H);
		window.setScene(scene);
		window.show();
	}
	public static void main(String[] args) {launch(args);}
}