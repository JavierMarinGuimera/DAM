package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import manager.MainManager;

public class TcpSocketServer {
    public static final int PORT = 9090;

    public static void main(String[] args) throws Exception {
        MainManager.welcomeMessage(MainManager.SERVER_SENDER);
        listen();
    }

    public static void listen() {
        try {
            // Creating our server socket who will receive the client request.
            ServerSocket serverSocket = new ServerSocket(PORT);

            // Receiving the client request.
            Socket clientSocket = serverSocket.accept();

            // Main program occurs form here...
            MainManager.startAndWaitThreads(clientSocket);

            // If the client socket is not closed, we need to close it.
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }

            // If the server socket is not closed, we need to close it.
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}