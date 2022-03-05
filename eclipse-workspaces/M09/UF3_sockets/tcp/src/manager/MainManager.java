package manager;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import server.TcpSocketServer;

public class MainManager {
    public static final String SERVER_SENDER = "Server";
    public static final String CLIENT_SENDER = "Client";
    public static final String ENDING_TEXT = "!END";

    public static boolean end = false;
    public static Scanner scanner;

    public static void welcomeMessage(String nodeUser) {
        System.out.println("Welcome " + nodeUser + " to the TCP app!"
                + (nodeUser.equals(SERVER_SENDER) ? " Server started at port " + TcpSocketServer.PORT + "."
                        : " Type " + ENDING_TEXT + " anytime to end the program."));
    }

    // TODO - Make thread methods to make full-duplex communication.
    public static void name() {
        
    }

    /**
     * Method to close a socket correctly.
     * 
     * @param socket
     */
    public static void closeSocket(Socket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                if (!socket.isInputShutdown()) {
                    socket.shutdownInput();
                }
                if (!socket.isOutputShutdown()) {
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static void closeEverything(Socket serverSocket, Socket clientSocket) {
        closeSocket(serverSocket);
        closeSocket(clientSocket);
        closeScanner();
    }

    public static String readMessage() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner.nextLine();
    }

    /**
     * Main method to print messages.
     * 
     * @param sender  The instance that is sending the message.
     * @param message Thre message that the instance sent.
     */
    public static void printMessage(String sender, String message) {
        System.out.println(sender + ": " + message);
    }
}
