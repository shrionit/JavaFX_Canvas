package imagefilters;
/**
 *
 * @author Shri
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;


public class Filters {
    private static WritableImage outImage;
    private static PixelWriter pw;
    private static PixelReader pr;
    private static Image IMG;
    private static int W, H;
    private static DecimalFormat formatter = new DecimalFormat("#0.00");
    private static Color c;
    private static char[] pix = new char[]{'#', '@', '&', '$', '*', '+', '=', '~', '-', '.'};
    private static double[][] pixels;
    private static File file;
    private static FileOutputStream fos;
    private static BufferedWriter bw;
    
    //for textoutput
    private static StringBuilder sb;
    
    
    //for blur
    public static Image normalBlur(Image originalImage){
        IMG = originalImage;
        W = (int)originalImage.getWidth();
        H = (int)originalImage.getHeight();
        pr = originalImage.getPixelReader();
        
        //code for blur
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
    
    
    //for black&white
    public static Image blackandwhiteImage(Image originalImage){
        IMG = originalImage;
        W = (int)originalImage.getWidth();
        H = (int)originalImage.getHeight();
        pr = originalImage.getPixelReader();
        
        //code for b&w
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
    
    //for negative
    public static Image negativeImage(Image originalImage){
        IMG = originalImage;
        W = (int)originalImage.getWidth();
        H = (int)originalImage.getHeight();
        pr = originalImage.getPixelReader();
        
        //code for b&w
        outImage = new WritableImage(W, H);
        pw = outImage.getPixelWriter();
        for(int y=0;y<H;y++){
            for(int x=0;x<W;x++){
                Color color = pr.getColor(x, y);
                double newR = 1.0 - color.getRed();
                double newG = 1.0 - color.getGreen();
                double newB = 1.0 - color.getBlue();
                Color newColor = Color.color(newR, newG, newB);
                pw.setColor(x, y, newColor);
            }
        }
        return outImage;
    }
    
    
    
    public static String getTextOutPut(Image image){
        int iW = (int)image.getWidth();
        int iH = (int)image.getHeight();
        pixels = new double[iH][iW];
        sb = new StringBuilder((iW+1)*iH);
        pr = image.getPixelReader();
        
        for(int y=0;y<iH;y++){
            if(sb.length() != 0)sb.append("\n");
            for(int x=0;x<iW;x++){
                c = pr.getColor(x, y);
                pixels[y][x] = Double.parseDouble(formatter.format((c.getBrightness())))*100;
                //log(pixels[y][x]);
                sb.append(stringFetcher(pixels[y][x])); 
            }
        }
        return sb.toString();
    }
    
    
    private static char stringFetcher(double g){
        final char str;

		if (g >= 95.0) {
			str = ' ';
		} else if (g >= 90.0) {
			str = '.';
		} else if (g >= 85.0) {
			str = ',';
		} else if (g >= 80.0) {
			str = ':';
		} else if (g >= 75.0) {
			str = ';';
		} else if (g >= 70.0) {
			str = 'i';
		} else if (g >= 65.0) {
			str = 'r';
		} else if (g >= 60.0) {
			str = 'j';
                } else if (g >= 55.0) {
                        str = 'l';
                } else if (g >= 50.0){
                        str = 'b';
                } else if (g >= 45.0){
                        str = 'd';
                } else if (g >= 40.0){
                        str = 'h';
                } else if (g >= 35.0){
                        str = '6';
                } else if (g >= 30.0){
                        str = '9';
                } else if (g >= 25.0){
                        str = '8';
                } else if (g >= 20.0){
                        str = 'B';
                } else if (g >= 15.0){
                        str = 'Q';
                } else if (g >= 10.0){
                        str = '&';
                } else if (g >= 5.0){
                        str = '#';
		} else {
			str = '@';
		}
		return str;
    }
    
    
    
//    public static File getTextOutPut(Image originalImage) throws IOException{
//        IMG = originalImage;
//        int iW = (int)IMG.getWidth();
//        int iH = (int)IMG.getHeight();
//        pixels = new double[iH][iW];
//        pr = originalImage.getPixelReader();
//        
//        try {
//            file = new File("imageOut.txt");
//            fos = new FileOutputStream(file);
//            bw = new BufferedWriter(new OutputStreamWriter(fos));
//
//             for(int y=0;y<iH;y++){
//                for(int x=0;x<iW;x++){
//                    c = pr.getColor(x, y);
//                    pixels[y][x] = Double.parseDouble(formatter.format((c.getBrightness())));
//                    if(pixels[y][x]*10 == 0.0){
//                        bw.write('#');
//                        //System.out.print("#");
//                    }
//                    if(pixels[y][x]*10 == 1.0){
//                        bw.write('.');
//                        //System.out.print(".");
//                    }
//                    int i;
//                    if((i = (int)(pixels[y][x]*10)-1) == -1){
//                        i = 0;
//                    }
//                    bw.write(pix[i]);
//                    //System.out.print(pix[i]);
//                }
//                
//                bw.newLine();
//                //System.out.print("\n");
//            }
//            bw.close();
//            
//            
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Filters.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return file;
//
//    }
}
