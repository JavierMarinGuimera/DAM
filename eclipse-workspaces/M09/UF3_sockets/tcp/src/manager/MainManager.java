package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.TcpSocketServer;
import threads.InThread;
import threads.OutThread;

public class MainManager {
    public static final String SERVER_SENDER = "Server";
    public static final String CLIENT_SENDER = "Client";
    public static final String ENDING_TEXT = "!END";

    public static boolean end = false;
    public static BufferedReader bf;

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

    public static String writeMessage() throws IOException {
        if (bf == null) {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        if (bf.ready()) {
            return bf.readLine();
        }

        return "";
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
}
