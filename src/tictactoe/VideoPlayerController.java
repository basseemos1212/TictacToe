/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nouran
 */
public class VideoPlayerController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Label playerName;
    @FXML
    private Button backBtn;
    @FXML
    private Button playAgainBtn;
    
    private String path;
    private static String actualPath;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       Font myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),20);
       Font myCustomFont2 = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"),35);
       
       playerName.setFont(myCustomFont2);
       actualPath="src/media/win.mp4";
       path=new File(actualPath).getAbsolutePath();
       media=new Media(new File(path).toURI().toString());
       mediaPlayer=new MediaPlayer(media);
       mediaView.setMediaPlayer(mediaPlayer);
       mediaPlayer.play();
       
       
      
        Set<Node> allNodes = parent.lookupAll("*");
        for (Node node : allNodes) 
        {
            if (node instanceof Text) 
            {
                ((Text) node).setFont(myCustomFont);

            } 
            else if (node instanceof Button) 
            {
                ((Button) node).setFont(myCustomFont);
            } 
            else if (node instanceof TextField)
            {
                ((TextField) node).setFont(myCustomFont);
            }
           
    
        }
        
       
        
        
        
    }    

    @FXML
    private void onBackClick(ActionEvent event) {
    }

    @FXML
    private void onPlayAgainClick(ActionEvent event) {
    }

    public static String getActualPath() {
        return actualPath;
    }

    public void setActualPath(String actualPath) {
        VideoPlayerController.actualPath = actualPath;
        
    }

    
    
}
