/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class AboutController implements Initializable {
        private Font myCustomFont;
        @FXML
        private Parent parent;
    @FXML
    private Button backBtn;


    /**
     * Initializes the controller class.
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ClientUtility.changeFontInAllNodes(parent);
        

    }    

   @FXML
    private void onBack(ActionEvent event) {
        try {
            ClientUtility.navigate(event,"HomeScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(RecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

