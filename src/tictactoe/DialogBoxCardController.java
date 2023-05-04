/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class DialogBoxCardController implements Initializable {

    @FXML
    private DialogPane parent;
    @FXML
    private Label dialogHeaderLabel;
    @FXML
    private ImageView dialogBoxImg;
    @FXML
    private Button okButton;
    @FXML
    private Label dialoBoxMsg1;
    @FXML
    private Label dialoBoxMsg2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),22);
        Font myCustomFont1 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),22);
        dialogHeaderLabel.setFont(myCustomFont);
//        dialoBoxMsg1.setFont(myCustomFont1);
//        dialoBoxMsg2.setFont(myCustomFont1);
        
        
        /*Set<Node> allNodes = parent.lookupAll("*");
        for (Node node : allNodes) {
            if (node instanceof Text) {
                ((Text) node).setFont(myCustomFont);

            } else if (node instanceof Button) {
                ((Button) node).setFont(myCustomFont);
            } else if (node instanceof TextField) {
                ((TextField) node).setFont(myCustomFont);
    }    
    
        }*/
    }    

    @FXML
    private void onClickOk(ActionEvent event) {
    }
    
}
