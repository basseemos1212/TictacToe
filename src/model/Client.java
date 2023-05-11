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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tictactoe.ChoosePlayerController;
import tictactoe.Toast;

/**
 *
 * @author moham
 */
public class Client {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    public static Player player;

    BlockingQueue<String> messageQueue;

    public Client(String serverIP, int serverPort) throws IOException {
        
            socket = new Socket(serverIP, serverPort);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            messageQueue = new LinkedBlockingQueue<>();
            player = new Player();

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
            int status = rootNode.get("status").asInt();
            String ImagePath = rootNode.get("ImagePath").asText();

            player.setUsername(userr);
            player.setPassword(password);
            player.setStatus(status);
            player.setImagePath(ImagePath);


//            player = new Player(userr, passe);
            //player = (Player) inputObjectStream.readObject();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return player;

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

}
