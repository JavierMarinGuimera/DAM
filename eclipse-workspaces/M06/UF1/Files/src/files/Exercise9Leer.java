package files;

import java.io.*;

public class Exercise9Leer {
	public static void main(String[] args) {
//declarem el fitxer
		File fitxer = new File("files/Empleats.dat");
//declarem el stream d'accés aleatori
		RandomAccessFile empleats = null;
		int id;
		char cognom[] = new char[10];
		double salari;
		try {
//obrim el stream en mode lectura
			empleats = new RandomAccessFile(fitxer, "r");
			empleats.seek(0); // ens situem a l'inici del fitxer
			while (empleats.getFilePointer() < empleats.length()) {
//llegim el id
				id = empleats.readInt();
//Llegim caràcter a caràcter el cognom
				for (int i = 0; i < cognom.length; i++) {
					cognom[i] = empleats.readChar();
				}
//El convertim a String
				String cognoms = new String(cognom);
				salari = empleats.readDouble();
				System.out.println("ID: " + id + " Cognom: " + cognoms.trim() + "\t Salari: " + salari);
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
