package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private static final int BUFFER_SIZE = 1024;
	private boolean finish;
	private DatagramSocket socket;

	/**
	 * Initialise attributes: Open a host socket and set initial finish condition to
	 * false
	 * 
	 * @param port port to use
	 * @throws SocketException
	 */
	public void init(int port) throws SocketException {
		socket = new DatagramSocket(port);
		finish = false;
		System.out.println("Server started at port " + port);
	}

	/**
	 * Attends client requests and give them response
	 * 
	 * @throws IOException
	 */
	public void runServer() throws IOException {
		byte[] bufferIn = new byte[BUFFER_SIZE];
		byte[] bufferInData;
		byte[] bufferOutData;
		InetAddress clientAddress;
		int clientPort;

		while (!finish) {
			// Receive
			DatagramPacket packet = new DatagramPacket(bufferIn, BUFFER_SIZE);

			socket.receive(packet);

			bufferInData = Arrays.copyOfRange(packet.getData(), 0, packet.getLength());
			System.out.println("Data received: " + Arrays.toString(bufferInData));

			// Process
			bufferOutData = processData(bufferInData);

			if (!finish) {
				// Send
				clientAddress = packet.getAddress();
				clientPort = packet.getPort();
				packet = new DatagramPacket(bufferOutData, bufferOutData.length, clientAddress, clientPort);

				socket.send(packet);

				System.out.println(
						"Sent data " + Arrays.toString(bufferOutData) + " to " + clientAddress + ":" + clientPort);
			}
		}
		// Close
		close();
		System.out.println("Server shutted down");
	}

	private byte[] processData(byte[] requestData) {
		try {
			Request request = new Request(requestData);
			Response response;

			byte op = request.getOperation();
			finish = (op == Request.STOP);

			if (finish) {
				return null;
			}

			switch (op) {
			case Request.OP_ADD:
				response = new Response(Response.OK, request.getOp1() + request.getOp2());
				break;
			case Request.OP_SUB:
				response = new Response(Response.OK, request.getOp1() - request.getOp2());
				break;
			case Request.OP_MUL:
				response = new Response(Response.OK, request.getOp1() * request.getOp2());
				break;
			case Request.OP_DIV:
				if (request.getOp2() != 0) {
					response = new Response(Response.OK, request.getOp1() / request.getOp2());
				} else {
					response = new Response(Response.ERROR_DIV_0, 0);
				}
				break;
			default:
				response = new Response(Response.ERROR, 0);
			}
			return response.getBytes();
		} catch (IOException e) {
			processError(e);
			return new byte[] { Response.ERROR };
		}
	}

	private void processError(IOException ex) {
		Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
	}

	private void close() {
		if (socket != null && !socket.isClosed()) {
			socket.close();
			System.out.println("Socket closed");
		}
	}
}
