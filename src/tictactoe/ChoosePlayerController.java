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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.AppClient;
import model.Client;

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
    private VBox onlinePlayersVBox;

    private AppClient appClient;
    private Client client;

    private Player player;
 

//    ObservableList<Player> cardList = FXCollections.observableArrayList();
    public static ObservableList<Player> onlinePlayersList = FXCollections.observableArrayList();

    @FXML
    private ListView<Player> onlinePlayersListview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();

        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }


        ClientUtility.changeFontInAllNodes(screenPane);
            

        onlinePlayersListview.setItems(onlinePlayersList);
        onlinePlayersListview.setCellFactory(param ->  new ListCell<Player>() {
            protected void updateItem(Player player, boolean empty) {
                super.updateItem(player, empty);

                if (empty || player == null || player.getUsername().equals(client.player.getUsername())) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Create a custom card view and set it as the graphic for the cell
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("PlayerCard.fxml"));
                    try {
                        HBox hbox = fxmlloader.load();
                        PlayerCardController playerCardController = fxmlloader.getController();
                        playerCardController.setData(player);
                        setGraphic(hbox);
                    } catch (IOException ex) {
                        Logger.getLogger(ChoosePlayerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });

    }

}
