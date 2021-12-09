package files;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Exercise3 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		// Ejercicio 3:

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

			printResults(thisDirectory, allFiles);
			break;

		case 2:
			String customPath = "";

			while (true) {
				try {
					System.out.println("Introduce el path donde desees ver su contenido:");
					customPath = scanner.nextLine();
					break;
				} catch (Exception e) {
					System.out.println("Ha ocurrido algo");
				}
			}

			File thisCustomDirectory = new File(customPath);
			String[] allFilesCustom = thisCustomDirectory.list();

			printResults(thisCustomDirectory, allFilesCustom);

			break;

		default:
			System.out.println("Has salido del programa.");
			break;
		}
		
		scanner.close();

	}

	public static void printResults(File directory, String[] files) throws IOException {

		if (files != null) {
			Calendar calendar = Calendar.getInstance();

			for (String string : files) {
				File currentFile = new File(directory.getCanonicalPath() + "\\" + string);
				calendar.setTimeInMillis(currentFile.lastModified());

				int mYear = calendar.get(Calendar.YEAR);
				int mMonth = calendar.get(Calendar.MONTH);
				int mDay = calendar.get(Calendar.DAY_OF_MONTH);

				String res = "El archivo actual es un ";

				if (currentFile.isDirectory()) {
					res += "directorio y su última modificación fue el " + mDay + "/" + mMonth + "/" + mYear + ".";

				}

				if (currentFile.isFile()) {
					res += "fichero, su última modificación fue el " + mDay + "/" + mMonth + "/" + mYear
							+ " y tiene un tamaño de " + currentFile.length() + " bytes.";
				}

				System.out.println(res);
			}
		} else {
			System.out.println("No existe el directorio introducido.");
		}
	}

}
