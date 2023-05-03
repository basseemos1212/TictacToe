/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author moham
 */
public class Client {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    
    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        this.objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
       
    }

    public boolean signUp(String username, String password) throws IOException {
        outputStream.writeUTF("signup");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }
   public int createGame(String username1, String username2) throws IOException {
        outputStream.writeUTF("game");
        outputStream.writeUTF(username1);
        outputStream.writeUTF(username2);
        return inputStream.readInt();
    }
     public void createMove(int gameID, int pos,int order,String symbol) throws IOException {
         outputStream.writeUTF("move");
         Move move=new Move(gameID, pos, order, symbol);
          
          objectOutputStream.writeObject(move);
         
         
         
  
        
//        inputStream.readBoolean();
        
    }
    
    
    public boolean signIn(String username, String password) throws IOException {
        outputStream.writeUTF("signin");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }


    // other methods for sign-up, send messages, etc.
}
