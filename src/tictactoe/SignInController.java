/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
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
import javafx.scene.control.Button;
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

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private Button sgnInButton;
    @FXML
    private Button signUpButton;
   

    /**
     * Initializes the controller class.
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO
    }    

    @FXML
    private void signIn(ActionEvent event) throws SQLException, IOException{
         
       /* if(validate()){
                CheckSignIn(userNameTextField.getText(), passwordTextField.getText());
                System.out.println("valid");
           
        }else{
            System.out.println("not valid");
        }*/
       
       navigate(event,"HomeScreen.fxml");

    }

    @FXML
    private void signUp(ActionEvent event) {
       
    }  
    private Boolean validate(){
                if(userNameTextField.getText().length()==0||passwordTextField.getText().length()==0){
         
           
            if(userNameTextField.getText().length()==0){
                userNameTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
                userNameTextField.setPromptText("You should enter valid username");
            } 
            if(passwordTextField.getText().length()==0){
                passwordTextField.setPromptText("You should enter valid password");
                passwordTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
            }
           return false;
            
        }else{
            System.out.println("bassem");
                  return true;
            }
    }
    private void CheckSignIn(String username,String password){
        
        try {
            Connection con;
            DriverManager.registerDriver(new ClientDriver());
            con =DriverManager.getConnection("jdbc:derby://localhost:1527/tictactoe","root","root");            
            PreparedStatement pst=con.prepareStatement("SELECT * FROM ROOT.PLAYER where username=? ",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, username);
            ResultSet rs=pst.executeQuery();
            
            if(rs.first()){
               if(password.equals(rs.getString("pasword"))){
                   
               
               }else{
                passwordTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
                passwordTextField.clear();
                passwordTextField.setPromptText("Incorrect password");
               }
                
            
            }else{
                userNameTextField.setStyle("-fx-border-color: red ; -fx-border-widrh:2px");
                userNameTextField.clear();
                userNameTextField.setPromptText("username Dont Exist");
           
                
            }
          
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
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
}
