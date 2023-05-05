/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.Game;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bassem
 */
public class RecordsScreenController implements Initializable {

    private Font myCustomFont;
    @FXML
    private Pane parent;
    @FXML
    private VBox gamesVbox;
    List<Game> games = new ArrayList<>(games());
    private Vector<String> moves = new Vector<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readRecordedGames();

 

        myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 24);
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
        // TODO
    }

    private List<Game> games() {
        List<Game> gm = new ArrayList<>();

        return gm;
    }

    private void readRecordedGames() {
        String folderPath = "recordedGames";
        Vector<Game> games = new Vector<Game>();
        ObjectMapper mapper = new ObjectMapper();
        File folder = new File("src/recordedGames");
        File[] files = folder.listFiles();
        System.out.println(files.length);
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                String[] jsonArray = gson.fromJson(sb.toString(), String[].class);
                
              
//                for (String move : jsonArray) {
//                    System.out.println(move);
//                    moves.add(move);
//                }
//                System.out.println(moves);
               for(int x=3;x<jsonArray.length;x++){
                   moves.add(jsonArray[x]);
               
               
               }
                System.out.println("we are in cart fileName=");
                Game game =new Game(file.getName(), jsonArray[1], jsonArray[2], jsonArray[jsonArray.length-1],moves);
                games.add(game);
                System.out.println(jsonArray[jsonArray.length-1]);
                moves.clear();
                
     
                
               

            }
        }
                   for (int i = 0; i < games.size(); i++) {
          
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("GameCard.fxml"));
                try {
                    HBox hbox = fxmlloader.load();
                    GameCardController gCardController = fxmlloader.getController();
                    gCardController.setData(games.get(i).getPlayerName1(), games.get(i).getPlayerName2(),games.get(i).getGameName());
                    gCardController.setMoves(games.get(i).getMoves());
                    gamesVbox.getChildren().add(hbox);

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
    }
//     private void playRecordedGame() {
//        String fileName = fileRecorded;
//        File file = new File("src/recordedGames/"+fileRecorded);
//
//// Read the JSON file using a BufferedReader
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//// Parse the JSON string using Gson
//        Gson gson = new Gson();
//        String[] jsonArray = gson.fromJson(sb.toString(), String[].class);
//
//// Create a new Vector object and add the elements from the JSON array
//        for (String move : jsonArray) {
//            moves.add(move);
//        }
//       
//// Print the Vector object
//        for (String move : jsonArray) {
//            System.out.println(move);
//        }
//
//    }
}

/**
 * Initializes the controller class.
 */
