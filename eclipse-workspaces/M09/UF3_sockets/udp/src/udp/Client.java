package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import lib.ConsoleInterface;

public class Client {
	private static final int BUFFER_SIZE = 1024;
	private InetAddress serverAddress;
	private int serverPort;
	private DatagramSocket socket;
	private ConsoleInterface consoleInterface;

	// Constructor:
	public void init(String host, int port) throws SocketException, UnknownHostException {
		System.out.println("Welcome! Client started.");
		serverAddress = InetAddress.getByName(host);
		serverPort = port;
		socket = new DatagramSocket();
		consoleInterface = new ConsoleInterface();
	}

	// Custom methods:
	public void runClient() throws IOException {
		while (mustContinue()) {
			// Here we read every CLIENT answer.
			Short firstNum = consoleInterface.readShort("First number: ");
			char op = consoleInterface.readOperation("Choose a valid operation (+, -, *, /): ");
			Short secondNum = consoleInterface.readShort("Second number: ");

			Request rq = new Request(op, firstNum, secondNum);

			DatagramPacket packet = new DatagramPacket(rq.getBytes(),
					rq.getBytes().length,
					serverAddress,
					serverPort);
			// And here we send the data to the SERVER.
			socket.send(packet);

			// Now here we get the reponses packet.
			packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			socket.receive(packet);

			System.out.println("Hola" + Arrays.toString(Arrays.copyOfRange(packet.getData(), 0, packet.getLength())));

			// Once we get the packet, we can handle it with our Repsonse class.
			Response rs = new Response(Arrays.copyOfRange(packet.getData(), 0, packet.getLength()));
			processData(rs);
		}

		this.close();
	}

	private void processData(Response rs) {
		switch (rs.getErrorCode()) {
			case Response.ERROR:
				this.consoleInterface.showError("Error inesperado.");
				break;

			case Response.ERROR_DIV_0:
				this.consoleInterface.showError("No puedes dividir entre 0.");
				break;

			case Response.OK:
				this.consoleInterface.showMessage(Long.toString(rs.getResult()));
				break;

			default:
				this.consoleInterface.showError(new Exception("Error en el programa."));
				break;
		}
	}

	private boolean mustContinue() {
		return consoleInterface.readYesNo("Quieres hacer una operación? (S = Sí)", 'S', 2);
	}

	private void close() {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}

	// Getters and Setters:
	public InetAddress getServerAddress() {
		return this.serverAddress;
	}

	public void setServerAddress(InetAddress serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public DatagramSocket getSocket() {
		return this.socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public ConsoleInterface getConsoleInterface() {
		return this.consoleInterface;
	}

	public void setConsoleInterface(ConsoleInterface consoleInterface) {
		this.consoleInterface = consoleInterface;
	}

}