package files;

import java.io.*;

public class Exercise9Escribir {
	public static void main(String[] args) {
//declarem el fitxer
		File fitxer = new File("files/Empleats.dat");
//declarem el stream d'accés aleatori
		RandomAccessFile empleats = null;
//dades a introduïr
		String cognoms[] = { "FERNANDEZ", "GIL", "LOPEZ", "SEVILLA" };
		Double salari[] = { 1000.45, 2400.60, 3000.0, 1500.56 };
		int i;
		StringBuffer buffer = null;
		try {
//Obrim el stream en mode lectura/escriptura
			empleats = new RandomAccessFile(fitxer, "rw");
			for (i = 0; i < cognoms.length; i++) {
//El codi de l'empleat serà l'index +1
				empleats.writeInt(i + 1);
//Per emmagatzemar el cognom
				buffer = new StringBuffer(cognoms[i]);
//I Que ocupi sempre 10.
				buffer.setLength(10);
//Escrivim el cognom
				empleats.writeChars(buffer.toString());
//Escrivim el salari
				empleats.writeDouble(salari[i]);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (empleats != null)
				try {
					empleats.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
