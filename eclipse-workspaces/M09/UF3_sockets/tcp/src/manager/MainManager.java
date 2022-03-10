package manager;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import server.TcpSocketServer;
import threads.InThread;
import threads.OutThread;

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

    /**
     * Thsi method is the thread's starter and waiter.
     * 
     * @param socket Socket to send or receive data.
     */
    public static void startAndWaitThreads(Socket socket) {
        try {
            InThread inThread = new InThread("Sender", socket);
            OutThread outThread = new OutThread("Receiver", socket);

            inThread.start();
            outThread.start();

            inThread.join();
            outThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String writeMessage() {
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

    public static boolean isFarewellMessage(String message) {
        return ENDING_TEXT.equals(message);
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
}
