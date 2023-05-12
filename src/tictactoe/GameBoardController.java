package tictactoe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AppClient;
import model.Client;

/**
 *
 * @author Bassem
 */
public class GameBoardController implements Initializable {

    private Image xImage = new Image("/assets/x_1.png");

    private Image oImage = new Image("/assets/o_1.png");

    int xoCounter = 0;
    int number = 0;
    private boolean endOFGame = false;
    public static boolean playRecord = false;
    private boolean isRecoarding = false;
    public static String fileRecorded;
    public static String player1 = "Player1";
    public static String player2 = "PC";
    public static boolean isVersusPC = false;
    List<Integer> excludedNumbers = new ArrayList<>(Arrays.asList());
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
    @FXML
    private BorderPane borderPane;
    private Font myCustomFont;
    private Vector<String> moves = new Vector<>();
    Button[] buttons = {gameBoardBtn1, gameBoardBtn2, gameBoardBtn3, gameBoardBtn4, gameBoardBtn5, gameBoardBtn6, gameBoardBtn7, gameBoardBtn8, gameBoardBtn9};
    private int gameID;
    private int order = 0;
    private AppClient appClient;
    private Client client;
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm a");
    String formattedDate = formatter.format(now);
    @FXML
    private Button recordButton;
    @FXML
    private Button backBtn;

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setPlayRecord(boolean playRecord) {
        this.playRecord = playRecord;
    }

    public String getFileRecorded() {
        return fileRecorded;
    }

    public void setFileRecorded(String fileRecorded) {
        this.fileRecorded = fileRecorded;
    }

