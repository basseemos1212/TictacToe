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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class OfflineVideoController implements Initializable {

    @FXML
    private Label playerName;
    @FXML
    private Button backButton;
    @FXML
    private Button playAgainButton;
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    @FXML
    private AnchorPane parent;
    private GameBoardController gameBoardController;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setVideo(String videoFile) {
        String path = new File("src/media/" + videoFile + ".mp4").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());

        //Media media = new Media(getClass().getResource(videoFile).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void setPlayerName(String name) {
        playerName.setText(name);
    }

    public void showVideo(String playerName, boolean didThePlayerOneWin) {
        setPlayerName(playerName);
        if (didThePlayerOneWin) {
            setVideo("win");
        } else {
            setVideo("YouLose");
        }

    }

    @FXML
    private void onBackClick(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        this.gameBoardController.closeBoard();

    }

    @FXML
    private void onPlayAgainClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        this.gameBoardController.playAgain();

    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

}
