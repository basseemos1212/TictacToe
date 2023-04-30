/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class SignupController implements Initializable {

    @FXML
    private Text signupLabel;

    private Font myCustomFont;
    @FXML
    private TextField signupusername;
    @FXML
    private PasswordField signuppass;
    @FXML
    private PasswordField signuprepass;
    @FXML
    private Button signupbtn;
    @FXML
    private AnchorPane parent;
    @FXML
    private Hyperlink gotosignin;
    @FXML
    private ToggleButton passtoggle;
    @FXML
    private ToggleButton repasstoggle;
    @FXML
    private TextField signuppassTF;
    @FXML
    private TextField signuprepassTF;

    BooleanProperty showPassword = new SimpleBooleanProperty(false);
    BooleanProperty showRePassword = new SimpleBooleanProperty(false);
    @FXML
    private HBox passHB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 18);
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

        showHidePassword();
        validatePassword();

    }

    @FXML
    public void onSignupClicked() {
        
        String password = signuppass.getText();

        // Define a regular expression to match the password criteria
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        // Check if the password matches the regular expression
        boolean matches = password.matches(regex);

        // If the password matches the criteria, continue with form submission
        if (matches) {
            // TODO: Submit the form
            boolean isTwoPassFieldsMatch = checkIfTheTwoPasswordsMatch();
            if(isTwoPassFieldsMatch){
                //Do THE SIGN UP
                System.out.println("SIGNUP CLICKED!!");
            }
        } else {
            // If the password does not match the criteria, show an alert dialog
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Password not valid");
            alert.setHeaderText("Password does not meet the requirements.");
            alert.setContentText("Please enter a password that is at least 8 characters long and contains at least one uppercase letter, one lowercase letter, and one digit.");

            alert.showAndWait();
        }
        
      

    }
    
    private void navigate(ActionEvent event, String url) throws IOException{
    
                // Load the FXML file for the first screen
        Parent root;
        Stage stage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        stage =  (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

      
    }

    @FXML
    private void onGoToSignin(ActionEvent event) throws IOException {
        navigate(event, "SignIn.fxml");
    }

    @FXML
    private void onPassToggleChecked(ActionEvent event) {
        showPassword.set(passtoggle.isSelected());

    }

    @FXML
    private void onRePassToggleChecked(ActionEvent event) {
        showRePassword.set(repasstoggle.isSelected());

    }

    private void showHidePassword() {
        // Bind the visibility property of each label to the toggle state
        signuppass.visibleProperty().bind(showPassword.not());
        signuppass.managedProperty().bind(showPassword.not());
        signuppassTF.visibleProperty().bind(showPassword);
        signuppassTF.managedProperty().bind(showPassword);
        signuppass.textProperty().bindBidirectional(signuppassTF.textProperty());

        // Bind the visibility property of each label to the toggle state
        signuprepass.visibleProperty().bind(showRePassword.not());
        signuprepass.managedProperty().bind(showRePassword.not());
        signuprepassTF.visibleProperty().bind(showRePassword);
        signuprepassTF.managedProperty().bind(showRePassword);
        signuprepass.textProperty().bindBidirectional(signuprepassTF.textProperty());

    }

    private void validatePassword() {

        // Add a change listener to the password field
        signuppass.textProperty().addListener((observable, oldValue, newValue) -> {
            // Define a regular expression to match the password criteria
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

            // Check if the new password matches the regular expression
            boolean matches = newValue.matches(regex);

            // If the password matches the criteria, remove the red border
            if (matches) {
                passHB.setStyle("");
            } else {
                // If the password does not match the criteria, add a red border
                passHB.setStyle("-fx-border-color: red;");
            }
        });

    }
    
    public boolean checkIfTheTwoPasswordsMatch(){
        if (!signuppass.getText().equals(signuprepass.getText())) {
            
            // The passwords don't match, show an alert dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords don't match");
            alert.setContentText("Please make sure to re-enter passwords correctly.");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }

}
