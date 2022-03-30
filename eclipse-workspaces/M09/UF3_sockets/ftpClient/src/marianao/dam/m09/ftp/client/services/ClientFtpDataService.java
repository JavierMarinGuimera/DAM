/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marianao.dam.m09.ftp.client.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import marianao.dam.m09.ftp.client.FtpSynchronizer;

/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class ClientFtpDataService implements Runnable {
    FtpSynchronizer notifier;
    IOException exception = null;
    InputStream in = null;
    OutputStream out = null;
    Socket socket;
    boolean closeInput = false;
    boolean closeOutput = false;

    public void init(FtpSynchronizer not, Socket pSocket,
            OutputStream pOut,
            boolean pCloseOutput) throws IOException {
        init(not, pSocket, pOut);
        closeOutput = pCloseOutput;
    }

    public void init(FtpSynchronizer not, Socket pSocket, OutputStream pOut)
            throws IOException {
        socket = pSocket;
        in = socket.getInputStream();
        out = pOut;
        notifier = not;
    }

    public void init(FtpSynchronizer not, Socket pSocket,
            InputStream pIn,
            boolean pCloseInput) throws IOException {
        init(not, pSocket, pIn);
        closeInput = pCloseInput;
    }

    public void init(FtpSynchronizer not, Socket pSocket, InputStream pIn)
            throws IOException {
        socket = pSocket;
        in = pIn;
        out = socket.getOutputStream();
        notifier = not;
    }

    public boolean hasError() {
        return exception != null;
    }

    public IOException getError() {
        return exception;
    }

    public void close() {
        try {
            if (closeInput && in != null) {
                in.close();
            }
            if (closeOutput && out != null) {
                out.close();
            }
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
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            exception = ex;
        }
    }

    @Override
    public void run() {
        try {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            exception = ex;
        } finally {
            close();
            notifier.enableSynchronizer();
        }
    }

}
