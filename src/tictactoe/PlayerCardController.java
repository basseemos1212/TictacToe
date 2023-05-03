/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Models.Game;
import Models.Player;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class PlayerCardController implements Initializable {

    @FXML
    private ImageView playerImg;
    @FXML
    private Label playerName;
    @FXML
    private HBox cardHBox;
    @FXML
    private Button inviteBtn;

    /**
     * Initializes the controller class.
     */
    public void setData(Player player){
        Image img=new Image(getClass().getResourceAsStream(player.getImagePath()));
        playerImg.setImage(img);
        playerName.setText(player.getUsername());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),20);
        
        
        Set<Node> allNodes = cardHBox.lookupAll("*");
        for (Node node : allNodes)
        {
            if (node instanceof Text)
            {
                ((Text) node).setFont(myCustomFont);

            }
            else if (node instanceof Button)
            {
                ((Button) node).setFont(myCustomFont);
            } 
            else if (node instanceof TextField)
            {
                ((TextField) node).setFont(myCustomFont);
            }
            else if (node instanceof Label)
            {
                ((Label) node).setFont(myCustomFont);
            } 
        }    
    
    }
}
