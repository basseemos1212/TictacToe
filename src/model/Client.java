/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    Player player=new Player();
    static String loginState;
// Read the Player object from the ObjectInputStream.
    
    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputObjectStream= new ObjectInputStream(socket.getInputStream());
    }

    public boolean signUp(String username, String password) throws IOException {
        outputStream.writeUTF("signup");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }
    
    
    public Player signIn(String username, String password) throws IOException, ClassNotFoundException {
        outputStream.writeUTF("signin");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        player=(Player) inputObjectStream.readObject();

        return player;
    
    }

    }



