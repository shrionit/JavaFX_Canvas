/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilters;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;


/**
 *
 * @author Shri
 */
public class ImageFilters extends Application{

    private double W = 800;
    private double H = 600;
    private Stage stage;
    private ChoiceBox cb;
    private Image originalImage, modifiedImage;
    private Button applyFilter, chBT, saveBT;
    private Label Flb, Ilb;
    private File inImage;
    private boolean flagForBlackBG = true, textViewSelected = false;
    private Scene scene;
    private TextArea textView;
    
    
    private void log(Object o){System.out.println(o);}
    
    private Pane getPane(){
        Pane pane = new Pane();
        HBox hbox = new HBox();
        HBox imgBox = new HBox();
        imgBox.setLayoutY(60);
        hbox.setStyle("-fx-border-width: 5px;-fx-border-color: green;");
        hbox.setLayoutX(15);
        hbox.setLayoutY(5);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 5, 0, 15));
        Ilb = new Label("Select Image: ");
        Ilb.setLayoutY(10);
        chBT = new Button("Browse");
        chBT.setMinWidth(120);
        applyFilter = new Button("Apply");
        applyFilter.setMinWidth(100);
        saveBT = new Button("Save");
        saveBT.setMinWidth(100);
        ImageView imageV = new ImageView();
        chBT.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            List<String> exts = Arrays.asList("*.jpg", "*.png", "*.jpeg");
            fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", exts)
            );
            inImage = fc.showOpenDialog(stage);
            if(inImage == null){
                System.out.println("null");
            }else{
                chBT.setText(inImage.getName());
                Image img = new Image("file:\\"+inImage.getAbsolutePath());
                originalImage = img;
                imageV.setFitWidth(400);
                imageV.setFitHeight(500);
                imageV.setLayoutY(100);
                imageV.setImage(img);
                //imgBox.getChildren().add(imageV);
            }
            if(!pane.getChildren().contains(imgBox)){
                pane.getChildren().add(imgBox);
            }            
            
        });
        
        Flb = new Label("Select Filter: ");
        cb = new ChoiceBox();
        cb.getItems().add("Blur");
        cb.getItems().add("InvertColor");
        cb.getItems().add("B&W");
        cb.getItems().add("ImageToText");
        ImageView modV = new ImageView();
        applyFilter.setOnAction(e -> {
            if(originalImage!=null){
                if(((String)cb.getValue()).equals("Blur")){                    
                    modifiedImage = Filters.normalBlur(originalImage);
                    originalImage = modifiedImage;
                    textViewSelected = false;
                    if(pane.getChildren().contains(textView)){
                        pane.getChildren().remove(textView);
                        pane.getChildren().add(imgBox);
                    }
                    if(modifiedImage != null){
                        modV.setFitWidth(400);
                        modV.setFitHeight(500);
                        modV.setLayoutY(60);
                        modV.setImage(modifiedImage);
                        System.out.println("Blur applied");
                    }            
                }else if(((String)cb.getValue()).equals("B&W")){                    
                    modifiedImage = Filters.blackandwhiteImage(originalImage);
                    originalImage = modifiedImage;
                    flagForBlackBG = true;
                    textViewSelected = false;
                    if(pane.getChildren().contains(textView)){
                        pane.getChildren().remove(textView);
                        pane.getChildren().add(imgBox);
                    }
                    if(modifiedImage != null){
                        modV.setFitWidth(400);
                        modV.setFitHeight(500);
                        modV.setLayoutY(60);
                        modV.setImage(modifiedImage);
                        //imgBox.getChildren().add(modV);
                        System.out.println("B&W applied");
                    }
                    
                }else if(((String)cb.getValue()).equals("ImageToText")){
                    textView = new TextArea();
                    textView.autosize();
                    textView.setLayoutY(60);
                    textView.setEditable(false);
                    textView.setWrapText(false);
                    textView.setPrefSize(800, 540);
                    if(flagForBlackBG){
                        textView.setStyle("text-area-background: black;-fx-text-fill: white;");
                    }
                    textView.setFont(Font.font("Monospaced", FontPosture.REGULAR, 5));
                    textView.setText(Filters.getTextOutPut(originalImage));
                    textViewSelected = true;
                    pane.getChildren().remove(imgBox);
                    pane.getChildren().add(1, textView);
                }else if(((String)cb.getValue()).equals("InvertColor")){
                    modifiedImage = Filters.negativeImage(originalImage);
                    originalImage = modifiedImage;
                    flagForBlackBG = true;
                    textViewSelected = false;
                    if(pane.getChildren().contains(textView)){
                        pane.getChildren().remove(textView);
                        pane.getChildren().add(imgBox);
                    }
                    if(modifiedImage != null){
                        modV.setFitWidth(400);
                        modV.setFitHeight(500);
                        modV.setLayoutY(60);
                        modV.setImage(modifiedImage);
                        //imgBox.getChildren().add(modV);
                        System.out.println("B&W applied");
                    }
                }
            }
        });
        
        saveBT.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            List<String> exts = Arrays.asList("*.jpg", "*.png", "*.jpeg");
            fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", exts)
            );
            File outImage = fc.showSaveDialog(stage);
            if(textViewSelected){
                SnapshotParameters snapshotParameters = new SnapshotParameters();
                snapshotParameters.setFill(Color.TRANSPARENT);
                Image snapImage = textView.snapshot(snapshotParameters, null);
                
                if(outImage == null){
                System.out.println("Please select where to save.");
                }else{
                    saveToFile(snapImage, outImage.getAbsolutePath());                
                }
            } else {
                if(outImage == null){
                    System.out.println("Please select where to save.");
                }else{
                    saveToFile(modifiedImage, outImage.getAbsolutePath());
                }
            }
        });
        
        imgBox.getChildren().addAll(imageV, modV);
        hbox.getChildren().addAll(Ilb, chBT, Flb, cb, applyFilter, saveBT);
        
        //-------------------------------------------------------------
        
        
        
        
        
        
        
        
        //--------------------------------------------------------------
        pane.getChildren().add(hbox);
        return pane;
    }
    
    private void saveToFile(Image img, String path){
        System.out.println("method called");
        File out = new File(path);
        String ext = getFileExtension(out);
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
        if(out!=null){
            try{
                System.out.println("inside try");
                ImageIO.write(bImage, "png", out);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    
    private String getFileExtension(File file){
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if(lastIndexOf == -1){
            return "";
        }
        return name.substring(lastIndexOf);
    }
    
    public void start(Stage primaryStage) throws Exception{
        scene = new Scene(getPane(), W, H);
        scene.getStylesheets().add("textAreaStyle.css");
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}