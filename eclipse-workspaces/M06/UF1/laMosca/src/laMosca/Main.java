package laMosca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
	private static final String MAIN_FILE = "files/La_mosca.txt";
	private static final String RESULT_FILE = "files/La_mosca_result.txt";
	private static final String[] VOCALES = { "a", "e", "i", "o", "u" };
	private static final int VOCALES_TOTALES = 5;
	private static final String STATISTICS_FILE = "statistics/Lamosca_ocurrencies.dat";

	private static final int LENGTH_ALPHABET = 26;

	public static void main(String[] args) {
		// Insertar una estrofa nueva por cada vocal.
		File finalSong = createFullSong();

		/*
		 * Mostrar por pantalla la información del fichero. - path relativo. - path
		 * canónico. - path del directorio padre. - tamaño del fichero. - fecha última
		 * modificación.
		 */
		if (finalSong != null) {
			showStatistics(finalSong);
		}

		// Mostrar el fichero final.
		readResultFile(finalSong);

		// TODO Contar número de letras del abecedario.
		File statisticsFile = countLetters(finalSong);

		// TODO Mostrar datos letra en específico.
		showStatisticsChar(statisticsFile);
	}

	private static File createFullSong() {
		File previousSong = new File(MAIN_FILE);

		BufferedReader lector = null;
		FileWriter escritor = null;

		try {

			File checkIfExist = new File(RESULT_FILE);

			if (checkIfExist.exists()) {
				checkIfExist.delete();
			}

			Files.copy(previousSong.toPath(), Paths.get(RESULT_FILE));

			File finalSong = new File(RESULT_FILE);

			escritor = new FileWriter(finalSong, true);

			String linea;

			escritor.write("\n \n");

			for (int i = 0; i < VOCALES_TOTALES; i++) {
				lector = new BufferedReader(new FileReader(previousSong));

				while ((linea = lector.readLine()) != null) {
					String newLine = linea.replaceAll("[aeiou]", VOCALES[i]);
					escritor.write(newLine + "\n");
				}

				escritor.write("\n");

				lector.close();
			}

			System.out.println("Canción creada correctamente! \n");

			escritor.close();

			return finalSong;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				escritor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (lector != null) {
				try {
					lector.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;

	}

	private static void showStatistics(File finalSong) {
		System.out.println("Datos del fichero: ");
		// Auto-generated method stub
		System.out.println("- Path relativo: " + finalSong.getPath());
		try {
			System.out.println("- Path canónico: " + finalSong.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("- Path del padre: " + finalSong.getParentFile().getAbsolutePath());
		System.out.println("- Tamaño fichero: " + finalSong.length() + " bytes.");

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(finalSong.lastModified());
		System.out.println("- Última modificación: " + calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));

		System.out.println();
	}

	private static void readResultFile(File finalSong) {
		System.out.println("Este es el archivo resultante: \n");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(finalSong));

			String linea;

			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static File countLetters(File finalSong) {

		/*
		 * Archivo resultante: - Código letra (0 para la a). - Letra (a). - Veces que
		 * aparece (x). - Porcentaje del total (15.0).
		 * 
		 * - EJEMPLO: 0 a 26 13.4
		 */

		File fileToWrite = new File(STATISTICS_FILE);

		try {
			int[] results = new int[LENGTH_ALPHABET];
			Arrays.fill(results, 0);

			int totalChars = 0;

			FileReader fileReader = new FileReader(finalSong);

			RandomAccessFile randomAccessFile = new RandomAccessFile(fileToWrite, "rw");

			int actualChar;

			while ((actualChar = fileReader.read()) > -1) {
				if (actualChar >= 97 && actualChar <= (97 + LENGTH_ALPHABET)) {
					totalChars++;
					results[actualChar - 97]++;
				}
			}

			fileReader.close();

			for (int i = 0; i < results.length; i++) {
				randomAccessFile.writeInt(i);
				randomAccessFile.writeChar((char) (i + 97));
				randomAccessFile.writeInt(results[i]);
				randomAccessFile.writeDouble((double) results[i] / totalChars * 100);
			}

			randomAccessFile.close();

			return fileToWrite;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void showStatisticsChar(File statisticsFile) {
		try {

			Scanner scanner = new Scanner(System.in);
			RandomAccessFile randomAccessFile = new RandomAccessFile(statisticsFile, "r");

			String opcion = "";

			while (!opcion.toLowerCase().equals("salir")) {
				System.out.println(
						"De qué letra quieres saber la información? Si deseas salir del programa, escribe 'Salir'.");

				opcion = scanner.nextLine().trim();

				if (opcion.toLowerCase().equals("salir")) {
					System.out.println("Hasta otra!");
				} else if (opcion.length() != 1) {
					System.out.println("Opción incorrecta!");
				} else {
					int letterToFind = (int)opcion.toLowerCase().charAt(0) - 97;
					
					randomAccessFile.seek((int)letterToFind * 18);
					
					System.out.println("Ascii code: " + randomAccessFile.readInt() + ", Letter: "
							+ randomAccessFile.readChar() + ", Appears: " + randomAccessFile.readInt()
							+ ", Appears: " + String.format("%.2f", randomAccessFile.readDouble())
							+ "%. \n\n");
				}

			}

			randomAccessFile.close();
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

	}
}
