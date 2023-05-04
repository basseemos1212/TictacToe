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


}
