package org.hillel.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements IClient {

    private  final String serverHost;

    private final int serverPort;

    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void doWhileNotExit(){

        DatagramSocket sock = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            sock = new DatagramSocket();
            String str = null;
            while (!ApplicationConstants.EXIT_COMMAND.equals(str)) {
                System.out.println("Enter the message: ");
                 str = br.readLine();
                byte[] b = str.getBytes();

                DatagramPacket data = new DatagramPacket(b, b.length, InetAddress.getByName("127.0.0.1"), 8090);
                sock.send(data);

                byte[] incommingData = new byte[65536];
                DatagramPacket reply = new DatagramPacket(incommingData, incommingData.length);
                sock.receive(reply);
                byte[] inData = reply.getData();
                str = new String(inData, 0, reply.getLength());

                System.out.println(
                        "Server: " + reply.getAddress()
                                .getHostAddress() + " Port: " + reply
                                .getPort() + " Receive: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

