/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.Game;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class GameCardController implements Initializable {

    @FXML
    private ImageView player1img;
    @FXML
    private ImageView player2Img;

    /**
     * Initializes the controller class.
     */
    public void setData(Game game){
        Image img1=new Image(getClass().getResourceAsStream(game.getPlayer1Img()));
        player1img.setImage(img1);
         Image img2=new Image(getClass().getResourceAsStream(game.getPlayer2Img()));
        player1img.setImage(img2);
        //name
        //gameid 
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       
}
