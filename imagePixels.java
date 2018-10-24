import javafx.application.Application;
import javafx.animation.*;
import javafx.stage.Stage; 
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.canvas.*;

public class imagePixels extends Application{
	private final double W = 800;
	private final double H = 600;
	private Canvas canvas;
	private GraphicsContext gc;
	private ColorPicker cp;
	private double mouseX;
	private double mouseY;
	private Paint p = Color.rgb(155, 122, 166);
	private double t = 0.0;
	private Image img = new Image("file:\\E:\\Shriprakash\\pics\\JPG\\alexandra-daddario-topless.jpg");
	private PixelReader pr = img.getPixelReader();
	private double X=0, Y=300;



	private void setup(){
		cp = new ColorPicker();
	}

	private void update(){
		canvas.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});
		gc.setFill(Color.rgb(0, 0, 0, 0.21));
		gc.fillRect(0, 0, W, H);
		gc.fill();
		p = cp.getValue();
	}

	private Color pixColor()throws IndexOutOfBoundsException{
		int xc = (int)(Math.random()*img.getWidth());
		int yc = (int)(Math.random()*img.getHeight());
		return pr.getColor(xc, yc);
	}

	

	private void putPixel(double x, double y, Paint c){
		gc.setFill(c);
		gc.fillOval(x, y, 2, 2);
		gc.fill();
	}

	private void drawCircle(double x, double y, double radius){
		double r = radius;
		gc.setFill(pixColor());//Color.rgb((int)(1+Math.random()*254), (int)(1+Math.random()*254), (int)(1+Math.random()*254)));
		gc.fillOval(x-r/2, y-r/2, r, r);
		gc.fill();
	}

	private void drawFrame(double x, double y, double w, double h){
		double STX1 = x-w/2, STY1 = y-h/2, STX2 = x+w/2;
		double SBX1 = x-w/2, SBY1 = y+h/2, SBX2 = x+w/2;
		double SLX1 = x-w/2, SLY1 = y-h/2, SLY2 = y+h/2;
		double SRX1 = x+w/2, SRY1 = y-h/2, SRY2 = y+h/2;

		for(double i=STX1;i<=STX2;i++){
			drawCircle(i, STY1+(Math.cos(i/t)/Math.sin(i/t)), 1+Math.random()*15);
		}

		for(double i=SRY1;i<=SRY2;i++){
			drawCircle(SRX1+(Math.sin(i/t)/Math.cos(i/t)), i, 1+Math.random()*15);
		}

		for(double i=SBX1;i<=SBX2;i++){
			drawCircle(i, SBY1+(Math.cos(i/t)/Math.sin(i/t)), 1+Math.random()*15);
		}

		for(double i=SLY1;i<=SLY2;i++){
			drawCircle(SLX1+(Math.sin(i/t)/Math.cos(i/t)), i, 1+Math.random()*15);
		}
	}

	private void path(String anime){

		if(anime.equals("anime1")){
			Y = 300+Math.sin(t/0.5)*100;
			X++;
		}else if(anime.equals("anime2")){
			Y = 300 + Math.cos(t/0.5)*100;
			X = 400 + Math.sin(t/0.5)*100;
		}
	}


	private void draw(){
		update();
		gc.setStroke(p);
		gc.strokeText("t = "+t, 500, 10);
		path("anime2");
		if((mouseX > X-100 && mouseX < X+100) && (mouseY > Y-100 && mouseY < Y+100)){
			drawFrame(X, Y, 270, 270);
			gc.drawImage(img, X-135, Y-135, 270, 270);
		}else{
			drawFrame(X, Y, 200, 200);
			gc.drawImage(img, X-100, Y-100, 200, 200);
		}
		
	}


	private Parent getRoot(){
		Pane pane = new Pane();
		canvas = new Canvas(W, H);
		gc = canvas.getGraphicsContext2D();
		setup();
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now){
				t += 0.01;
				//X++;
				draw();
			}
		};
		timer.start();
		pane.getChildren().addAll(canvas, cp);
		return pane;
	}

	public void start(Stage stage) throws Exception{
		stage.setScene(new Scene(getRoot(), W, H));
		stage.show();
	}
	public static void main(String[] args){launch(args);}
}