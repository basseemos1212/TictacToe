/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class ChoosePlayerDialogController implements Initializable {

    @FXML
    private DialogPane parent;
    @FXML
    private Label dialogHeaderLabel;
    private TextField p1TextField;
    private TextField p2TextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void onClickOk(ActionEvent event) throws IOException{
        Stage stage = (Stage) parent.getScene().getWindow();
          stage.close();
          navigate(event,"GameBoard.fxml");
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
        GameBoardController.player1=p1TextField.getText();
        GameBoardController.player2=p2TextField.getText();
        GameBoardController gbc = loader.getController();
      


      
    }
    
}
