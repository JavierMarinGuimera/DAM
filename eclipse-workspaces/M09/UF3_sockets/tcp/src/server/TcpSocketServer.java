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
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

            clientSocket = serverSocket.accept();

            MainManager.startAndWaitThreads(clientSocket);

            // String clientMessage;
            // BufferedReader in = null;
            // PrintStream out = null;
            // try {
            // in = new BufferedReader(new
            // InputStreamReader(clientSocket.getInputStream()));
            // out = new PrintStream(clientSocket.getOutputStream());

            // do {
            // String dataToSend = MainManager.writeMessage();
            // out.println(dataToSend);
            // out.flush();

            // clientMessage = in.readLine();
            // MainManager.isFarewellMessage(clientMessage);

            // MainManager.printMessage(MainManager.CLIENT_SENDER, clientMessage);
            // } while (!MainManager.end);
            // } catch (IOException ex) {
            // Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE,
            // null, ex);
            // }

            // MainManager.closeSocket(clientSocket);

            // We need to close the main socket.
            if (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Closing socket");
                serverSocket.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}