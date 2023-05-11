/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ChooseImageDailogeController implements Initializable {

    @FXML
    private DialogPane parent;
    @FXML
    private Label dialogHeaderLabel;
    @FXML
    private Button av1;
    @FXML
    private Button av2;
    @FXML
    private Button av3;
    @FXML
    private Button av4;
    @FXML
    private Button av5;
    @FXML
    private Button av6;
    private String imagePath;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Node buttonBar = parent.lookup(".button-bar");

    //buttonBar.setVisible(false);
    buttonBar.setStyle("-fx-background-color: #3B1787"); // Set background color to white


    }

  

    @FXML
    private void onClickAv1(ActionEvent event) {
        imagePath = "/assets/p1L.png";
        SignupController.imagePath = imagePath;
       
                onBack(event);

    }

    @FXML
    private void onClickAv2(ActionEvent event) {
        imagePath = "/assets/p2L.png";
        SignupController.imagePath = imagePath;
                onBack(event);

    }

    @FXML
    private void onClickAv3(ActionEvent event) {
        imagePath = "/assets/p3L.png";

        SignupController.imagePath = imagePath;
                onBack(event);

    }

    @FXML
    private void onClickAv4(ActionEvent event) {
        imagePath = "/assets/p4L.png";
        SignupController.imagePath = imagePath;
        onBack(event);

    }

    @FXML
    private void onClickAv5(ActionEvent event) {
        imagePath = "p5.png";
        imagePath = "/assets/p5L.png";
        SignupController.imagePath = imagePath;
        onBack(event);

    }

    @FXML
    private void onClickAv6(ActionEvent event) {
        imagePath = "/assets/p6L.png";
        SignupController.imagePath = imagePath;
        onBack(event);
    }

    @FXML
    private void onBack(ActionEvent event) {
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.close();
    }

}
