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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    RequestMessageController requestMessageController;
    Stage mainWindow;
    boolean closeDialog = false;
    private DialogPane waitinfDialogPane;
    

    @Override
    public void start(Stage stage) throws Exception {
        mainWindow=stage;

        Parent root = FXMLLoader.load(TicTacToe.class.getResource("SplashUI.fxml"));
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
                Parent firstScreenRoot = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

                Scene firstScreenScene = new Scene(firstScreenRoot, 1024, 700);
                stage.setScene(firstScreenScene);

            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.show();
        stage.setResizable(false);
        delay.play();
         Platform.setImplicitExit(true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Execute the function here
                if(Client.player!=null){
                    
                    client.closeClient();
                }
        }));
    
        

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
            client.reciverRespondBooleanProperty.addListener((observable, oldValue, newValue) -> {
                // This code will execute whenever the value of the boolean property changes
                if (newValue) {

                    showmyWaitingDialog();

                } else {
                    closeWaitingDialog();
                }
                

                System.out.println("Boolean value changed from " + oldValue + " to " + newValue);
            });

            client.acceptStringProperty.addListener((observable, oldValue, newValue) -> {
                // This code will execute whenever the value of the boolean property changes
                System.out.println("for accept Boolean value changed from " + oldValue + " to " + newValue);
                if (newValue.equals("accept")) {
                    client.acceptStringProperty.set("");
                   /* try {
                        client.putInOutGame("inGame");
                    } catch (IOException ex) {
                        Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                    }*/

                    showBoard();

                } else if (newValue.equals("reject")) {
                    System.out.println("from acceptBoolean 2 Boolean value changed from " + oldValue + " to " + newValue);
                    client.acceptStringProperty.set("");
                    closeWaitingDialog();
                    showRefuseDialog();
                }

            });

        } catch (IOException ex) {

            Platform.runLater(() -> {
                Stage stage = new Stage();
                Toast.makeText(stage, "Server is off. Running on offline mode now!");
            });
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

    private void showmyDialog() {
        RequestMessageController.stageParent=mainWindow;
        try {
            
            Dialog<Void> requestDialog = new Dialog<>();
            requestDialog.initStyle(StageStyle.TRANSPARENT);
            ///////

            requestDialog.initModality(Modality.APPLICATION_MODAL);
            ///////
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestMessage.fxml"));

            DialogPane requestDialogPane = loader.load();
            client.senderNameStringProperty.addListener((observable2, oldValue2, newValue2) -> {
                requestMessageController = loader.getController();
                requestMessageController.setPlayerName(newValue2);
            });
            requestDialog.getDialogPane().setContent(requestDialogPane);
            requestDialog.getDialogPane().getScene().getWindow().sizeToScene();
// Add a rectangle with rounded corners to the stack pane

            /*StackPane root = new StackPane();
        
        
        Rectangle roundedRect = new Rectangle(400, 300);
        roundedRect.setArcWidth(20);
        roundedRect.setArcHeight(20);
        roundedRect.setFill(Color.WHITE);
        root.getChildren().add(roundedRect);
        
        
         // Create a scene with a transparent background
        Scene scene = new Scene(dialog.getDialogPane(), 450, 160, Color.TRANSPARENT);

        // Set the clip shape for the content of the dialog pane
        Rectangle sceneShape = new Rectangle(450, 160);
        sceneShape.setArcWidth(20);
        sceneShape.setArcHeight(20);
        dialog.getDialogPane().setClip(sceneShape);*/
            Window window = requestDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> {

                window.hide();
                client.replyToInviteRequest("reject");

            });
            Stage dialogStage = (Stage) window;
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.initModality(Modality.NONE);
            requestDialogPane.getScene().setFill(Color.TRANSPARENT);
            dialogStage.setScene(requestDialogPane.getScene());
            /*dialog.getDialogPane().setContent(dialogPane);

            // Set the dialog size to match the content
            dialog.getDialogPane().getScene().getWindow().sizeToScene();
            dialog.getDialogPane().getScene().setFill(Color.TRANSPARENT);*/
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Calculate the top-left position of the dialog
            double dialogX = screenBounds.getMinX() + (screenBounds.getWidth() - requestDialogPane.getPrefWidth()) / 4;
            double dialogY = screenBounds.getMinY() + (screenBounds.getHeight() - requestDialogPane.getPrefHeight()) / 4;
            requestDialog.setX(dialogX);
            requestDialog.setY(dialogY);

            /*Rectangle rect = new Rectangle((int)dialogStage.getWidth(),(int)dialogStage.getHeight());
            rect.setArcWidth(20);
            rect.setArcHeight(20);
            dialog.getDialogPane().setClip(rect);*/
            // Show the dialog as a modal dialog
            requestDialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showBoard() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineBoard.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showRefuseDialog() {
        try {

            Dialog<Void> dialogForRefuse = new Dialog<>();
            dialogForRefuse.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RefuseDialog.fxml"));

            DialogPane refuseDialogPane = loader.load();

            dialogForRefuse.getDialogPane().setContent(refuseDialogPane);
            dialogForRefuse.getDialogPane().getScene().getWindow().sizeToScene();
            Window window = dialogForRefuse.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> {

                window.hide();

            });
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Calculate the top-left position of the dialog
            double dialogX = screenBounds.getMinX() + (screenBounds.getWidth() - refuseDialogPane.getPrefWidth()) / 4;
            double dialogY = screenBounds.getMinY() + (screenBounds.getHeight() - refuseDialogPane.getPrefHeight()) / 4;
            dialogForRefuse.setX(dialogX);
            dialogForRefuse.setY(dialogY);
            dialogForRefuse.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showmyWaitingDialog() {
        try {

            Dialog<Void> waitingDialog = new Dialog<>();
            waitingDialog.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WaitingForResponse.fxml"));

            waitinfDialogPane = loader.load();

            waitingDialog.getDialogPane().setContent(waitinfDialogPane);
            waitingDialog.getDialogPane().getScene().getWindow().sizeToScene();
            Window window = waitingDialog.getDialogPane().getScene().getWindow();
            Stage xStage=(Stage) waitingDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> {
//                mainWindow.close();
                window.hide();

            });
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Calculate the top-left position of the dialog
            double dialogX = screenBounds.getMinX() + (screenBounds.getWidth() - waitinfDialogPane.getPrefWidth()) / 2;
            double dialogY = screenBounds.getMinY() + (screenBounds.getHeight() - waitinfDialogPane.getPrefHeight()) / 2;
            waitingDialog.setX(dialogX);
            waitingDialog.setY(dialogY);
            /*if (close) {
                System.out.println("I am in close");
                Platform.runLater(() -> {
                    waitingDialog.setResult(null);
                    waitingDialog.close();
                    waitingDialog.getDialogPane().getScene().getWindow().hide();
                    //dialogPane.getScene().getWindow().hide();
                });

            } else {
                waitingDialog.showAndWait();
            }*/
            waitingDialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeWaitingDialog()
    {
        System.out.println("from closepane");
        if (waitinfDialogPane != null) {
            System.out.println("from closepane not null");
        Window window = waitinfDialogPane.getScene().getWindow();
        Stage s =(Stage) window;
        s.close();
        mainWindow.close();
//        Platform.runLater(window::hide);
    }
    }

}
