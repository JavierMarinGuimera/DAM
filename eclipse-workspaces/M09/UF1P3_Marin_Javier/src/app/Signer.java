package app;

import static app.managers.MessageManager.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;

import app.managers.KeyStoreManager;
import app.managers.SignManager;

/**
 * @author Javier Marín Guimerà.
 */
public class Signer {
    private static KeyStoreManager ksm;

    public static void main(String[] args) {
        showMessage(WELCOME_MESSAGE);
        mainTasks();
    }

    private static void mainTasks() {
        ksm = KeyStoreManager.getInstance();

        String path;
        File file;

        while (true) {
            if ((path = askForX(ASK_PATH_MESSAGE)).toLowerCase().equals("exit")) {
                showMessage(FAREWELL_MESSAGE);
                return;
            }

            file = new File(path);

            if (file != null && file.exists() && file.isDirectory()) {
                File[] files = file.listFiles();

                for (File childFile : files) {
                    signFile(childFile);
                }
            } else {
                showMessage(NOT_DIRECTORY_MESSAGE);
            }
        }
    }

    private static void signFile(File file) {
        try {
            KeyStore ks = ksm.getKeyStore();

            PrivateKey pk = (PrivateKey) ks.getKey(KeyStoreManager.KEY_STORE_ALIAS,
                    KeyStoreManager.KEY_STORE_PASSWORD.toCharArray());

            System.out.println(file.getAbsolutePath() + " is being signed.");
            System.out.println("Result sign: " + getStringFromBase64(new String(SignManager.signData(file, pk))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStringFromBase64(String str) {
        return Base64.getDecoder().decode(str).toString();
    }
}
