/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;

/**
 *
 * @author mohamed
 */
public class AppClient {
    private static AppClient instance = null;
    private Client client;

    private AppClient(String serverIP, int serverPort) throws IOException {
        client = new Client(serverIP, serverPort);
    }

    public static AppClient getInstance(String serverIP, int serverPort) throws IOException {
        if (instance == null) {
            instance = new AppClient(serverIP, serverPort);
        }
        return instance;
    }

    public Client getClient() {
        return client;
    }
    
    
}
