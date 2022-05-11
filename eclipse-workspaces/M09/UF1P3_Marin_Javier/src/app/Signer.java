package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Scanner;

import app.managers.KeyStoreManager;

/**
 * @author Javier Marín Guimerà.
 */
public class Signer {
    private static final int WELCOME_MESSAGE = 0;
    private static final int FAREWELL_MESSAGE = 1;
    private static final int NOT_DIRECTORY_MESSAGE = 2;
    private static final int ASK_PATH_MESSAGE = 3;
    private static final int ERROR_PATH_MESSAGE = 4;

    private static Scanner sc;

    public static void main(String[] args) {
        showMessage(WELCOME_MESSAGE);
        mainTasks();
    }

    private static void showMessage(int messageType) {
        switch (messageType) {
            case WELCOME_MESSAGE:
                System.out.println("Welcome to the Signer!");
                break;

            case FAREWELL_MESSAGE:
                System.out.println("Good bye!");
                break;

            case NOT_DIRECTORY_MESSAGE:
                System.out.println("You have choosed a file path!");
                break;

            case ASK_PATH_MESSAGE:
                System.out.println("Enter the directory path to sign. Leave this empty to leave program.");
                break;

            case ERROR_PATH_MESSAGE:
                System.out.println("You need to enter a good path!");
                break;

            default:
                System.err.println("Wrong option to print message!");
                break;
        }
    }

    private static void mainTasks() {
        sc = new Scanner(System.in);

        String path;
        File file;

        while (true) {
            if ((path = askForNewPath()) == "") {
                showMessage(FAREWELL_MESSAGE);
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

    private static String askForNewPath() {
        String path = "";
        while (true) {
            try {
                showMessage(ASK_PATH_MESSAGE);
                path = sc.nextLine();
                Integer.parseInt(path);
                continue;
            } catch (Exception e) {
                showMessage(ERROR_PATH_MESSAGE);
                return path;
            }
        }
    }

    private static void signFile(File file) {
        try {
            KeyStore ks = KeyStoreManager.getKeyStore();

            PrivateKey pk = (PrivateKey) ks.getKey(KeyStoreManager.KEY_STORE_ALIAS,
                    KeyStoreManager.KEY_STORE_PASSWORD.toCharArray());

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[512];

            // fileInputStream.read(buffer);

            // while (fileInputStream.read(buffer)) {

            // }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
