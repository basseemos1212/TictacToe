/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class VideoPlayerController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Label playerName;
    @FXML
    private Button backBtn;

    private String path;
    private static String actualPath;
    public static String name;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private AnchorPane parent;

    public static MeduimPCController meduimPCController;
     public static OnlineBoardController onlineBoardController;
     public static boolean isonline = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 20);
        Font myCustomFont2 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 35);

        Platform.runLater(() -> {
            playerName.setFont(myCustomFont2);
            playerName.setText(name);
        });

        path = new File(actualPath).getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

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

    }

    @FXML
    private void onBackClick(ActionEvent event) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
        if(isonline){
            this.onlineBoardController.closeBoard();

        }else{
            this.meduimPCController.closeBoard();

        }

    }


    public static String getActualPath() {
        return actualPath;
    }

    public void setActualPath(String actualPath) {
        VideoPlayerController.actualPath = actualPath;

    }

    public void setGameBoardController(MeduimPCController meduimPCController) {

        this.meduimPCController = meduimPCController;
    }
    
    
    public void setOnlineGameBoardController(OnlineBoardController onlineBoardController) {

        this.onlineBoardController = onlineBoardController;
    }

}
