package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import manager.MainManager;
import server.TcpSocketServer;

public class TcpSocketClient {

    public static void main(String[] args) throws Exception {
        MainManager.welcomeMessage(MainManager.CLIENT_SENDER);

        connect("localhost", TcpSocketServer.PORT);
    }

    public static void connect(String address, int port) {
        Socket socket;

        try {
            // Creating new socket to contact with the server at its port.
            socket = new Socket(InetAddress.getByName(address), port);

            // Main program occurs form here...
            MainManager.startAndWaitThreads(socket);

            // If the socket is not closed, we need to close it.
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
