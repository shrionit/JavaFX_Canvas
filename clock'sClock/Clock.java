package clock;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Clock {
    private static GraphicsContext gc;
    private Point2D minute, hour, pos;
    private double r=50;
    
    private double md = 0, hd = 0;
    private double fm = 135, fh = 135;
    private double speed = 1;
    
    public Clock(){
        pos = new Point2D(1366/2, 768/2);
        double angleh = Math.toRadians(0);
        double xh = pos.getX()+r/2*Math.cos(angleh);
        double yh = pos.getY()+r/2*Math.sin(angleh);
        hour = new Point2D(xh, yh);
        
        double anglem = Math.toRadians(180);
        double xm = pos.getX()+r/2*Math.cos(anglem);
        double ym = pos.getY()+r/2*Math.sin(anglem);
        minute = new Point2D(xm, ym);
        
        
    }
    
    public static void setContext(GraphicsContext g){
        gc = g;
    }
    
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    public Clock(Point2D pos, double m, double h, double radius){
        hd = h;
        md = m;
        this.pos = pos;
        
        //this.minute = new Point2D(pos.getX()+(r/2)+Math.sin(Math.toRadians(minute.getX())), pos.getY()+(r/2)+Math.cos(Math.toRadians(minute.getY())));
        //this.hour  = new Point2D(pos.getX()+(r/2)+Math.sin(Math.toRadians(hour.getX())), pos.getY()+(r/2)+Math.cos(Math.toRadians(hour.getY())));;
        r = radius;
    }
    
    public void setTimeInDeg(double m, double h){
        fh = h;
        fm = m;
    }
    
    public void display(){
        
        animsetup();
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeLine(pos.getX(), pos.getY(), hour.getX(), hour.getY());
        //gc.setStroke(Color.GREEN);
        gc.strokeLine(pos.getX(), pos.getY(), minute.getX(), minute.getY());
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeOval(pos.getX()-(r/2), pos.getY()-(r/2), r, r);
    }
    
    private void setup(){
        double angleh = Math.toRadians(hd);
        double xh = pos.getX()+r/2*Math.cos(angleh);
        double yh = pos.getY()+r/2*Math.sin(angleh);
        hour = new Point2D(xh, yh);
        
        double anglem = Math.toRadians(md);
        double xm = pos.getX()+r/2*Math.cos(anglem);
        double ym = pos.getY()+r/2*Math.sin(anglem);
        minute = new Point2D(xm, ym);
        
    }
    
    private void animsetup(){
        if (hd > fh) {
            double angleh = Math.toRadians(hd);
            double xh = pos.getX() + r / 2 * Math.cos(angleh);
            double yh = pos.getY() + r / 2 * Math.sin(angleh);
            hour = new Point2D(xh, yh);
            hd-=speed;
        }else if (hd < fh) {
            double angleh = Math.toRadians(hd);
            double xh = pos.getX() + r / 2 * Math.cos(angleh);
            double yh = pos.getY() + r / 2 * Math.sin(angleh);
            hour = new Point2D(xh, yh);
            hd+=speed;
        }else{
            double angleh = Math.toRadians(hd);
            double xh = pos.getX() + r / 2 * Math.cos(angleh);
            double yh = pos.getY() + r / 2 * Math.sin(angleh);
            hour = new Point2D(xh, yh);
            speed = 1;
        }
        
        
        
        if (md > fm) {
            double anglem = Math.toRadians(md);
            double xm = pos.getX() + r / 2 * Math.cos(anglem);
            double ym = pos.getY() + r / 2 * Math.sin(anglem);
            minute = new Point2D(xm, ym);
            md-=speed;
        }else if (md < fm) {
            double anglem = Math.toRadians(md);
            double xm = pos.getX() + r / 2 * Math.cos(anglem);
            double ym = pos.getY() + r / 2 * Math.sin(anglem);
            minute = new Point2D(xm, ym);
            md+=speed;
        }else{
            double anglem = Math.toRadians(md);
            double xm = pos.getX() + r / 2 * Math.cos(anglem);
            double ym = pos.getY() + r / 2 * Math.sin(anglem);
            minute = new Point2D(xm, ym);
            speed = 1;
        }
        
        
    }
    
}
