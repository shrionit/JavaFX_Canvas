/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilters;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;



/**
 *
 * @author Shri
 */
public class Blur {
    private WritableImage outImage;
    private PixelWriter pw;
    private PixelReader pr;
    private Image IMG;
    private int W, H;
    
    
    
    public Blur(Image img){
        this.IMG = img;
        this.W = (int)img.getWidth();
        this.H = (int)img.getHeight();
        this.pr = img.getPixelReader();
    }
    
    public Image getBlurredImage(){
        int kernalSize = 3;
        outImage = new WritableImage(W, H);
        pw = outImage.getPixelWriter();
        for(int y=0;y<H;y++){
            for(int x=0;x<W;x++){
                double rCount = 0;
                double gCount = 0;
                double bCount = 0;
                double alpha = 0;
                int counter = 0;
                for(int i = -kernalSize;i<kernalSize;i++){
                    for(int j= -kernalSize;j<kernalSize;j++){
                        if (x + i < 0 || x + i >= W || y + j < 0 || y + j >= H) {
                            continue;
                        }
                        Color color = pr.getColor(x + i, y + j);
                        rCount += color.getRed();
                        gCount += color.getGreen();
                        bCount += color.getBlue();
                        alpha += color.getOpacity();
                        counter++;
                    }
                }
                Color newColor = Color.color(rCount/counter, gCount/counter, bCount/counter, alpha/counter);
                pw.setColor(x, y, newColor);
            }
        }
        return outImage;
    }
    
}
