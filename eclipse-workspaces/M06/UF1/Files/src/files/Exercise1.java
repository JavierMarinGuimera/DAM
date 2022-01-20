package files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		// Ejercicio 1:
//		File ej1File = new File("C:/Users/Javier/eclipse/M06/Files/files/datos.txt");
		File ej1File = new File("./files/datos.txt");

		if (ej1File.exists()) {
			System.out.println("Ruta relativa: " + ej1File.getPath());
			System.out.println("Ruta absoluta: " + ej1File.getAbsolutePath());
			System.out.println("Ruta canónica: " + ej1File.getCanonicalPath());
			System.out.println("Tamaño del archivo: " + ej1File.length());
		} else {
			System.out.println("El archivo no existe, lo acabamos de crear.");
			ej1File.createNewFile();
		}
		
		scanner.close();

	}

}
