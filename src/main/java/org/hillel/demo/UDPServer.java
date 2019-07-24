package org.hillel.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer implements IServer{

    private final int port;

    public UDPServer (int port){this.port=port;}

    public void doWhileNotExit() throws IOException {

        try {
            DatagramSocket socket = new DatagramSocket(8090);
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            System.out.println("Data awaiting...");
            String str = null;
            while (!ApplicationConstants.EXIT_COMMAND.equals(str)) {
                socket.receive(incoming);
                byte[] data = incoming.getData();

                 str = new String(data, 0, incoming.getLength());

                System.out.println("Server received: " + str);

                DatagramPacket toClient = new DatagramPacket(
                        str.getBytes(),
                        str.getBytes().length,
                        incoming.getAddress(),
                        incoming.getPort());
                socket.send(toClient);
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);

        }
    }
}
