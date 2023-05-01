/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author Bassem
 */
public class GameBoardController implements Initializable {

    private Image xImage = new Image("/assets/x.png");

    private Image oImage = new Image("/assets/o.png");

    int xoCounter = 0;
    int test = 0;

    boolean endOFGame = false;
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
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
    private ImageView playerTwoImg;
    @FXML
    private Label playerTwoName;
    @FXML
    private ImageView playerTwoSmbl;

    @FXML
    public void onClick(ActionEvent event) {

        Button onClick = (Button) event.getSource();
        System.out.println("test= " + test++);

        if (onClick.getText().equals("") && endOFGame == false) {
            if (xoCounter == 0) {
                onClick.setText("x");

                ImageView xImageView = new ImageView(xImage);
                onClick.setGraphic(xImageView);
                xoCounter = 1;
            } else {
                onClick.setText("o");

                ImageView oImageView = new ImageView(oImage);
                onClick.setGraphic(oImageView);
                xoCounter = 0;
            }
        }
        if (!endOFGame) {
            calculateResult();
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
                new KeyFrame(Duration.seconds(0.3), evt -> button.setStyle("-fx-background-color: yellow")),
                new KeyFrame(Duration.seconds(0.6), evt -> button.setStyle("-fx-background-color: #3B1787"))
        );
        timeline.setCycleCount(7);

        timeline.play();
        button.setStyle("-fx-background-color: yellow");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
