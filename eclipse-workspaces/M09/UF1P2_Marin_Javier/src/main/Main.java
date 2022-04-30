package main;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static final int WELCOME_MESSAGE = 0;
	public static final int FAREWELL_MESSAGE = 1;

	public static final int FILE_NAME = 0;
	public static final int PASSWORD = 1;
	public static final int EXIT = 2;

	public static void main(String[] args) {
		showMessage(WELCOME_MESSAGE);
		mainProgram();
		showMessage(FAREWELL_MESSAGE);
	}

	private static void showMessage(int messageType) {
		switch (messageType) {
		case WELCOME_MESSAGE:
			System.out.println("Welcome to the encrypter / decrypter! Let's do something!");
			break;

		case FAREWELL_MESSAGE:
			System.out.println("Good bye!");
			break;

		default:
			break;
		}
	}

	private static void mainProgram() {
		Scanner sc = new Scanner(System.in);
		
		// 
		while (true) {
			File file= new File(askForX(sc, FILE_NAME));
			
			if (!file.exists()) {
				System.err.println("The file does not exist!");
			} else {
				String password = askForX(sc, PASSWORD);
				
				MyCipher.encryptOrDecryptFile(MyCipher.passwordKeyGeneration(password, MyCipher.BITS_192), MyCipher.AES_CBC, file);
			}
			
			if (askForX(sc, EXIT).toLowerCase().equals("n")) {
				break;
			}
		}
		
		sc.close();
	}

	private static String askForX(Scanner sc, int dataToAsk) {
		switch (dataToAsk) {
		case FILE_NAME:
			System.out.print("File name or path: ");
			return sc.nextLine();
			
		case PASSWORD:
			System.out.print("Password: ");
			return sc.nextLine();
			
		case EXIT:
			System.out.print("\nDo you want to encrypt or decrypt again?(y/n)");
			return sc.nextLine();

		default:
			System.err.println("Wrong option on 'askForX");
			break;
		}

		return "";
	}
}
