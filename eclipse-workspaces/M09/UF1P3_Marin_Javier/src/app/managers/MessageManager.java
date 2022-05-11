package app.managers;

import java.util.Scanner;

public class MessageManager {
    /**
     * Messages:
     */
    public static final int WELCOME_MESSAGE = 0;
    public static final int FAREWELL_MESSAGE = 1;
    public static final int DIRECTORY_MESSAGE = 20;
    public static final int NOT_DIRECTORY_MESSAGE = 21;
    public static final int ASK_PATH_MESSAGE = 30;
    public static final int ASK_SIGN_MESSAGE = 31;
    public static final int ERROR_PATH_MESSAGE = 4;

    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    /**
     * No instantiable class.
     */
    private MessageManager() {
    }

    public static void showMessage(int messageType) {
        switch (messageType) {
            case WELCOME_MESSAGE:
                System.out.println("Welcome to the program!");
                break;

            case FAREWELL_MESSAGE:
                System.out.println("Good bye!");
                break;

            case DIRECTORY_MESSAGE:
                System.out.println("You have choosed a directory path!");
                break;
            case NOT_DIRECTORY_MESSAGE:
                System.out.println("You have choosed a file path!");
                break;

            case ASK_PATH_MESSAGE:
                System.out.print("Enter the directory path to sign. (type 'exit' to end the program):");
                break;

            case ASK_SIGN_MESSAGE:
                System.out.print("Enter the sign to check the file:");
                break;

            case ERROR_PATH_MESSAGE:
                System.out.println("You need to enter a good path!");
                break;

            default:
                System.err.println("Wrong option to print message!");
                break;
        }
    }

    public static String askForX(int option) {
        String str = "";
        while (true) {
            try {
                showMessage(option);
                str = sc.nextLine();
                Integer.parseInt(str);
                continue;
            } catch (Exception e) {
                return str;
            }
        }
    }
}
