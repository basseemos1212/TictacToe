/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import model.AppClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Client;

/**
 *
 * @author Bassem
 */
public class TicTacToe extends Application {

    private Font myCustomFont;
    AppClient appClient;
    Client client;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashUI.fxml"));
        Scene scene = new Scene(root, 1024, 700);
        //Complete exit whenever the window is closed
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

//    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.setScene(scene);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        // Navigate to the first screen after 3 seconds
        delay.setOnFinished(event -> {
            try {
                // Load the FXML file for the first screen
                Parent firstScreenRoot = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

                Scene firstScreenScene = new Scene(firstScreenRoot, 1024, 700);
                stage.setScene(firstScreenScene);

            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.show();
        stage.setResizable(false);
        delay.play();

    }

    @Override
    public void init() throws Exception {
        super.init();
        myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 15);
        

        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();
            client.myBooleanProperty.addListener((observable, oldValue, newValue) -> {
                // This code will execute whenever the value of the boolean property changes
                if (newValue) {
                    showmyDialog();

                }
                
                System.out.println("Boolean value changed from " + oldValue + " to " + newValue);
            });
            client.acceptBooleanProperty.addListener((observable, oldValue, newValue) -> {
                // This code will execute whenever the value of the boolean property changes
                if (newValue) {
                    showBoard();

                }
                
                System.out.println("Boolean value changed from " + oldValue + " to " + newValue);
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

    private void showmyDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestMessage.fxml"));
            DialogPane dialogPane = loader.load();

            Dialog<Void> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> {

                window.hide();
                client.replyToInviteRequest("reject");

            });
            dialog.getDialogPane().setContent(dialogPane);

            // Set the dialog size to match the content
            dialog.getDialogPane().getScene().getWindow().sizeToScene();

            // Show the dialog as a modal dialog
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showBoard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}