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
            socket = new Socket(InetAddress.getByName(address), port);

            MainManager.startAndWaitThreads(socket);

            // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // out = new PrintStream(socket.getOutputStream());

            // while (!MainManager.end) {
            // System.out.println(MainManager.end);
            // // Getting the data from the server.
            // serverMessage = in.readLine();
            // MainManager.printMessage(MainManager.SERVER_SENDER, serverMessage);

            // // Typing new message...
            // clientMessage = MainManager.writeMessage();

            // // Send the data to the server.
            // out.println(clientMessage);
            // out.flush();
            // }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
