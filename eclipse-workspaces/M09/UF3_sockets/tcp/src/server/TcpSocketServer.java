package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpSocketServer {
    static final int PORT = 9090;
    private boolean end = false;

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

            while (!end) {
                clientSocket = serverSocket.accept();
                // processem la petició del client
                proccesClientRequest(clientSocket);
                // tanquem el sòcol temporal per atendre el client
                closeClient(clientSocket);
            }
            // tanquem el sòcol principal
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void proccesClientRequest(Socket clientSocket) {
        boolean farewellMessage = false;
        String clientMessage = "";
        BufferedReader in = null;
        PrintStream out = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintStream(clientSocket.getOutputStream());
            do {
                // processem el missatge del client i generem la resposta. Si
                // clientMessage és buida generarem el missatge de benvinguda
                String dataToSend = processData(clientMessage);
                out.println(dataToSend);
                out.flush();
                clientMessage = in.readLine();
                farewellMessage = isFarewellMessage(clientMessage);
            } while ((clientMessage) != null && !farewellMessage);
        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private boolean isFarewellMessage(String clientMessage) {
        return false;
    }

    private String processData(String clientMessage) {
        return null;
    }

    private void closeClient(Socket clientSocket) {
        // si falla el tancament no podem fer gaire cosa, només enregistrar
        // el problema
        try {
            // tancament de tots els recursos
            if (clientSocket != null && !clientSocket.isClosed()) {
                if (!clientSocket.isInputShutdown()) {
                    clientSocket.shutdownInput();
                }
                if (!clientSocket.isOutputShutdown()) {
                    clientSocket.shutdownOutput();
                }
                clientSocket.close();
            }
        } catch (IOException ex) {
            // enregistrem l’error amb un objecte Logger
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}