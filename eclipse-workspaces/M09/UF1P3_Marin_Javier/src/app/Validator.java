package app;

import static app.managers.MessageManager.*;
import app.managers.KeyStoreManager;
import app.managers.SignManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PublicKey;

/**
 * @author Javier Marín Guimerà
 */
public class Validator {
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
            }

            file = new File(path);

            if (file != null && file.exists() && !file.isDirectory()) {
                validateFile(file);
            } else {
                showMessage(NOT_DIRECTORY_MESSAGE);
            }
        }
    }

    private static void validateFile(File file) {
        try {
            String sign = askForX(ASK_SIGN_MESSAGE);
            KeyStore ks = ksm.getKeyStore();

            PublicKey pk = (PublicKey) ks.getCertificate(KeyStoreManager.KEY_STORE_ALIAS);

            System.out.println(file.getAbsolutePath() + " is being signed.");
            System.out.println("Result sign: " + SignManager.validateSignature(file, sign.getBytes(), pk));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
