/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class SplashUIController implements Initializable {

    @FXML
    private ImageView logoImage;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        addRandomShapeImages(anchorPane);

        Glow glow = new Glow(0);
        SepiaTone sepia = new SepiaTone(0);
        sepia.setInput(glow);
        logoImage.setEffect(sepia);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(sepia.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(glow.levelProperty(), 1),
                        new KeyValue(sepia.levelProperty(), 1)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(sepia.levelProperty(), 0))
        );

        timeline.play();

    }

//    public void addRandomShapeImages(AnchorPane anchorPane) {
//        Random rand = new Random();
////        String[] imagePaths = {"team4xo.assets/O.png", "team4xo.assets/rect.png", "team4xo.assets/star.png", "team4xo.assets/tri.png", "team4xo.assets/tri2.png", "team4xo.assets/x.png"};
//        String[] imagePaths = {
//            "file:///C:/Users/moham/Desktop/xoassets/star.png",
//            "file:///C:/Users/moham/Desktop/xoassets/tri2.png",
//            "file:///C:/Users/moham/Desktop/xoassets/rect.png",
//            "file:///C:/Users/moham/Desktop/xoassets/tri.png",
//            "file:///C:/Users/moham/Desktop/xoassets/O.png",
//            "C:/Users/moham/Desktop/xoassets/x.png"};
//        for (int i = 0; i < 10; i++) {
//            String imagePath = imagePaths[rand.nextInt(imagePaths.length)];
//            Image image = new Image(imagePath);
//            int x = rand.nextInt((int) anchorPane.getWidth());
//            int y = rand.nextInt((int) anchorPane.getHeight());
//            int size = rand.nextInt(50) + 20;
//
//            ImageView imageView = new ImageView(image);
//            imageView.setX(x);
//            imageView.setY(y);
//            imageView.setFitWidth(size);
//            imageView.setFitHeight(size);
//            imageView.setOpacity(0.5);
//            anchorPane.getChildren().add(imageView);
//        }
//    }

}
