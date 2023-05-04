/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import model.Game;
import model.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class ChoosePlayerController implements Initializable {

    @FXML
    private AnchorPane screenPane;
    private Font myCustomFont;
    @FXML
    private Label onlinePlayersLabel;
    @FXML
    private VBox onlinePlayersVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ClientUtility.changeFontInAllNodes(screenPane);

        List<Player> player = new ArrayList<>(players());
        for (int i = 0; i < player.size(); i++) {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("PlayerCard.fxml"));
            try {
                HBox hbox = fxmlloader.load();
                PlayerCardController playerCardController = fxmlloader.getController();
                playerCardController.setData(player.get(i));
                onlinePlayersVBox.getChildren().add(hbox);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }

    private List<Player> players() {

        List<Player> playersList = new ArrayList<>();
        Player player1 = new Player();
        player1.setUsername("Farah Mohamed");
        player1.setImagePath("/assets/avatar.png");

        playersList.add(player1);
        playersList.add(player1);
        playersList.add(player1);
        playersList.add(player1);
        playersList.add(player1);
        playersList.add(player1);
        playersList.add(player1);

        return playersList;
    }
}
