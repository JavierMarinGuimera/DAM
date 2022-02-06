package udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import lib.ConsoleInterface;

public class Client {
	private static final int BUFFER_SIZE = 1024;
	private InetAddress serverAddress;
	private int serverPort;
	private DatagramSocket socket;
	private ConsoleInterface consoleInterface;

	public void init(String host, int port) throws SocketException, UnknownHostException {
		// TODO Initialise attributes
	}

	public void runClient() throws IOException {
		// TODO Client implementation
	}

	private void close() {
		// TODO Close socket here
	}
}