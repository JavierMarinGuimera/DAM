package files;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Exercise6 {

	public static void main(String[] args) throws IOException, ParseException {
		Scanner scanner = new Scanner(System.in);
		// Ejercicio 6:

		int option;

		while (true) {
			try {
				System.out.println("Selecciona una opción:");
				System.out.println("0: Salir del programa.");
				System.out.println("1: Buscar en el directorio files.");
				System.out.println("2: Introducir directorio.");
				option = Integer.parseInt(scanner.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Debes introducir un número.");
			}
		}

		switch (option) {
		case 1:

			File thisDirectory = new File(".");
			String[] allFiles = thisDirectory.list();

			Exercise3.printResults(thisDirectory, allFiles);
			break;

		case 2:
			String customPath = "";

			while (true) {
				try {
					System.out.println("Introduce el path donde desees ver su contenido:");
					customPath = scanner.nextLine();
					
					System.out.println("Quieres filtrar por fecha?\n1: Si.\n2: No");
					option = Integer.parseInt(scanner.nextLine());
					break;
				} catch (Exception e) {
					System.out.println("Ha ocurrido algo");
				}
			}
			
			File thisCustomDirectory = new File(customPath);
			
			if (option == 1) {
				System.out.println("Introduce una fecha con formato dd/mm/yyyy");
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
				
				File[] filteredFiles = thisCustomDirectory.listFiles(new MyFilter(date));
				thisCustomDirectory.list();
				if (filteredFiles.length == 0) {
					System.out.println("No hay archivos posteriores a la fecha introducida.");
				} else {
					for (File file : filteredFiles) {
						System.out.println((file.isDirectory() ? "Directorio: ": "Archivo: ") + file.getName());
					}
				}
			} else {
				String[] allFilesCustom = thisCustomDirectory.list();

				Exercise3.printResults(thisCustomDirectory, allFilesCustom);
			}
			
				
			option = 0;
			break;

		default:
			System.out.println("Has salido del programa.");
			break;
		}

		scanner.close();
	}

}
