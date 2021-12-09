package files;

import java.io.File;
import java.util.Scanner;

public class Exercise5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Ejercicio 5:
		System.out.println("Dinos que fichero quieres modificar: \n");
		
		File fileToEdit = new File(scanner.nextLine());

		if (fileToEdit.exists()) {
			System.out.println("Qué nombre le quieres poner ahora?");
			File fileEdited = new File(scanner.nextLine());
			fileToEdit.renameTo(fileEdited);
			System.out.println("Fichero actualizado con éxito");
		} else {
			System.out.println("El fichero no existe.");
		}
		
		scanner.close();
	}

}
