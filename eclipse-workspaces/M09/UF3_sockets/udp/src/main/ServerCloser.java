/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import udp.Request;

public class ServerCloser {
	public static final int STOP = Request.STOP;
	private String address;
	private int port;

	public ServerCloser(String adress, int port) {
		this.address = adress;
		this.port = port;
	}

	public static void main(String[] args) {
		try {
			ServerCloser cli = new ServerCloser("localhost", ServerRunner.PORT);
			cli.closeServer();
		} catch (IOException ex) {
			Logger.getLogger(ServerCloser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void closeServer() throws IOException {
		DatagramSocket s = new DatagramSocket();

		InetAddress addr = InetAddress.getByName(address);
		ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream(Integer.SIZE);
		DataOutputStream output = new DataOutputStream(arrayOutput);

		output.writeInt(STOP);
		byte[] sortida = arrayOutput.toByteArray();
		DatagramPacket paquetSortida = new DatagramPacket(sortida, sortida.length, addr, port);
		s.send(paquetSortida);
		s.close();
	}
}
