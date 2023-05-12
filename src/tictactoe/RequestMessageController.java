/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.AppClient;
import model.Client;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class RequestMessageController implements Initializable {

    @FXML
    private DialogPane parent;
    @FXML
    private Label playerName;
    @FXML
    private HBox bottom;
    @FXML
    private Button acceptBtn;
    @FXML
    private Button rejectBtn;
    private Client client;
    private AppClient appClient;
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientUtility.changeFontInAllNodes(parent);
        

        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();
            //client.replyToInviteRequest("none");


        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onAcceptClick(ActionEvent event) {
        client.replyToInviteRequest("accept");
        parent.getScene().getWindow().hide();

    }

    @FXML
    private void onRejectClick(ActionEvent event) {
        client.replyToInviteRequest("reject");
        parent.getScene().getWindow().hide();

    }
    public void setPlayerName(String senderName)
    {
        playerName.setText(senderName);
    }
}
