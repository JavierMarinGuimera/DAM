package marianao.dam.m09.ftp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class ClientFtpProtocolService implements Runnable {

    public static final String USER = "USER ";
    public static final String PASS = "PASS ";
    public static final String PASV = "PASV ";
    public static final String LIST = "LIST ";
    public static final String RETR = "RETR ";
    public static final String CDUP = "CDUP ";
    public static final String PORT = "PORT ";
    public static final String CWD = "CWD ";
    public static final String PWD = "PWD ";
    public static final String QUIT = "QUIT ";

    private FtpSynchronizer notifier;
    private Socket controlChannelSocket;
    private Socket dataChannelSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean end = false;
    private PrintWriter systemOutput;
    private boolean passiveError = false;

    // private ClientFtpControlService ftpClient;
    public void init(FtpSynchronizer notifier, OutputStream systemOutput) {
        this.systemOutput = new PrintWriter(systemOutput);
        this.notifier = notifier;
    }

    public void connectTo(String server, int port) throws IOException {
        /*
         * Permetrà configurar la connexió passant el nom i el port del servidor FTP
         * en el qual connectar-se. No caldrà que feu cap tractament d’error dins aquest
         * mètode.
         * Per això la declaració inclou la clàusula throws IOException en cas que
         * l’intent de connexió
         * produeixi un error.
         * Bàsicament, haureu d'initzialitzar els atributs següents:
         * controlChannelSocket
         * in
         * out
         */

        controlChannelSocket = new Socket(InetAddress.getByName(server), port);
        in = new BufferedReader(new InputStreamReader(controlChannelSocket.getInputStream()));
        out = new PrintWriter(controlChannelSocket.getOutputStream());

        (new Thread(this)).start();
    }

    public void authenticate(String user, String pass) {
        /*
         * La implementació d’aquest mètode haurà de generar l’enviament de las comandes
         * USER i PASS
         * amb els seus paràmetres corresponents (ja els teniu fets als mètodes sendUser
         * i sendPass, que
         * podeu fer servir com a exemple per a la resta de mètodes send que haureu
         * d'implementar)
         */
    }

    public String sendPassv() {
        /*
         * TODO: El mètode enviarà la comanda PASV i preparà la sincronització per tal
         * que no es
         * comencin a copiar les dades abans d’haver creat el sòcol específic del canal
         * de dades.
         * Es retornarà la cadena de comades enviada.
         */
        return "TODO";
    }

    public String sendList(OutputStream out, boolean closeOutput)
            throws IOException {
        /*
         * TODO: El mètode enviarà la comanda LIST i esperarà el senyal de
         * sincronització indicant
         * que es pot iniciar la rebuda de dades. Un cop arribi el senyal de
         * sincronització s’instanciarà
         * un objecte de ClientFtpDataService i se’l configurarà per tal que comenci
         * la còpia al canal de sortida que l’usuari pugui visualitzar (flux rebut en el
         * segon paràmetre).
         * El tercer paràmetre indicarà si cal o no tancar el flux de segon paràmetre.
         * Es retornarà la cadena de comades enviada.
         */
        return "TODO";
    }

    public String sendCdup() {
        /*
         * TODO: Aquest mètode executarà la comanda CDUP en el servidor i retornarà la
         * cadena enviada.
         */
        return "TODO";
    }

    public String sendCwd(String down) {
        /*
         * TODO: Aquí caldrà enviar la comanda CWD per canviar al directori especificat
         * en el paràmetre.
         * Es retornarà també la cadena enviada.
         */
        return "TODO";
    }

    public String sendPwd() {
        /*
         * TODO: En aquest cas la comanda enviada serà PWD. De forma similar a sendQuit,
         * retornarà també el valor de la cadena enviada.
         */
        return "TODO";
    }

    public String sendQuit() {
        /*
         * TODO: Similar a close, malgrat que a més d’enviar la comanda QUIT, retorna la
         * cadena
         * enviada per si l’objecte que l’està invocant necessita saber el valor de la
         * comanda enviada
         * (per exemple per imprimir per pantalla).
         */
        return "TODO";
    }

    public String sendRetr(String remote, OutputStream out, boolean closeOutput)
            throws IOException {
        /*
         * TODO: El mètode enviarà la comanda RETR amb el nom del fitxer remot a copiar
         * contingut al primer paràmetre i esperarà el senyal de sincronització indicant
         * que es pot iniciar la rebuda de dades. Un cop arribi el senyal de
         * sincronització
         * s’instanciarà un objecte de ClientFtpDataService i se’l configurarà per tal
         * que comenci
         * la còpia en un canal de sortida adequat per copiar les dades a un fitxer
         * (segon paràmetre). El tercer paràmetre indicarà si cal o no tancar el flux de
         * segon paràmetre.
         * Es retornarà la cadena de comades enviada.
         */
        return "TODO";
    }

    public String sendUser(String user) {
        String ret = USER + user;
        send(ret);
        return ret;
    }

    public String sendPass(String pass) {
        String ret = PASS + pass;
        send(ret);
        return ret;
    }

    public void close() {
        /*
         * TODO:
         * Aquest mètode enviarà la comanda QUIT al servidor per desconnectar-se.
         * Cal assegurar que un cop desconnectat es tancaran tots els sòcols que
         * romanguin oberts.
         */
        if (!controlChannelSocket.isClosed()) {
            /*
             * TODO: Enviar Quit
             */
        }
        /*
         * TODO: Tancar socket de control i de dades
         * close(controlChannelSocket);
         * close(dataChannelSocket);
         */

    }

    private void createDataSocket(byte[] addres, int port) {
        try {
            if (notifier.isEnabled()) {
                notifier.disableSynchronizer();
            }

            InetAddress inetAddressServer = InetAddress.getByAddress(addres);
            dataChannelSocket = new Socket(inetAddressServer, port);
            passiveError = false;

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            passiveError = true;
        }

        notifier.youCanStart();
    }

    private void processResponse(String ret) throws IOException {
        if (ret.startsWith("227")) {
            // init passive mode
            passiveService(ret.substring(ret.indexOf('(') + 1, ret.indexOf(')')));
        }
    }

    private void passiveService(String str) throws IOException {
        byte[] adress = new byte[4];
        String[] strBytes = str.split(",");
        for (int i = 0; i < 4; i++) {
            adress[i] = (byte) Integer.parseInt(strBytes[i]);
        }
        int port = Integer.parseInt(strBytes[4]) * 256;
        port += Integer.parseInt(strBytes[5]);

        createDataSocket(adress, port);
    }

    private void processInputData(OutputStream out, boolean closeOutput)
            throws IOException {
        if (notifier.isDisabled()) {
            notifier.waitingToStart();
        } else {
            // Error cal PASV
            return;
        }

        if (!passiveError) {
            ClientFtpDataService channel = new ClientFtpDataService();

            channel.init(notifier, dataChannelSocket, out, closeOutput);
            (new Thread(channel)).start();
        }
    }

    /**
     * Executa el procés d'escoltar al servidor en un fil diferent.
     */
    @Override
    public void run() {
        try {
            listen();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(controlChannelSocket);
            close(dataChannelSocket);
        }

    }

    /**
     * Escolta el servidor fins que l'usuari indiqui el tancament i el servidor
     * mantingui oberta la connexió.
     */
    private void listen() throws IOException {
        String inputLine = readLine();
        end = inputLine == null;
        while (!end) {
            processResponse(inputLine);
            inputLine = readLine();
            end = inputLine == null;
        }
    }

    private void send(String message) {
        out.println(message);
        out.flush();
    }

    private String readLine() throws IOException {
        String ret = in.readLine();
        if (ret != null) {
            systemOutput.println(ret);
            systemOutput.flush();
        }
        return ret;
    }

    private void close(Socket socket) {
        /*
         * TODO: Tancar els streams corresponets i el socket. Logar cada excepció per
         * separat escrivint la excepció correspoent al log mitjançant la següent
         * comanda:
         * Logger.getLogger(ClientFtpProtocolService.class.getName()).log(Level.SEVERE,
         * null, ex);
         */
        if (socket != null && !socket.isClosed()) {
            if (!socket.isInputShutdown()) {
                /* TODO: Tancar aquí el input stream */
            }
            if (!socket.isOutputShutdown()) {
                /* TODO: Tancar aquí el output stream */

            }
            /* TODO: Tancar aquí el socket */
        }
    }
}
