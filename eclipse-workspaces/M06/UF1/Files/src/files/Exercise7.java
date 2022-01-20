package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Exercise7 {

	public static void main(String[] args) {
		// Ejercicio 7:

		File file = new File("files/ej7.txt");
		
		writeMessage(file);
		readMessage(file);
		
	}

	private static void writeMessage(File file) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Vamos a escribir en un fichero.");
		System.out.println("Introduce el texto que quieras introducir en el archivo " + file.getName() + ":");
		String texto = scanner.nextLine();
		
		FileWriter escritor = null;
		
		try {
			escritor = new FileWriter(file);
			escritor.write(texto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (escritor != null) {
				try {
					escritor.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		scanner.close();
	}
	
	private static void readMessage(File file) {
		System.out.println("\nVamos a leer el fichero " + file.getName() + " que contiene lo siguiente;");
		
		BufferedReader lector = null;
		
		try {
			lector = new BufferedReader(new FileReader(file));
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lector != null) {
				try {
					lector.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}
