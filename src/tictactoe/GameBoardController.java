/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Models.AppClient;
import Models.Client;
import Models.Game;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Bassem
 */
public class GameBoardController implements Initializable {

    private Image xImage = new Image("/assets/x_1.png");

    private Image oImage = new Image("/assets/o_1.png");
    public static Boolean isRecorded = false;
    private Boolean isRecording = false;

    int xoCounter = 0;
    int test = 0;

    boolean endOFGame = false;

    @FXML
    private ImageView playerOneImg;
    @FXML
    private Label playerOneName;
    @FXML
    private ImageView playerOneSmbl;
    @FXML
    private GridPane boradGridPane;
    @FXML
    private Button gameBoardBtn1;
    @FXML
    private Button gameBoardBtn2;
    @FXML
    private Button gameBoardBtn3;
    @FXML
    private Button gameBoardBtn4;
    @FXML
    private Button gameBoardBtn5;
    @FXML
    private Button gameBoardBtn6;
    @FXML
    private Button gameBoardBtn7;
    @FXML
    private Button gameBoardBtn8;
    @FXML
    private Button gameBoardBtn9;
    @FXML
    private ImageView playerOneImg2;
    @FXML
    private Label playerOneName2;
    @FXML
    private ImageView playerOneSmbl2;
    private AppClient appClient;
    private Client client;
    private int gameID;
    private int order = 0;

    @FXML
    private BorderPane borderPane;
    private Font myCustomFont;
    private Vector<String> moves = new Vector<>();
    Button[] buttons = {gameBoardBtn1, gameBoardBtn2, gameBoardBtn3, gameBoardBtn4, gameBoardBtn5, gameBoardBtn6, gameBoardBtn7, gameBoardBtn8, gameBoardBtn9};
    @FXML
    private Button recordButton;

    @FXML
    public void onClick(ActionEvent event) throws IOException {

        Button onClick = (Button) event.getSource();
        System.out.println("test= " + test++);
        String move = String.valueOf(onClick.idProperty().get().charAt(12));
        order = order + 1;

        if (onClick.getText().equals("") && endOFGame == false) {
            if (xoCounter == 0) {
                onClick.setText("x");
                System.out.println(onClick.idProperty().get().charAt(12));

                ImageView xImageView = new ImageView(xImage);
                xImageView.autosize();
                onClick.setGraphic(xImageView);
                moves.add(move);
                xoCounter = 1;
                client.createMove(gameID, Integer.valueOf(move), order, "x");
            } else {
                onClick.setText("o");

                ImageView oImageView = new ImageView(oImage);
                oImageView.autosize();
                onClick.setGraphic(oImageView);
                moves.add(move);
                xoCounter = 0;
                client.createMove(gameID, Integer.valueOf(move), order, "o");

            }
        }
        if (!endOFGame) {
            calculateResult();

        } else {

        }
    }

    public void RecordClick(Button bt) {
        System.out.println("test= " + test++);
        String move = String.valueOf(bt.idProperty().get().charAt(12));

        if (bt.getText().equals("") && endOFGame == false) {
            if (xoCounter == 0) {
                bt.setText("x");
                System.out.println(bt.idProperty().get().charAt(12));
                moves.add(move);
                ImageView xImageView = new ImageView(xImage);
                bt.setGraphic(xImageView);

                xoCounter = 1;

            } else {
                bt.setText("o");
                ImageView oImageView = new ImageView(oImage);
                moves.add(move);
                bt.setGraphic(oImageView);
                xoCounter = 0;

            }
        }
        if (!endOFGame) {

            calculateResult();

        } else {

        }

    }

