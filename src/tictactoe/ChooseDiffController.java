/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class ChooseDiffController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Button easyButton;
    @FXML
    private ImageView easyImg;
    @FXML
    private Button meduimButton;
    @FXML
    private ImageView meduimImg;
    @FXML
    private Button hardButton;
    @FXML
    private ImageView hardImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickEasy(ActionEvent event) throws IOException {
        navigate(event,"GameBoard.fxml");
        
    }

    @FXML
    private void onClickMeduim(ActionEvent event) {
    }

    @FXML
    private void onClickHard(ActionEvent event) {
    }
      private void navigate(ActionEvent event, String url) throws IOException{
    
                // Load the FXML file for the first screen
        Parent root;
        Stage stage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        stage =  (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        GameBoardController.isVersusPC=true;
        GameBoardController gbc = loader.getController();
      


      
    }
    
}
