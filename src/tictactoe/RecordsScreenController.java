/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Models.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class RecordsScreenController implements Initializable {
    private Font myCustomFont;
    @FXML
    private Pane parent;
    @FXML
    private VBox gamesVbox;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Game> games=new ArrayList<>(games());
        for(int i=0;i<games.size();i++){
            FXMLLoader fxmlloader=new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("GameCard.fxml"));
            try{
            HBox hbox=fxmlloader.load();
            GameCardController gCardController=fxmlloader.getController();
            gCardController.setData(games.get(i));
            gamesVbox.getChildren().add(hbox);
            
            
            } catch(IOException e){
                e.printStackTrace();
            
            }
        
        }

  myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 24);
        Set<Node> allNodes = parent.lookupAll("*");
        for (Node node : allNodes) {
            if (node instanceof Text) {
                ((Text) node).setFont(myCustomFont);

            } else if (node instanceof Button) {
                ((Button) node).setFont(myCustomFont);
            } else if (node instanceof TextField) {
                ((TextField) node).setFont(myCustomFont);
            }else if (node instanceof Label) {
                ((Label) node).setFont(myCustomFont);
            }
           
        }
        // TODO
    } 
    private List<Game> games(){
        List<Game> gm=new ArrayList<>();
        Game game1=new Game();
        game1.setGameID("first");
        game1.setPlayer1Img("/assets/avatar.png");
        game1.setPlayer2Img("/assets/single.png");
        gm.add(game1);
          Game game2=new Game();
        game2.setGameID("first");
        game2.setPlayer1Img("/assets/avatar.png");
        game2.setPlayer2Img("/assets/single.png");
        gm.add(game2);
          Game game3=new Game();
        game3.setGameID("first");
        game3.setPlayer1Img("/assets/avatar.png");
        game3.setPlayer2Img("/assets/single.png");
        gm.add(game1);
          Game game4=new Game();
        game4.setGameID("first");
        game4.setPlayer1Img("/assets/avatar.png");
        game4.setPlayer2Img("/assets/single.png");
        gm.add(game1);
        gm.add(game1);
        gm.add(game1);
        gm.add(game1);
        gm.add(game1);
        gm.add(game1);
        gm.add(game1);
             
        
        
        return gm;
    }


}


    /**
     * Initializes the controller class.
     */
   