    public void calculateResult() {
        String res1 = gameBoardBtn1.getText();
        String res2 = gameBoardBtn2.getText();
        String res3 = gameBoardBtn3.getText();
        String res4 = gameBoardBtn4.getText();
        String res5 = gameBoardBtn5.getText();
        String res6 = gameBoardBtn6.getText();
        String res7 = gameBoardBtn7.getText();
        String res8 = gameBoardBtn8.getText();
        String res9 = gameBoardBtn9.getText();

        if (res1.equals("x")) {
            if (res1.equals(res2) && res1.equals(res3)) {
                System.out.println("X win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn3);

                endOFGame = true;
            } else if (res1.equals(res4) && res1.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn7);

                endOFGame = true;
            }
        } else if (res1.equals("o")) {
            if (res1.equals(res2) && res1.equals(res3)) {
                System.out.println("O win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn3);

                endOFGame = true;
            } else if (res1.equals(res4) && res1.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn7);
                endOFGame = true;
            }
        }

        if (res5.equals("x")) {
            if (res5.equals(res4) && res5.equals(res6)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn6);
                endOFGame = true;
            } else if (res5.equals(res2) && res5.equals(res8)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn8);

                endOFGame = true;
            } else if (res5.equals(res1) && res5.equals(res9)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn9);

                endOFGame = true;
            } else if (res5.equals(res3) && res5.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn3);
                flashButton(gameBoardBtn7);

                endOFGame = true;
            }
        } else if (res5.equals("o")) {
            if (res5.equals(res4) && res5.equals(res6)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn6);

                endOFGame = true;
            } else if (res5.equals(res2) && res5.equals(res8)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn8);

                endOFGame = true;
            } else if (res5.equals(res1) && res5.equals(res9)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn9);

                endOFGame = true;
            } else if (res5.equals(res3) && res5.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn3);
                flashButton(gameBoardBtn7);

                endOFGame = true;
            }
        }

        if (res9.equals("x")) {
            if (res9.equals(res6) && res9.equals(res3)) {
                System.out.println("X win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn6);
                flashButton(gameBoardBtn3);

                endOFGame = true;
            } else if (res9.equals(res8) && res9.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn8);
                flashButton(gameBoardBtn7);
                endOFGame = true;
            }
        } else if (res9.equals("o")) {
            if (res9.equals(res6) && res9.equals(res3)) {
                System.out.println("O win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn6);
                flashButton(gameBoardBtn3);
                endOFGame = true;
            } else if (res9.equals(res8) && res9.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn8);
                flashButton(gameBoardBtn7);

                endOFGame = true;
            }
        }
    }

    public void flashButton(Button button) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.3), evt -> {
                    button.setStyle("-fx-background-color: yellow; -fx-text-fill: yellow;");
                }),
                new KeyFrame(Duration.seconds(0.6), evt -> {
                    button.setStyle("-fx-background-color: #3B1787; -fx-text-fill: #3B1787;");
                })
        );
        timeline.setCycleCount(7);

        timeline.play();
    }

    public void test() {

        for (int i = 0; i < moves.size(); i++) {
            System.out.println("while i = " + i + "moves = " + moves.get(i));
            Timeline timeline = new Timeline(
                    moves.get(i) == "1" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn1);

            }) : moves.get(i) == "2" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn2);
            }) : moves.get(i) == "3" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn3);
            }) : moves.get(i) == "4" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn4);
            }) : moves.get(i) == "5" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn5);
            }) : moves.get(i) == "6" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn6);
            }) : moves.get(i) == "7" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn7);
            }) : moves.get(i) == "8" ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn8);
            }) : new KeyFrame(Duration.seconds(i + 0.1), (event) -> {
                RecordClick(gameBoardBtn9);
            }));

            timeline.setCycleCount(0);

            timeline.play();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.appClient = AppClient.getInstance("localhost", 3333);
            this.client = appClient.getClient();
            gameID = client.createGame("player1", "computer");
            System.out.println("the game id  =" + gameID);

        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        myCustomFont = Font.loadFont(getClass().getResourceAsStream("/fonts/gumbo.otf"), 18);
        Set<Node> allNodes = borderPane.lookupAll("*");
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
//        moves.add(0, "1");
//        moves.add(1, "4");
//        moves.add(2, "2");
//        moves.add(3, "5");
//        moves.add(4, "3");

        if (isRecorded) {
            Platform.runLater(() -> test());
        }

    }

    @FXML
    private void onClickRecord(ActionEvent event) {

        if (isRecording) {
            recordButton.setText("Record");
            recordButton.setStyle("-fx-background-color:#00D1FF;");
            isRecording = false;
        } else {
            recordButton.setText("pause");
            recordButton.setStyle("-fx-background-color:red;");
            isRecording = true;
        }
        System.out.println(isRecording);

    }

}
