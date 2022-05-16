package app.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Base64;

public class KeyStoreManager {
    private static final String KEY_STORE_TYPE = "JCEKS";
    private static final String KEY_STORE_FILE = "resources/key.jks";
    private static final String KEY_STORE_FILE_SOLVED = "resources/keySolved.jks";

    public static final String KEY_STORE_ALIAS = "fitxers"; // CLAVE PRIVADA Y CERTIFICADO.
    public static final String KEY_STORE_PASSWORD = "password";

    private static KeyStoreManager ksm;
    private static KeyStore ks;

    private KeyStoreManager() {
    };

    public static KeyStoreManager getInstance() {
        if (ksm == null) {
            ksm = new KeyStoreManager();
        }

        return ksm;
    }

    public KeyStore getKeyStore() throws Exception {
        if (ks == null) {
            KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE); // Gets a keystore instance of JCEKS type
            File file = getFileFromBase64(new File(KEY_STORE_FILE)); // File instance related to the file path
            if (file.isFile()) {
                // It is a file
                FileInputStream in = new FileInputStream(file); // Prepare a file input stream to read the file

                /**
                 * TODO - Salta "Invalid keystore format" y no sé por qué :(
                 * 
                 * He mirado en el siguiente link, pero no sé cómo me puede servir lo que
                 * comentan.
                 * Link:
                 * https://stackoverflow.com/questions/1052700/java-io-ioexception-invalid-keystore-format
                 * 
                 * He visto que comentan algo de un filter, pero es utilizando maven y no es el
                 * caso.
                 */
                ks.load(in, KEY_STORE_PASSWORD.toCharArray()); // Loads the keystore object with the data from the file.
                                                               // Uses the password needed to access the data
                in.close();
            }
        }

        return ks; // The keystore object corresponding to the keystore file
    }

    public File getFileFromBase64(File inputFile) {
        try {
            File outputFile = new File(KEY_STORE_FILE_SOLVED);

            // 
            InputStream fis = Base64.getDecoder().wrap(new FileInputStream(inputFile));
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] buffer = new byte[256];
            int readed = -1;

            while ((readed = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, readed);
            }

            fis.close();
            fos.close();

            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
