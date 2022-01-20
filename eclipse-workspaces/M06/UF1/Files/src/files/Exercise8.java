package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Exercise8 {

	public static void main(String[] args) {
		// Ejercicio 8:

		File file = new File("files/img.webp");
		
		copyFile(file);
		
	}

	private static void copyFile(File file) {
		System.out.println("Vamos a intentar copiar el archivo: " + file.getName());
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Vamos a escribir en un fichero.");
		System.out.println("Introduce el texto que quieras introducir en el archivo " + file.getName() + ":");
		
		FileInputStream lector = null;
		FileOutputStream escritor = null;
		
		try {
			File newFile = new File("files/img2.webp");
			
			lector = new FileInputStream(file);
			escritor = new FileOutputStream(newFile);
			
			while (lector.available() > 0) {
				escritor.write(lector.read());
			}
			
			System.out.println("Se ha completado la copia.");
			
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

}
