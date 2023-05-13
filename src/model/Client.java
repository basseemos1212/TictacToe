/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ChoosePlayerController;
import tictactoe.GameBoardController;
import tictactoe.OnlineBoardController;

/**
 *
 * @author moham
 */
public class Client {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static Player player;
    public boolean isInvited = false;
    private BlockingQueue<String> messageQueue;
    public BooleanProperty myBooleanProperty = new SimpleBooleanProperty(false);
    public StringProperty acceptStringProperty = new SimpleStringProperty();
    public StringProperty senderNameStringProperty = new SimpleStringProperty();
    public BooleanProperty reciverRespondBooleanProperty = new SimpleBooleanProperty(false);

    public BooleanProperty acceptBooleanProperty = new SimpleBooleanProperty(false);
    String ImagePath;
    String senderName = "";
    boolean listening = false;

    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        messageQueue = new LinkedBlockingQueue<>();
        player = new Player("Guest");
        //isInvited=getInviteRequest();
        listening = true;

        listenForMessages();

    }

    public boolean signUp(String username, String password, String ImagePath) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("ImagePath", ImagePath);

        jsonObject.addProperty("func", "signup");
        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        System.out.println(json);
        outputStream.writeUTF(json);

        try {
            String signupResponseMessage = readFromMessageQueue();
            //wait the response
            JsonObject responseJsonObject = new Gson().fromJson(signupResponseMessage, JsonObject.class);
            boolean result = responseJsonObject.get("result").getAsBoolean();
            System.out.println("RESULT OF SIGNUP ISSS " + result);
            return result;

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cant sign up");
            return false;
        }

//        return inputStream.readBoolean();
    }

    public Player signIn(String username, String password) throws IOException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("func", "signin");
        Gson gson = new Gson();
        outputStream.writeUTF(gson.toJson(jsonObject));

        try {
            String loggedInMessage = readFromMessageQueue();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(loggedInMessage);
            String userr = rootNode.get("username").asText();
            String passe = rootNode.get("password").asText();
            System.out.println("score == " +rootNode.get("score").asText());
//            int score = rootNode.get("score").asInt();

            int status = rootNode.get("status").asInt();
            if (rootNode.get("ImagePath").asText().equals(null)) {
                ImagePath = "/assets/avatar.png";

            } else {
                ImagePath = rootNode.get("ImagePath").asText();
            }

            player.setUsername(userr);
            player.setPassword(password);
            player.setStatus(status);
            player.setImagePath(ImagePath);
            player.setScore(Integer.valueOf(rootNode.get("score").asText()));

//            player = new Player(userr, passe);
            //player = (Player) inputObjectStream.readObject();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return player;

    }
    
