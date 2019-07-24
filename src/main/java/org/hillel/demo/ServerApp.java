package org.hillel.demo;

import java.io.IOException;
import java.net.BindException;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        try {
            IServer server = new UDPServer(ApplicationConstants.APPLICATION_DEMO_PORT);
            server.doWhileNotExit();
        } catch (BindException bindException) {
            System.out.println("Address or port port is already in use.");
        } catch (IOException exception) {
            System.out.println("Server application error.");
            exception.printStackTrace();
        }
    }
}
