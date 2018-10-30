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
import java.io.*;


public class imageAnimation extends Application{
	private double W = 800;
	private double H = 600;
	private Stage window;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private double t = 0.0;
	private Image inImage;
	private PixelReader pr;
	private Color[][] c;
	private Random rand = new Random(System.currentTimeMillis());
	private int xStart, yStart, xEnd, yEnd;
	private double mouseX = 400, mouseY = 300;
	private double side = 100;






	public imageAnimation(){
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(window);
		Image img = new Image("file:\\"+file.getAbsolutePath());
		inImage = img;
		pr = inImage.getPixelReader();
		c = new Color[(int)inImage.getHeight()][(int)inImage.getWidth()];
	}

	
	public void update(){
		canvas.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});


		gc.setFill(Color.rgb(0, 0, 0, 0.03));
		gc.fillRect(0, 0, W, H);
		gc.fill();
	}

	private void putRect(int x, int y, int side){
		xStart = x;
		yStart = y;
		xEnd = x+side;
		yEnd = y+side;
		for(int j=yStart;j<yEnd;j++){
			for(int i=xStart;i<xEnd;i++){
				gc.setFill(c[j][i]);
				gc.fillRect(i, j, 1, 1);
				gc.fill();
			}
		}
	}

	
	public void physics(){
		xStart = (int)(mouseX - side/2);
		if(xStart < 0){xStart = 0;}
		yStart = (int)(mouseY - side/2);
		if(yStart < 0){yStart = 0;}
		xEnd = (int)(mouseX + side/2);
		if(xEnd > 799){xEnd = 799;}
		yEnd = (int)(mouseY + side/2);
		if(yEnd > 599){yEnd = 599;}
		/*gc.setFont(Font.font("Consolas", 15));
		gc.setStroke(Color.GREEN);
		gc.strokeText("xStart = "+xStart+"; ", 100, 100);
		gc.strokeText("yStart = "+yStart+"; ", 100, 200);
		gc.strokeText("xEnd = "+xEnd+"; ", 100, 300);
		gc.strokeText("yEnd = "+yEnd+"; ", 100, 400);*/

		
	}

	public void setup(){
		for(int yC=0;yC<inImage.getHeight();yC++){
			for(int xC=0;xC<inImage.getWidth();xC++){
				c[yC][xC] = pr.getColor(xC, yC);
			}
		}
		gc.setLineWidth(1);
	}

	
	public void draw(){
		int x = rand.nextInt(700);
		int y = rand.nextInt(500);
		putRect(x, y, 100);
		//physics();
		update();
	}

	public Parent getContent(){
		Pane pane = new Pane();
		canvas = new Canvas(W, H);
		gc = canvas.getGraphicsContext2D();
		setup();
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now){
				draw();
				t += 0.07;
			}
		};
		timer.start();
		pane.getChildren().add(canvas);
		return pane;
	}

	public void start(Stage stage)throws Exception{
		window = stage;
		scene = new Scene(getContent(), W, H);
		window.setScene(scene);
		window.show();
	}
	public static void main(String[] args) {launch(args);}
}