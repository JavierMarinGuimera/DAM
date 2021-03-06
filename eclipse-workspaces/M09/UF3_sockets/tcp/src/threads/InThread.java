package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import manager.MainManager;

public class InThread extends Thread {

    Socket socket;

    public InThread(String name, Socket socket) {
        this.setName(name);
        this.socket = socket;
    }

    /**
     * This thread will ONLY read from de sender. THIS IS THE RECEIVER!
     */
    @Override
    public void run() {
        try {
            String incomingMessage;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            socket.setSoTimeout(2000);

            while (!MainManager.end && !socket.isClosed()) {
                try {
                    incomingMessage = in.readLine();
                    
                    if (MainManager.isFarewellMessage(incomingMessage)) {
                        MainManager.end = true;
                        MainManager.closeSocket(socket);
                    }
                } catch (Exception e) {
                    if (MainManager.end) {
                        break;
                    } else {
                        continue;
                    }
                }

                MainManager.printMessage(MainManager.CLIENT_SENDER, incomingMessage);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
