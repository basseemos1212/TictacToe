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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class HomeScreenController implements Initializable {

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
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO
        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),26);
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

      
    }

    @FXML
    private void pressTwoPlayer(ActionEvent event) throws IOException {
        navigate(event,"GameBoard.fxml");
    }
        
}
    
