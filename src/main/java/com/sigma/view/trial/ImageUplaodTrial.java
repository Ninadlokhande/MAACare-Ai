package com.sigma.view.trial;

import java.io.File;

import com.sigma.controller.ImageUploadController;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageUplaodTrial {
    String url;
    private Scene scene;
     public Scene getImageuploaderScene(Stage stage){

        Image image1 = new Image("file:Maacare-Ai\\src\\main\\resources\\assets\\images\\logo\\logo.png");
        ImageView  image1View = new ImageView(image1);
        Text messagetext= new Text("");
        messagetext.setStyle(" -fx-fill:#ff66ff; -fx-font-size : 24px; -fx-font-Weignt:Bold;");

        image1View.setFitHeight(300);
        image1View.setPreserveRatio(true);
        Button chooseFile = new Button(" Choose image to upload");
        chooseFile.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select image to upload to cloud");
            ImageUploadController imageUploadController = new ImageUploadController();
            File file = fileChooser.showOpenDialog(stage);
            if(file !=null){
                System.out.println("Selected Image us "+file.getAbsolutePath());
                url = imageUploadController.imageUpload(file);
                if(url !=null && !url.isBlank()){
                    System.out.println("image uploaded");
                    image1View.setImage(new Image(url));
                    messagetext.setText(" Image Uploaded Suceessfully");


                }
                else{
                    System.out.println(" Image upload Failed Url is Null");
                    messagetext.setText("error !!!  could not upload the image to cloud try again after a while");
                }
            }
        }); 

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20));
        vBox.setStyle("-fx-background-color:black;-fx-alignment:center");
        vBox.getChildren().addAll(messagetext,image1View,chooseFile);
        Scene scene = new Scene(vBox, 500,500);
        return scene;

        
     }
    
}
