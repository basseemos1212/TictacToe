/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class GameCardController implements Initializable {

    private ImageView player1img;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
//    private String player1 = player1Name.getText();
//    private String player2 = player2Name.getText();
    Vector<String> moves = new Vector<>();
    @FXML
    private Button playRecord;
    private String fileName;

    public Vector<String> getMoves() {
        return moves;
    }

    public void setMoves(Vector<String> moves) {
        this.moves = moves;
    }

    /**
     * Initializes the controller class.
     */
    public void setData(String player1, String player2, String fileName) {
        player1Name.setText(player1);
        player2Name.setText(player2);
        this.fileName = fileName;

        //name
        //gameid 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   private void navigate(ActionEvent event, String url, String player1, String player2, Vector<String> moves, String fileName) throws IOException {
        GameBoardController.fileRecorded=fileName;
    GameBoardController.playRecord=true;
     GameBoardController.player1=player1;
      GameBoardController.player2=player2;

    // Load the FXML file for the first screen
    FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    Parent root = loader.load();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // get a reference to the GameBoardController instance and set the player names and file name
    GameBoardController gbc = loader.getController();
  

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

    @FXML
    private void onClickPlay(ActionEvent event) throws IOException {
        navigate(event, "GameBoard.fxml", player1Name.getText(), player2Name.getText(), moves, fileName);

    }

}