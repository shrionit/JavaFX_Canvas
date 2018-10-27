/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilters;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 *
 * @author Shri
 */
public class BlackAndWhite {
    private WritableImage outImage;
    private PixelWriter pw;
    private PixelReader pr;
    private Image IMG;
    private int W, H;
     
    public BlackAndWhite(Image img){
        this.IMG = img;
        this.W = (int)img.getWidth();
        this.H = (int)img.getHeight();
        this.pr = img.getPixelReader();
    }
    
    public Image getModifiedImage(){
        outImage = new WritableImage(W, H);
        pw = outImage.getPixelWriter();
        for(int y=0;y<H;y++){
            for(int x=0;x<W;x++){
                Color color = pr.getColor(x, y);
                double brightness = color.getBrightness();
                Color newColor = Color.color(brightness, brightness, brightness);
                pw.setColor(x, y, newColor);
            }
        }
        return outImage;
    }
    
}
