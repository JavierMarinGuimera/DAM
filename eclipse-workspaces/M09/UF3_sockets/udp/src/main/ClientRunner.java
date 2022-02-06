package main;

import java.io.IOException;

import udp.Client;

public class ClientRunner {

	public static void main(String[] args) {
		try {
			Client prg = new Client();
			prg.init("localhost", ServerRunner.PORT);
			prg.runClient();
		} catch (IOException ex) {
			System.out.println("Error rebent o enviant");
		}
	}
}
