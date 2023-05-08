/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author moham
 */
public class Client {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    ObjectInputStream inputObjectStream ;
    public Player player=new Player();
// Read the Player object from the ObjectInputStream.
    
    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputObjectStream= new ObjectInputStream(socket.getInputStream());
    }

    public boolean signUp(String username, String password) throws IOException {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("func", "signup");
        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        System.out.println(json);
        outputStream.writeUTF(json);
        return inputStream.readBoolean();
    }
    
    
    public Player signIn(String username, String password) throws IOException, ClassNotFoundException {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("func", "signin");
            Gson gson = new Gson();
        outputStream.writeUTF(gson.toJson(jsonObject));
    
        player=(Player) inputObjectStream.readObject();
        return player;
    
    }

    }



