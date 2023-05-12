/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.util.Duration;
import model.AppClient;
import model.Client;

import model.Player;
import model.Settings;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class HomeScreenController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Button singleBtn;
    @FXML
    private ImageView singleImg;
    @FXML
    private Button twoPlayersBtn;
    @FXML
    private ImageView twoPlayersImg;
    @FXML
    private Button onlineBtn;
    @FXML
    private ImageView onlineImg;
    @FXML
    private Text name;
    @FXML
    private VBox recordVBox;
    @FXML
    private ImageView recordImg;
    @FXML
    private VBox aboutVBox;
    @FXML
    private Button recordScreenBtn;
    @FXML
    private Button aboutScreenBtn;
    private AppClient appClient;
    private Client client;
    /**
     * Initializes the controller class.
     */
    Font myCustomFont2 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 22);
    @FXML

    private Text scoreLabel;

    public Player player;

    @FXML
    private Button LogOutBtn;
    @FXML
    private ImageView playerImage;

    /**
     * Initializes the controller class.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();

        } catch (IOException ex) {

            Stage stage = new Stage();
            Toast.makeText(stage, "Server is off. Running on offline mode now!");
        }

        // TODO
//        printPlayer(player);
        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 26);
        Font myCustomFont3 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 26);
        name.setFont(myCustomFont2);

//        scoreLabel.setFont(myCustomFont2);
        if (client.player != null) {
            if (client.player.getStatus() == 1) {
                System.out.println("setPlayerValues");
                Platform.runLater(() -> setPlayerValues());
            } else {
                System.out.println("loadSettings");

                Platform.runLater(() -> loadSettings());
            }
        } else {
            System.out.println("loadSettings");

            Platform.runLater(() -> loadSettings());
        }

        //Platform.runLater(() -> scoreLabel.setText(Integer.toString(client.player.getScore())));
        //loadSettings();
        Set<Node> allNodes = parent.lookupAll("*");
        for (Node node : allNodes) {
            if (node instanceof Text) {
                ((Text) node).setFont(myCustomFont);

            } else if (node instanceof Button) {
                ((Button) node).setFont(myCustomFont);
            } else if (node instanceof TextField) {
                ((TextField) node).setFont(myCustomFont);
            }

        }
        if (client.player != null) {

            if (!checkRmbr() && client.player.getUsername() == "Guest") {
                System.out.println("btn : " + client.player.getUsername());
                System.out.println(!checkRmbr());
                LogOutBtn.setDisable(true);
                LogOutBtn.setVisible(false);
            }

        }
    }

    public void setPlayerValues() {
        if (Client.player != null) {
            System.out.println(client.player.getUsername());
            String imagePath = client.player.getImagePath();
            if (imagePath != null) {
                Image newImage = new Image(imagePath);
                playerImage.setImage(newImage);
            } else {
                Image newImage = new Image("assets/avatar.png");
                playerImage.setImage(newImage);
            }
            name.setText(client.player.getUsername());
            if (client.player.getScore() != 0) {
                scoreLabel.setText(Integer.toString(client.player.getScore()));
            }
        } else {
            Image newImage = new Image("assets/avatar.png");
            playerImage.setImage(newImage);
            name.setText("Guest");
            scoreLabel.setText("");
        }
    }

    public void printPlayer(Player player) {
        System.out.println("from home player is :" + player.getUsername());
    }

    private void navigate(ActionEvent event, String url) throws IOException {

        // Load the FXML file for the first screen
        Parent root;
        Stage stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void showmyDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoosePlayerDialog.fxml"));
            DialogPane dialogPane = loader.load();

            Dialog<Void> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());
            dialog.getDialogPane().setContent(dialogPane);

            // Set the dialog size to match the content
            dialog.getDialogPane().getScene().getWindow().sizeToScene();

            // Show the dialog as a modal dialog
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void pressTwoPlayer(ActionEvent event) throws IOException {
        showmyDialog();

    }

    @FXML
    private void recordScreenNav(ActionEvent event) throws IOException {
        navigate(event, "RecordsScreen.fxml");

    }

    @FXML
    private void aboutScreenNav(ActionEvent event) throws IOException {
        //VideoPlayerController vc=new VideoPlayerController();
        //vc.setActualPath("src/media/win.mp4");
        // navigate(event, "RequestMessage.fxml");
        VideoPlayerController vc = new VideoPlayerController();
        vc.setActualPath("src/media/win.mp4");

        Parent root;
        Stage stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("videoPlayer.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadSettings() {
        try {
            File file = new File("settings.json");
            if (file.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                Settings settings = mapper.readValue(file, Settings.class);
                if (settings.getUsername() != null) {
                    name.setText(settings.getUsername());
                }
                if (settings.getScore() != 0) {
                    scoreLabel.setText(String.valueOf(settings.getScore()));
                } else {
                    scoreLabel.setText("");

                }
                if (settings.getImagePath() != null) {
                    Image newImage = new Image(settings.getImagePath());
                    playerImage.setImage(newImage);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkRmbr() {
        File file = new File("settings.json");
        if (file.exists()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Settings settings = mapper.readValue(file, Settings.class);
                if (settings.getUsername() != null) {
                    return true; // user is already logged in
                }
            } catch (IOException ex) {
                Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false; // user is not logged in

    }

    @FXML
    private void OnLogOut(ActionEvent event) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Settings settings = new Settings();
            mapper.writeValue(new File("settings.json"), settings);
            Image newImage = new Image("assets/avatar.png");
            playerImage.setImage(newImage);

            if (client.player != null) {
                System.out.println("CLIENT IS NOT NULL");
                client.player.setUsername("Guest");
                client.closeClient();

            }
            scoreLabel.setText(" ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
        flashPass();

        name.setText("Guest");

////        Log
    }

    public void flashPass() {
        PauseTransition pause = new PauseTransition(Duration.millis(5000));
        pause.setOnFinished(event -> {
            LogOutBtn.setStyle("-fx-border-color: rgba(255, 0, 0, 0.8); -fx-border-width: 4px;-fx-border-radius: 10;");
        });
        pause.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.3), evt -> {
                    LogOutBtn.setStyle("-fx-border-color: rgba(255, 0, 0, 0.8); -fx-border-width: 4px;-fx-border-radius: 10;");
                }),
                new KeyFrame(Duration.seconds(0.6), evt -> {
                    LogOutBtn.setStyle("-fx-border-color: rgba(255, 255, 255, 0); -fx-border-width: 4px;");
                }),
                new KeyFrame(Duration.seconds(1.0), evt -> {
                    LogOutBtn.setDisable(true);
                    LogOutBtn.setVisible(false);
                })
        );
        timeline.setCycleCount(2);
        timeline.play();

    }

//    LogOutBtn.setDisable(true);
//    LogOutBtn.setVisible(false);
    @FXML
    private void onlineOnClick(ActionEvent event) {
        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();
            if (client.player.getUsername() != "Guest") {
                ClientUtility.navigate(event, "ChoosePlayer.fxml");

            } else {
                ClientUtility.navigate(event, "SignIn.fxml");
            }

        } catch (IOException ex) {
            Stage stage = new Stage();
            Toast.makeText(stage, "Server is off. Running on offline mode now!");
        }

//        try {
//            if (checkLogin()) {
//                try {
//                    ClientUtility.navigate(event, "ChoosePlayer.fxml");
//                } catch (IOException ex) {
//                    Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            } else {
//                ClientUtility.navigate(event, "SignIn.fxml");
//
//            }
//            ///if not
//        } catch (IOException ex) {
////            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
//            Toast.showToast("Server is off. Running on offline mode now!");
//        }
    }

    @FXML
    private void onSignleClick(ActionEvent event) throws IOException {
        navigate(event, "ChooseDiff.fxml");
    }

}
