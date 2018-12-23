package clock;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class SegClock {
    
    private double x, y, w, h;
    private double r;
    private double hn = 4, vn = 10;
    private int num;
    private static GraphicsContext gc;
    private double zero[][];
    private List<double[][]> numbers = new ArrayList<>();
    private final double TOP = 270, LEFT = 180, RIGHT = 0, BOTTOM = 90, Q = 135;
    
    List<Clock> clocks = new ArrayList<>();
    
    public SegClock(double x, double y, double w, double h){
        
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        r = 50;
        
        for(double i=this.y;i<this.h;i+=r+5){
            for(double j=this.x;j<this.w;j+=r+5){
                clocks.add(new Clock(new Point2D(j+(r/2), i+(r/2)), 0, 180, r));
            }
        }
    }
    
    public static void setContext(GraphicsContext g){
        gc = g;
        Clock.setContext(gc);
    }
    
    public void setNum(int num){
        this.num = num;
    }
    
    public void setSpeed(double speed){
        clocks.forEach(c -> c.setSpeed(speed));
    }
    
    public void set(){
        TimeArrayGen(num);
        clocks.forEach(c -> c.display());
    }
    
    private void TimeArrayGen(int n){
        
        //---------------0
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, RIGHT}, {LEFT, TOP}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {LEFT, TOP}
        });
        
        //---------------1
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {Q, Q},
            {TOP, RIGHT}, {BOTTOM, LEFT}, {BOTTOM, TOP}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q},
            {BOTTOM, RIGHT}, {TOP, LEFT}, {RIGHT, TOP}, {BOTTOM, LEFT},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {LEFT, TOP}
        });
        
        //---------------2
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {RIGHT, BOTTOM}, {RIGHT, LEFT}, {TOP, LEFT},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {LEFT, TOP}
        });
        
        //---------------3
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {LEFT, TOP}
        });
        
        //---------------4
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, //1
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},       //2
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},       //3
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},       //4
            {TOP, BOTTOM}, {TOP, RIGHT}, {TOP, LEFT}, {TOP, BOTTOM},          //5
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},       //6
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //7
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //8
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},          //9
            {Q, Q}, {Q, Q}, {TOP, RIGHT}, {LEFT, TOP}           //10
        });
        
        //---------------5
        numbers.add(new double[][]{
            {BOTTOM, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {BOTTOM, RIGHT}, {RIGHT, LEFT}, {TOP, LEFT},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {TOP, LEFT}
        });
        
        //---------------6
        numbers.add(new double[][]{
            {BOTTOM, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {BOTTOM, RIGHT}, {RIGHT, LEFT}, {TOP, LEFT},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {Q, Q}, {Q, Q},
            {TOP, BOTTOM}, {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {RIGHT, TOP}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {TOP, LEFT}
        });
        
        //---------------7
        numbers.add(new double[][]{
            {RIGHT, BOTTOM}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, //1
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},       //2
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //3
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //4
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},          //5
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //6
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //7
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},       //8
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},          //9
            {Q, Q}, {Q, Q}, {TOP, RIGHT}, {LEFT, TOP}           //10
        });
        
        //---------------8
        numbers.add(new double[][]{
            {BOTTOM, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {BOTTOM, RIGHT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, RIGHT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {RIGHT, TOP}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {TOP, LEFT}
        });
        
        //---------------9
        numbers.add(new double[][]{
            {BOTTOM, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {BOTTOM, LEFT},
            {TOP, BOTTOM}, {BOTTOM, RIGHT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {TOP, BOTTOM}, {TOP, RIGHT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {BOTTOM, LEFT}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {Q, Q}, {Q, Q}, {TOP, BOTTOM}, {TOP, BOTTOM},
            {BOTTOM, RIGHT}, {RIGHT, LEFT}, {TOP, LEFT}, {TOP, BOTTOM},
            {TOP, RIGHT}, {RIGHT, LEFT}, {RIGHT, LEFT}, {TOP, LEFT}
        });
        
        //--------------dot
        numbers.add(new double[][]{
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q},
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q},
            {Q, Q}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {Q, Q},
            {Q, Q}, {TOP, RIGHT}, {TOP, LEFT}, {Q, Q},
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q},
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q},
            {Q, Q}, {RIGHT, BOTTOM}, {BOTTOM, LEFT}, {Q, Q},
            {Q, Q}, {TOP, RIGHT}, {TOP, LEFT}, {Q, Q},
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q},
            {Q, Q}, {Q, Q}, {Q, Q}, {Q, Q}
        });
        
        
        
        for(int i=0;i<40;i++){
            clocks.get(i+8).setTimeInDeg(numbers.get(n)[i][0], numbers.get(n)[i][1]);
        }
        
    }
    
    
    
    
}
