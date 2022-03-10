package threads;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import manager.MainManager;

public class OutThread extends Thread {

    Socket socket;

    public OutThread(String name, Socket socket) {
        this.setName(name);
        this.socket = socket;
    }

    /**
     * This thread will ONLY write to the receiver. THIS IS THE SENDER!
     */
    @Override
    public void run() {
        try {
            String outgoingMessage;
            PrintStream out = new PrintStream(socket.getOutputStream());

            socket.setSoTimeout(1000);

            while (!MainManager.end && !socket.isClosed()) {
                outgoingMessage = "";
                System.out.print("Yo: ");
                while (!MainManager.end && !socket.isClosed() && !outgoingMessage.equals("")) {
                    try {
                        outgoingMessage = MainManager.writeMessage();
                    } catch (Exception e) {
                        continue;
                    }
                }

                // if (MainManager.isFarewellMessage(outgoingMessage))
                // MainManager.end = true;
                if (!MainManager.end && !outgoingMessage.equals("")) {
                    out.println(outgoingMessage);
                    out.flush();
                }

                if (MainManager.isFarewellMessage(outgoingMessage)) {
                    MainManager.end = true;
                    MainManager.closeSocket(socket);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        MainManager.closeSocket(socket);
    }

}
