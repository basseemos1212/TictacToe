/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import model.AppClient;
import model.Client;
import model.Player;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.derby.jdbc.ClientDriver;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class SignInController implements Initializable {

    private AppClient appClient;
    private Client client;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInBtn;

    private Player player;
    //HomeScreenController homeScreenController=new HomeScreenController(player) ;

    /**
     * Initializes the controller class.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();

        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO
    }

    @FXML
    private void signUpOnclick(ActionEvent event) throws IOException {
        VideoPlayerController vc=new VideoPlayerController();
        vc.setActualPath("src/media/YouLose.mp4");
        ClientUtility.navigate(event, "videoPlayer.fxml");

    }

    private Boolean validate() {
        if (userNameTextField.getText().length() == 0 || passwordTextField.getText().length() == 0) {

            if (userNameTextField.getText().length() == 0) {


                userNameTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
                userNameTextField.setPromptText("You should enter valid username");
            }
            if (passwordTextField.getText().length() == 0) {
                passwordTextField.setPromptText("You should enter valid password");
                passwordTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
            }
            return false;

        } else {
            System.out.println("bassem");
            return true;
        }
    }

    private void goToHome(ActionEvent event, Player player) throws IOException {
        // Load the FXML file for the HomeScreen
        Parent root;
        Stage stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        root = loader.load();

        // Get the controller instance for the HomeScreen
        HomeScreenController homeScreenController = loader.getController();
        homeScreenController.setPlayer(player);

        // Set the player object as a property of the HomeScreenController
        // Show the HomeScreen
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void signInonClick(ActionEvent event) throws IOException {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        try {

            player = client.signIn(username, password);
            System.out.println("signInonClick obj =" + player.getUsername());
            goToHome(event, player);
            //navigate(event, "HomeScreen.fxml");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
