/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author mohamed
 */
public class ClientUtility {

    public static Font myCustomFont = Font.loadFont(ClientUtility.class.getResourceAsStream("/fonts/gumbo.otf"), 18);

    public static void changeFontInAllNodes(Parent parent) {
        Set<Node> allNodes = parent.lookupAll("*");
        for (Node node : allNodes) {
            if (node instanceof Text) {
                ((Text) node).setFont(myCustomFont);
            } else if (node instanceof Button) {
                ((Button) node).setFont(myCustomFont);
            } else if (node instanceof TextField) {
                ((TextField) node).setFont(myCustomFont);
            } else if (node instanceof Label) {
                ((Label) node).setFont(myCustomFont);
            }
        }
    }

    
    
    public static void navigate(ActionEvent event, String url) throws IOException {

        Parent root;
        Stage stage;

        FXMLLoader loader = new FXMLLoader(ClientUtility.class.getResource(url));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