//need handeling

    public boolean getInviteRequest() //for the 2nd player
    {
        try {
            String inviteRwquestMessage = readFromMessageQueue();
            System.out.println("from getInviteRequest " + inviteRwquestMessage);

            //wait the response
            JsonObject responseJsonObject = new Gson().fromJson(inviteRwquestMessage, JsonObject.class);
            String sender = responseJsonObject.get("player1").getAsString();
            String reciever = responseJsonObject.get("player2").getAsString();
            System.out.println(sender + " sent a request to " + reciever);
            senderName = sender;
            Platform.runLater(()
                    -> {

                senderNameStringProperty.set(senderName);
            });
            return true;

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    /*boolean inviteSuccess=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
              try {
            String inviteRwquestMessage = readFromMessageQueue();
            System.out.println("from getInviteRequest "+inviteRwquestMessage);
            
            //wait the response
            JsonObject responseJsonObject = new Gson().fromJson(inviteRwquestMessage, JsonObject.class);
            String sender = responseJsonObject.get("player1").getAsString();
            String reciever = responseJsonObject.get("player2").getAsString();
            System.out.println(sender+" sent a request to "+reciever);
            if(reciever.equals(player))
            inviteSuccess= true;
            else
                return false;
            

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }   
            }
        }).start();*/
    public void invite(String player1, String player2) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "invite");
        jsonObject.addProperty("player1", player1);
        jsonObject.addProperty("player2", player2);
        Gson gson = new Gson();
        Platform.runLater(() -> {
            System.out.println("from waitResponse " + "boolean " + reciverRespondBooleanProperty);
            reciverRespondBooleanProperty.set(true);

        });
        outputStream.writeUTF(gson.toJson(jsonObject));

    }
        public void addScore(String player) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "addScore");
        jsonObject.addProperty("player", Client.player.getUsername());
        
        Gson gson = new Gson();
 
        outputStream.writeUTF(gson.toJson(jsonObject));

    }

    public void play(String player1, String player2, String counter, String move, String owner) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "playMove");
        jsonObject.addProperty("player1", player1);
        jsonObject.addProperty("player2", player2);
        jsonObject.addProperty("counter", counter);
        jsonObject.addProperty("move", move);
        jsonObject.addProperty("owner", owner);

        Gson gson = new Gson();
        outputStream.writeUTF(gson.toJson(jsonObject));

    }

    public void putInOutGame(String msg) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", msg);

        Gson gson = new Gson();
        outputStream.writeUTF(gson.toJson(jsonObject));

    }

    public void listenForMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (listening) {
                    try {
                        String messageFromServer = inputStream.readUTF();
                        JsonObject jsonObject = new Gson().fromJson(messageFromServer, JsonObject.class);
                        String header = jsonObject.get("head").getAsString();

                        switch (header) {
                            case "onlineplayersResponse":
                                System.out.println("PLAYERS CHANGED");
                                // get the list value
                                JsonArray jsonArray = jsonObject.getAsJsonArray("playerslist");
                                ArrayList<Player> list = new ArrayList<>();
                                for (JsonElement element : jsonArray) {
                                    Player playerfromjson = new Gson().fromJson(element, Player.class);
                                    list.add(playerfromjson);
                                }
                                Platform.runLater(() -> {
                                    ChoosePlayerController.onlinePlayersList.setAll(list);
                                });

                                break;
                            case "inviteRequest":
                                messageQueue.put(messageFromServer);
                                System.out.println("from inviteRequest " + messageFromServer);
//                                isInvited=getInviteRequest();
                                System.out.println("isInvited response: " + isInvited);
//                                Platform.runLater(() -> {
//                                    Requ.onlinePlayersList.setAll(list);
//                                });
                                Platform.runLater(() -> {

                                    myBooleanProperty.set(getInviteRequest());
                                    senderNameStringProperty.set(header);
                                });
                                break;
                            /*case "waitForRespond":
                                messageQueue.put(messageFromServer);
                                Platform.runLater(() -> {
                                    System.out.println("from waitResponse " + messageFromServer+"boolean "+reciverRespondBooleanProperty);
                                    reciverRespondBooleanProperty.set(true);
                                    
                                    
                                });
                                break;*/
                            case "checkAcceptance":
                                messageQueue.put(messageFromServer);
                                System.out.println("from checkAccept " + messageFromServer);

//                                
                                goToBoard();
                                break;
                            case "play":
                                messageQueue.put(messageFromServer);
                                System.out.println("from play " + messageFromServer);
                                try {
                                    String playMoveMsg = readFromMessageQueue();
                                    System.out.println("from playOnlineMove " + playMoveMsg);
                                    if (OnlineBoardController.xoCounter == 0) {
                                        OnlineBoardController.xoCounter = 1;
                                    } else {
                                        OnlineBoardController.xoCounter = 0;
                                    }

                                    //wait the response
                                    JsonObject responseJsonObject = new Gson().fromJson(playMoveMsg, JsonObject.class);

                                    OnlineBoardController.playXO.set(responseJsonObject.get("move").getAsInt());

                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

                                }

//                                
//                                goToBoard();
                                break;

                            default:
                                messageQueue.put(messageFromServer);

                        }
                    } catch (Exception ex) {
                        System.out.println("Client closed!");

                        
//                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    private String readFromMessageQueue() throws InterruptedException {
        return messageQueue.take();
    }

    public void replyToInviteRequest(String reply) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("func", "replyToInvite");
            jsonObject.addProperty("senderUsername", senderName);
            jsonObject.addProperty("recievererUsername", this.player.getUsername());
            jsonObject.addProperty("reply", reply);
            Gson gson = new Gson();
            /*Platform.runLater(() -> {
                                    System.out.println("from waitResponse " +"boolean "+reciverRespondBooleanProperty);
                                    
                                    
                                    
                                });*/
            outputStream.writeUTF(gson.toJson(jsonObject));
            myBooleanProperty.set(false);
            //reciverRespondBooleanProperty.set(false);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToBoard() {
        try {
            String inviteRwquestMessage = readFromMessageQueue();
            System.out.println("from goToBoard " + inviteRwquestMessage);

            //wait the response
            JsonObject responseJsonObject = new Gson().fromJson(inviteRwquestMessage, JsonObject.class);
            String sender = responseJsonObject.get("sender").getAsString();
            String reciever = responseJsonObject.get("reciever").getAsString();
            OnlineBoardController.player1 = sender;
            OnlineBoardController.player2 = reciever;
            String reply = responseJsonObject.get("reply").getAsString();

            if (reply.equals("accept")) {
                if (sender.equals(player.getUsername())) {

                    Platform.runLater(() -> {

                        acceptStringProperty.set("accept");
                        System.out.println("from waitResponse in gotoBoard " + "boolean " + reciverRespondBooleanProperty);

                        reciverRespondBooleanProperty.set(false);
                    });

                } else if (reciever.equals(player.getUsername())) {
                    Platform.runLater(()
                            -> {

                        acceptStringProperty.set("accept");

                    });

                }
                /*Platform.runLater(() -> {
                                    System.out.println("from waitResponse in gotoBoard " +"boolean "+reciverRespondBooleanProperty);
                                    reciverRespondBooleanProperty.set(false);
                                    
                                    
                                });*/

            } else if (reply.equals("reject")) {
                if (sender.equals(player.getUsername())) {
                    Platform.runLater(() -> {
                        System.out.println("I am sender and I was rejected");
                        acceptStringProperty.set("reject");
                        System.out.println("from waitResponse in gotoBoard " + "boolean " + reciverRespondBooleanProperty);

                        reciverRespondBooleanProperty.set(false);
                    });

                }
                /*else if (reciever.equals(player.getUsername())) {
                    Platform.runLater(() -> {
                        System.out.println("I am reciever and I reject");
                        acceptStringProperty.set("reject");
                    });

                }*/

 /*Platform.runLater(() -> {
                                    System.out.println("from waitResponse in gotoBoard" +"boolean "+reciverRespondBooleanProperty);
                                    reciverRespondBooleanProperty.set(false);
                                    
                                    
                                });*/
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void playOnlineMove() {

    }

    public void closeClient() {

        // Send the message to the server
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("func", "closeclient");
            Gson gson = new Gson();
            outputStream.writeUTF(gson.toJson(jsonObject));

        } catch (IOException e) {
            // Handle the error
            e.printStackTrace();
        }

        try {
            socket.close();
            listening = false;
            Thread.currentThread().interrupt();
            messageQueue.clear();

        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
