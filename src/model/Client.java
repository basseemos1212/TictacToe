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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ChoosePlayerController;

/**
 *
 * @author moham
 */
public class Client {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    ObjectInputStream inputObjectStream;
    public Player player;
    public boolean isInvited = false;
    private BlockingQueue<String> messageQueue;
    public BooleanProperty myBooleanProperty = new SimpleBooleanProperty(false);
    public BooleanProperty acceptBooleanProperty = new SimpleBooleanProperty(false);

    String senderName = "";

    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputObjectStream = new ObjectInputStream(socket.getInputStream());
        messageQueue = new LinkedBlockingQueue<>();
        //isInvited=getInviteRequest();

        listenForMessages();
    }

    public boolean signUp(String username, String password) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
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
            player = new Player(userr, password);

            //player = (Player) inputObjectStream.readObject();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return player;

    }

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
        outputStream.writeUTF(gson.toJson(jsonObject));

    }

    public void listenForMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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
                                });
                                break;
                            case "checkAcceptance":
                                messageQueue.put(messageFromServer);
                                System.out.println("from checkAccept " + messageFromServer);
//                                
                                goToBoard();
                                break;
                            default:
                                messageQueue.put(messageFromServer);

                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    private String readFromMessageQueue() throws InterruptedException {
        return messageQueue.take();
    }

    public void replyToInviteRequest(String reply) {//send from reciever to server the reply of the invite request.
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("func", "replyToInvite");
            jsonObject.addProperty("senderUsername", senderName);
            jsonObject.addProperty("recievererUsername", this.player.getUsername());
            jsonObject.addProperty("reply", reply);
            Gson gson = new Gson();
            outputStream.writeUTF(gson.toJson(jsonObject));
            myBooleanProperty.set(false);
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
            String reply = responseJsonObject.get("reply").getAsString();

            if (reply.equals("accept")) {
                if (sender.equals(player.getUsername())) {
                    Platform.runLater(() -> {

                        acceptBooleanProperty.set(true);
                    });

                } else if (reciever.equals(player.getUsername())) {
                    Platform.runLater(() -> {

                        acceptBooleanProperty.set(true);
                    });

                }

            }
            else if (reply.equals("reject")) {
                if (sender.equals(player.getUsername())) {
                    Platform.runLater(() -> {

                        acceptBooleanProperty.set(false);
                    });

                } else if (reciever.equals(player.getUsername())) {
                    Platform.runLater(() -> {

                        acceptBooleanProperty.set(false);
                    });

                }

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

//    private void navigate( Stage stage,String url) throws IOException {
//
//        // Load the FXML file for the first screen
//        Parent root;
//        
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
//        root = loader.load();
//       
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
}