    @FXML
    public void onClickListner(ActionEvent event) {

        Button onClick = (Button) event.getSource();
        System.out.println("test= " + number++);
        String move = String.valueOf(onClick.idProperty().get().charAt(12));

        if (onClick.getText().equals("") && endOFGame == false) {
            if (xoCounter == 0) {
                onClick.setText("x");

                ImageView xImageView = new ImageView(xImage);
                onClick.setGraphic(xImageView);
                moves.add(move);
                excludedNumbers.add(Integer.valueOf(String.valueOf(onClick.idProperty().get().charAt(12))));
                xoCounter = 1;
                if (isVersusPC && number < 8) {
                    pcPlay(generateRandomNumber(excludedNumbers));

                }
            } else if(!isVersusPC) {
                onClick.setText("o");

                ImageView oImageView = new ImageView(oImage);
                onClick.setGraphic(oImageView);
                xoCounter = 0;
                moves.add(move);
            }

        }
        if (!endOFGame) {
            calculateResult();
        }
        if (number == 9 && !endOFGame) {
            System.out.println("THIS IS DRAW");
            playVideo(3);

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
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            } else if (res1.equals(res4) && res1.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn7);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            }
        } else if (res1.equals("o")) {
            if (res1.equals(res2) && res1.equals(res3)) {
                System.out.println("O win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn3);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);

            } else if (res1.equals(res4) && res1.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn7);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);

            }
        }

        if (res5.equals("x")) {
            if (res5.equals(res4) && res5.equals(res6)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn6);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            } else if (res5.equals(res2) && res5.equals(res8)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn8);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            } else if (res5.equals(res1) && res5.equals(res9)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn9);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            } else if (res5.equals(res3) && res5.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn3);
                flashButton(gameBoardBtn7);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);
            }
        } else if (res5.equals("o")) {
            if (res5.equals(res4) && res5.equals(res6)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn4);
                flashButton(gameBoardBtn6);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);
            } else if (res5.equals(res2) && res5.equals(res8)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn2);
                flashButton(gameBoardBtn8);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);
            } else if (res5.equals(res1) && res5.equals(res9)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn1);
                flashButton(gameBoardBtn9);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);
            } else if (res5.equals(res3) && res5.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn5);
                flashButton(gameBoardBtn3);
                flashButton(gameBoardBtn7);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }

                endOFGame = true;
                playVideo(2);

            }
        }

        if (res9.equals("x")) {
            if (res9.equals(res6) && res9.equals(res3)) {
                System.out.println("X win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn6);
                flashButton(gameBoardBtn3);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }

                endOFGame = true;
                playVideo(1);
            } else if (res9.equals(res8) && res9.equals(res7)) {
                System.out.println("X win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn8);
                flashButton(gameBoardBtn7);
                moves.add(player1);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(1);

            }
        } else if (res9.equals("o")) {
            if (res9.equals(res6) && res9.equals(res3)) {
                System.out.println("O win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn6);
                flashButton(gameBoardBtn3);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }
                endOFGame = true;
                playVideo(2);
            } else if (res9.equals(res8) && res9.equals(res7)) {
                System.out.println("O win");
                flashButton(gameBoardBtn9);
                flashButton(gameBoardBtn8);
                flashButton(gameBoardBtn7);
                moves.add(player2);
                if (isRecoarding) {
                    recordGame();
                }

                endOFGame = true;
                playVideo(2);
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

    public void RecordClick(Button bt) {
        System.out.println("test= " + number++);
        String move = String.valueOf(bt.idProperty().get().charAt(12));

        if (bt.getText().equals("") && endOFGame == false) {
            if (xoCounter == 0) {
                bt.setText("x");
                System.out.println(bt.idProperty().get().charAt(12));

                ImageView xImageView = new ImageView(xImage);
                bt.setGraphic(xImageView);
                moves.add(move);

                xoCounter = 1;

            } else {
                bt.setText("o");
                ImageView oImageView = new ImageView(oImage);
                excludedNumbers.add(Integer.valueOf(String.valueOf(bt.idProperty().get().charAt(12))));
                moves.add(move);
                bt.setGraphic(oImageView);
                xoCounter = 0;

            }
            calculateResult();

        }
//        if (!endOFGame) {
//
//            calculateResult();
//
//        } else {
//
//        }

    }

    public void test() {

        for (int i = 3; i < moves.size() - 1; i++) {
            System.out.println(Integer.valueOf(moves.get(i)) == 1);
            System.out.println("while i = " + i + "moves = " + moves.get(i));
            Timeline timeline = new Timeline(
                    Integer.valueOf(moves.get(i)) == 1 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn1);

            }) : Integer.valueOf(moves.get(i)) == 2 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn2);
            }) : Integer.valueOf(moves.get(i)) == 3 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn3);
            }) : Integer.valueOf(moves.get(i)) == 4 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn4);
            }) : Integer.valueOf(moves.get(i)) == 5 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn5);
            }) : Integer.valueOf(moves.get(i)) == 6 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn6);
            }) : Integer.valueOf(moves.get(i)) == 7 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn7);
            }) : Integer.valueOf(moves.get(i)) == 8 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn8);
            }) : Integer.valueOf(moves.get(i)) == 9 ? new KeyFrame(Duration.seconds(i + 0.1), (event) -> {

                RecordClick(gameBoardBtn9);
            }) : new KeyFrame(Duration.seconds(i + 0.1), (event) -> {
                System.out.println("nothing");
            }));

            timeline.setCycleCount(0);

            timeline.play();

        }

    }

    private void recordGame() {
        Random random = new Random();

// Define the characters that can be used in the random string
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

// Generate a random string of 6 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

// Print the random string
        String randomString = sb.toString() + ".json";
        for (String move : moves) {
            System.out.println(move);
        }

        File folder = new File("src/recordedGames/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
//        File file = new File(folder, fileName);
        JsonArray movesJson = new JsonArray();
        for (String move : moves) {
            movesJson.add(move);
        }

// Write the JSON array to a file
        try (FileWriter file = new FileWriter("src/recordedGames/" + randomString)) {
            file.write(movesJson.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("File written: " + file.getAbsolutePath());
    }

    private void playRecordedGame() {

        String fileName = fileRecorded;

        File file = new File("src/recordedGames/" + fileRecorded);

// Read the JSON file using a BufferedReader
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

// Parse the JSON string using Gson
        Gson gson = new Gson();
        String[] jsonArray = gson.fromJson(sb.toString(), String[].class);

// Create a new Vector object and add the elements from the JSON array
        for (String move : jsonArray) {
            moves.add(move);
        }

// Print the Vector object
        for (String move : jsonArray) {
            System.out.println(move);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientUtility.changeFontInAllNodes(borderPane);

        System.out.println("moves=" + moves);
        System.out.println("fileName" + fileRecorded);
        System.out.println("the boolean =" + playRecord);
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
        if (playRecord) {
            System.out.println("iam playing the record game with ");

            System.out.println("filename=" + fileRecorded);
//     playRecordedGame();
        }
        Platform.runLater(() -> {
            if (!playRecord) {
                moves.add(formattedDate);
                moves.add(player1);
                moves.add(player2);

            }

            if (playRecord) {
                playRecordedGame();
                System.out.println("iam setting data to labels");
                this.playerOneName.setText(moves.get(1));
                this.playerOneName2.setText(moves.get(2));
                test();
            } else {
                this.playerOneName.setText(player1);
                this.playerOneName2.setText(player2);
            }
        });

    }

    @FXML
    private void onClickRecord(ActionEvent event) {
        if (!isRecoarding) {
            recordButton.setStyle("-fx-background-color:red; ");
            recordButton.setText("Pause");
            isRecoarding = !isRecoarding;

        } else {
            recordButton.setStyle("-fx-background: linear-gradient(to right, #59DFFF, #788CFE);");
            recordButton.setText("Record");
            isRecoarding = !isRecoarding;

        }

    }

    private int generateRandomNumber(List<Integer> excludedNumbers) {
        Random rand = new Random();
        int randomNumber;
        do {
            randomNumber = rand.nextInt(9) + 1;
        } while (excludedNumbers.contains(randomNumber));
        return randomNumber;
    }

    private void pcPlay(int number) {
        Timeline timeline = new Timeline(
                number == 1 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                            RecordClick(gameBoardBtn1);

                        }) : number == 2 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                    RecordClick(gameBoardBtn2);
                                }) : number == 3 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                            RecordClick(gameBoardBtn3);
                                        }) : number == 4 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                    RecordClick(gameBoardBtn4);
                                                }) : number == 5 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                            RecordClick(gameBoardBtn5);
                                                        }) : number == 6 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                                    RecordClick(gameBoardBtn6);
                                                                }) : number == 7 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                                            RecordClick(gameBoardBtn7);
                                                                        }) : number == 8 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                                                    RecordClick(gameBoardBtn8);
                                                                                }) : number == 9 ? new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {

                                                                                            RecordClick(gameBoardBtn9);
                                                                                        }) : new KeyFrame(Duration.seconds(1 + 0.1), (event) -> {
                                                                                            System.out.println("nothing");
                                                                                        }));

        timeline.setCycleCount(0);

        timeline.play();
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            ClientUtility.navigate(event, "HomeScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(RecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Plays a video depending on the state of the game End.
     *
     * @param state 1= player one won, 2 = player 2 won , 3 = draw
     */
    private void playVideo(int state) {
        String lastPlayer = moves.lastElement();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OfflineVideo.fxml"));

            AnchorPane root = loader.load();
            OfflineVideoController controller = loader.getController();
            controller.setGameBoardController(this);
            switch (state) {
                case 1:
                    //player 1 wins
                    if (player2.equals("PC")) {
                        controller.showVideo("you");
                    } else {
                        controller.showVideo(player1);
                    }
                    break;
                case 2:
                    //player 2 wins
                    if (player2.equals("PC")) {
                        controller.showVideo("PC");
                    } else {
                        controller.showVideo(player2);
                    }
                    break;
                default:
                    //Draw
                    controller.showVideo("draw");
                    break;
            }

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            //stage.setTitle("My Dialog");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeBoard() {
        try {
            Parent root;
            Stage stage;

            FXMLLoader loader = new FXMLLoader(ClientUtility.class.getResource("HomeScreen.fxml"));
            root = loader.load();
            stage = (Stage) borderPane.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void playAgain() {
        try {
            Parent root;
            Stage stage;

            FXMLLoader loader = new FXMLLoader(ClientUtility.class.getResource("GameBoard.fxml"));
            root = loader.load();
            stage = (Stage) borderPane.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
