package app.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Base64;

public class KeyStoreManager {
    private static final String KEY_STORE_FILE = "resources/key.jks";
    private static final String KEY_STORE_FILE_SOLVED = "resources/keySolved.jks";

    public static final String KEY_STORE_TYPE = "JCEKS";
    public static final String KEY_STORE_ALIAS = "fitxers"; // CLAVE PRIVADA Y CERTIFICADO.
    public static final String KEY_STORE_PASSWORD = "password";

    private static KeyStore ks;

    private KeyStoreManager() {
    };

    public static KeyStore getKeyStore() throws Exception {
        if (ks == null) {
            ks = KeyStore.getInstance(KEY_STORE_TYPE); // Gets a keystore instance of JCEKS type
            File f = getFileFromBase64(); // File instance related to the file path
            if (f.isFile()) {
                // It is a file
                FileInputStream in = new FileInputStream(f); // Prepare a file input stream to read the file
                ks.load(in, KEY_STORE_PASSWORD.toCharArray()); // Loads the keystore object with the data from the file.
                                                               // Uses the
                // password needed to access the data
            }
        }

        return ks; // The keystore object corresponding to the keystore file

    }

    private static File getFileFromBase64() {
        byte[] array;
        try {
            File outputFile = new File(KEY_STORE_FILE_SOLVED);
            array = Base64.getDecoder().decode(Files.readAllBytes(Paths.get(KEY_STORE_FILE)));
            FileOutputStream stream = new FileOutputStream(outputFile.getAbsolutePath());
            stream.write(array);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
