/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author moham
 */
public class Client {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    
    public Client(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public boolean signUp(String username, String password) throws IOException {
        outputStream.writeUTF("signup");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }
    
    
    public boolean signIn(String username, String password) throws IOException {
        outputStream.writeUTF("signin");
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }


    // other methods for sign-up, send messages, etc.
}
