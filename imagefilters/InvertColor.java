/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;
import javax.imageio.ImageIO;



/**
 *
 * @author Shri
 */
public class InvertColor {
    private WritableImage outImage;
    private PixelWriter pw;
    private PixelReader pr;
    private BufferedImage IMG;
    private int W, H;
    
    
    
    public InvertColor(File f, Image img) throws IOException{
        this.IMG = ImageIO.read(f);
        this.W = (int)img.getWidth();
        this.H = (int)img.getHeight();
        this.pr = img.getPixelReader();
    }
    
    public Image getNegativeImage(){
        outImage = new WritableImage(W, H);
        pw = outImage.getPixelWriter();
        for(int y=0;y<H;y++){
            for(int x=0;x<W;x++){
                int p = IMG.getRGB(x, y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                
                p = (a<<24) | (r<<16) | (g<<8) | b;
                Color newColor = Color.rgb(r, g, b, a);
                pw.setColor(x, y, newColor);
            }
        }
        return outImage;
    }
    
}
