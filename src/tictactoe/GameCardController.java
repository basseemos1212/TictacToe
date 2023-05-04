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
import javafx.scene.control.Label;
import javafx.scene.image.Image;

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

    /**
     * Initializes the controller class.
     */
    public void setData(String player1 ,String player2){
        player1Name.setText(player1);
        player2Name.setText(player2);
        //name
        //gameid 
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       
}
