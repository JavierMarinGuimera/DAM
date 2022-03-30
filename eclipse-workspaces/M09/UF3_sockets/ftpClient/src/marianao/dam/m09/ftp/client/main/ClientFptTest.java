package marianao.dam.m09.ftp.client.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import marianao.dam.m09.ftp.client.FtpSynchronizer;
import marianao.dam.m09.ftp.client.services.ClientFtpProtocolService;

/**
 * 
 * @author Toni Moreno <amoren86@xtec.cat>
 *
 */
public class ClientFptTest {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		FtpSynchronizer not = new FtpSynchronizer();
		ClientFtpProtocolService protocol = new ClientFtpProtocolService();
		try {

			protocol.init(not, System.out);
			protocol.connectTo("ftp.rediris.es", 21);

			protocol.authenticate("anonymous", "guest");
			System.out.println(protocol.sendCwd("/"));
			System.out.println(protocol.sendPassv());
			System.out.println(protocol.sendList(System.out, false));
			System.out.println(protocol.sendPwd());
			System.out.println(protocol.sendCwd("mirror"));
			System.out.println(protocol.sendCwd("GNU"));
			System.out.println(protocol.sendPwd());
			System.out.println(protocol.sendPassv());
			System.out.println(protocol.sendList(System.out, false));
			System.out.println(protocol.sendCwd("bash"));
			System.out.println(protocol.sendPwd());
			System.out.println(protocol.sendPassv());
			System.out.println(protocol.sendList(System.out, false));
			System.out.println(protocol.sendCdup());
			System.out.println(protocol.sendPwd());
			System.out.println(protocol.sendPassv());
			System.out.println(
					protocol.sendRetr("README.DESCRIPTIONS", new FileOutputStream("README.DESCRIPTIONS"), true));
			not.waitingToEnabled();
			System.out.println(protocol.sendQuit());
		} catch (IOException ex) {
			protocol.close();
			Logger.getLogger(ClientFptTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}