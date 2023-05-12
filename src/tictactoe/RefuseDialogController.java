/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class RefuseDialogController implements Initializable {

    @FXML
    private DialogPane parent;
    @FXML
    private Label dialoBoxMsg1;
    @FXML
    private ImageView dialogBoxImg1;
    @FXML
    private Button okBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ClientUtility.changeFontInAllNodes(parent);
    }    

    @FXML
    private void onOkClicked(ActionEvent event) {
        parent.getScene().getWindow().hide();
    }
    
}
