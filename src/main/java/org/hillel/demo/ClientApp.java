package org.hillel.demo;

import java.io.IOException;
import java.net.ConnectException;

public class ClientApp {
    public static void main(String[] args) {
        try {
            IClient IClient = new Client("127.0.0.1", ApplicationConstants.APPLICATION_DEMO_PORT);
            IClient.doWhileNotExit();
        } catch (ConnectException connectException) {
            System.out.println("There is no server on such host/port.");
        } catch (IOException ioException) {
            System.out.println("Client start failed.");
            ioException.printStackTrace();
        }
    }
}
