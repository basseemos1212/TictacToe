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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class HomeScreenController implements Initializable {

    @FXML
    private AnchorPane parent;
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
    private Label name;
    @FXML
    private VBox recordVBox;
    @FXML
    private ImageView recordImg;
    @FXML
    private VBox aboutVBox;
    @FXML
    private Button recordScreenBtn;
    @FXML
    private Button aboutScreenBtn;

    /**
     * Initializes the controller class.
     */
    Font myCustomFont2 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 22);
    @FXML
    private Label scoreLabel;

    public static Player player;

    /**
     * Initializes the controller class.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO
//        printPlayer(player);
        Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 26);
        Font myCustomFont3 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 26);
        name.setFont(myCustomFont2);
        scoreLabel.setFont(myCustomFont3);
        Platform.runLater(() -> name.setText(player.getUsername()));

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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void printPlayer(Player player) {
        System.out.println("from home player is :" + player.getUsername());
    }

    private void navigate(ActionEvent event, String url) throws IOException {

        // Load the FXML file for the first screen
        Parent root;
        Stage stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void showmyDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoosePlayerDialog.fxml"));
            DialogPane dialogPane = loader.load();

            Dialog<Void> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());
            dialog.getDialogPane().setContent(dialogPane);

            // Set the dialog size to match the content
            dialog.getDialogPane().getScene().getWindow().sizeToScene();

            // Show the dialog as a modal dialog
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void pressTwoPlayer(ActionEvent event) throws IOException {
        showmyDialog();

    }

    @FXML
    private void recordScreenNav(ActionEvent event) throws IOException {
        navigate(event, "RecordsScreen.fxml");
    }

    @FXML
    private void aboutScreenNav(ActionEvent event) throws IOException {
        navigate(event, "About.fxml");
    }

    @FXML
    private void goToOnline(ActionEvent event) throws IOException {
        navigate(event, "ChoosePlayer.fxml");
    }

    @FXML
    private void pressOnline(ActionEvent event) throws IOException {
        //navigate(event,"About.fxml");
    }
    
    

}
