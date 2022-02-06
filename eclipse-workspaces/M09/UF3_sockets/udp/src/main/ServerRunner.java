/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import udp.Server;

public class ServerRunner {
	public final static int PORT = 9994;

	public static void main(String[] args) {
		try {
			Server prg = new Server();
			prg.init(PORT);
			prg.runServer();
		} catch (IOException ex) {
			Logger.getLogger(ServerRunner.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
