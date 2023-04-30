/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Bassem
 */
public class TicTacToe extends Application {
    
   private Font myCustomFont;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashUI.fxml"));
        Scene scene = new Scene(root, 800, 600);

//    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.setScene(scene);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        // Navigate to the first screen after 3 seconds
        delay.setOnFinished(event -> {
            try {
                // Load the FXML file for the first screen
                Parent firstScreenRoot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));

                Scene firstScreenScene = new Scene(firstScreenRoot, 800, 600);
                stage.setScene(firstScreenScene);

            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.show();
        delay.play();

    }

    @Override
    public void init() throws Exception {
        super.init(); 
        myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 15);

    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}
