package files;

import java.io.File;
import java.util.Scanner;

public class Exercise4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Ejercicio 4:

		System.out.println("\nIntroduce el path del archivo para borrarlo:");

		File fileToDelete = new File(scanner.nextLine());

		if (fileToDelete.delete()) {
			System.out.println("Fichero eliminado correctamente.");
		} else {
			System.out.println("No existe ese archivo.");
		}
		
		scanner.close();

	}

}
