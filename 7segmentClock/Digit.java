
package grid;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author shri
 */
public class Digit {
    private Color color;
    private double w, h, x, y;
    private GraphicsContext gc;
    private double l = 10;
    private double t = 30;
    
    public Digit(GraphicsContext gc, int d, Color color, double x, double y, double w, double h){
        this.color = color;
        this.gc = gc;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        drawDigit(d);
    }
    
    private void drawDigit(int d){
        switch (d){
            case 0:
                makeShape("FILL", "FILL", "FILL", "FILL", "FILL", "FILL", "STROKE");
                break;
            case 1:
                makeShape("STROKE", "FILL", "FILL", "STROKE", "STROKE", "STROKE", "STROKE");
                break;
            case 2:
                makeShape("FILL", "FILL", "STROKE", "FILL", "FILL", "STROKE", "FILL");
                break;
            case 3:
                makeShape("FILL", "FILL", "FILL", "FILL", "STROKE", "STROKE", "FILL");
                break;
            case 4:
                makeShape("STROKE", "FILL", "FILL", "STROKE", "STROKE", "FILL", "FILL");
                break;
            case 5:
                makeShape("FILL", "STROKE", "FILL", "FILL", "STROKE", "FILL", "FILL");
                break;
            case 6:
                makeShape("FILL", "STROKE", "FILL", "FILL", "FILL", "FILL", "FILL");
                break;
            case 7:
                makeShape("FILL", "FILL", "FILL", "STROKE", "STROKE", "STROKE", "STROKE");
                break;
            case 8:
                makeShape("FILL", "FILL", "FILL", "FILL", "FILL", "FILL", "FILL");
                break;
            case 9:
                makeShape("FILL", "FILL", "FILL", "FILL", "STROKE", "FILL", "FILL");
                break;
            default:
                makeShape("STROKE", "STROKE", "STROKE", "STROKE", "STROKE", "STROKE", "STROKE");
        }
                    
    }
    
    private void makeShape(String... s) {
        drawBar(x,     y,         w, t,     color, s[0], "H");
        drawBar(x + w, y,         t, h / 2, color, s[1], "V");
        drawBar(x + w, y + h / 2, t, h / 2, color, s[2], "V");
        drawBar(x,     y + h,     w, t,     color, s[3], "H");
        drawBar(x,     y + h / 2, t, h / 2, color, s[4], "V");
        drawBar(x,     y,         t, h / 2, color, s[5], "V");
        drawBar(x,     y + h / 2, w, t,     color, s[6], "H");
    }
    
    private void drawBar(double x, double y, double width, double height, Color barColor, String colorMode, String mode){
        
        double[] hX = {
            x,
            x+((width*l)/100),
            x+(width-((width*l)/100)),
            x+width,
            x+(width-((width*l)/100)),
            x+((width*l)/100),
            x
        };
        
        double[] hY = {
            y,
            y-height/2,
            y-height/2,
            y,
            y+height/2,
            y+height/2,
            y
            
        };
        
        double vX[] = {
            x,
            x-width/2,
            x-width/2,
            x,
            x+width/2,
            x+width/2,
            x
        };
        
        double vY[] = {
            y,
            y+((height*l)/100),
            y+(height-((height*l)/100)),
            y+height,
            y+(height-((height*l)/100)),
            y+((height*l)/100),
            y
        };
        
        if(colorMode.equals("FILL")){
            gc.setFill(barColor);
            if(mode.equals("H")){
                gc.fillPolygon(hX, hY, 7);
            }
            if(mode.equals("V")){
                gc.fillPolygon(vX, vY, 7);
            }
            gc.fill();
        }
        
        if(colorMode.equals("STROKE")){
            gc.setStroke(Color.BLACK);
            if(mode.equals("H")){
                gc.strokePolygon(hX, hY, 7);
            }
            if(mode.equals("V")){
                gc.strokePolygon(vX, vY, 7);
            }
            gc.stroke();
        }
        
    }
    
}
