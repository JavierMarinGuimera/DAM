package files;

import java.io.File;
import java.io.IOException;

public class Exercise2 {

	public static void main(String[] args) throws IOException {
		// Ejercicio 2:
		File ej2File = new File("./files/datos2.txt");

		if (ej2File.exists()) {
			System.out.println("Ruta relativa: " + ej2File.getPath());
			System.out.println("Ruta absoluta: " + ej2File.getAbsolutePath());
			System.out.println("Ruta canónica: " + ej2File.getCanonicalPath());
			System.out.println("Tamaño del archivo: " + ej2File.length());
		} else {
			File filesDirectory = new File("./files");
			if (filesDirectory.exists()) {
				System.out.println("El directorio existe");
				System.out.println("El archivo no existe, vamos a crearlo");
				ej2File.createNewFile();
			} else {
				System.out.println("El directorio no existe, vamos a crearlo");
				filesDirectory.mkdir();
				System.out.println("El archivo no existe, vamos a crearlo");
				ej2File.createNewFile();
			}
		}

	}

}
