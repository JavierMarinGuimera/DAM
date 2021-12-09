package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Exercise9 {
	private static final int DOUBLE_BYTES = 8;
	
	public static void main(String[] args) {
		
				File fitxer = new File("files/Empleats.dat");
				
				RandomAccessFile empleats = null;
				char cognom[] = new char[10];
				Scanner scanner;
				scanner = new Scanner(System.in);
				try {
					empleats = getUserById(fitxer, cognom, scanner);
					
					modifySalary(fitxer, cognom, scanner);
					
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
				scanner.close();
			}

	private static RandomAccessFile getUserById(File fitxer, char[] cognom, Scanner scanner)
			throws FileNotFoundException, IOException {
		RandomAccessFile empleats;
		empleats = new RandomAccessFile(fitxer, "r");
		System.out.println("Introduce el número de usuario que quieres leer: ");
		int pos = scanner.nextInt();
		
		if (getInPosition(empleats, cognom, scanner, pos)) {
			int id = empleats.readInt();
			
			for (int i = 0; i < cognom.length; i++) {
				cognom[i] = empleats.readChar();
			}
			
			String cognoms = new String(cognom);
			
			System.out.println("ID: " + id + ", Cognom: " + cognoms.trim() + "\t Salario: " + empleats.readDouble());
		}
		
		
		return empleats;
	}

	private static void modifySalary(File fitxer, char[] cognom, Scanner scanner) throws FileNotFoundException {
		RandomAccessFile empleats;
		empleats = new RandomAccessFile(fitxer, "rw");
		System.out.println("Introduce el número de usuario al que quieres modificar su salario: ");
		int pos = scanner.nextInt();
		
		
		if (getInPosition(empleats, cognom, scanner, pos)) {
			try {
				int id = empleats.readInt();
				
				for (int i = 0; i < cognom.length; i++) {
					cognom[i] = empleats.readChar();
				}
				
				String cognoms = new String(cognom);
				
				double lastSalary = empleats.readDouble();
				
				empleats.seek(empleats.getFilePointer() - DOUBLE_BYTES);
				System.out.println("Introduce el salario nuevo del usuario:");
				empleats.writeDouble(scanner.nextDouble());
				
				empleats.seek(empleats.getFilePointer() - DOUBLE_BYTES);
				
				System.out.println("ID: " + id + ", Cognom: " + cognoms.trim() + "\t Salario antiguo: " + lastSalary + ", Salario nuevo: " + empleats.readDouble() + ".");
			} catch (Exception e) {
				System.out.println("Debes introducir un número valido como 1.0");
			}
		}
	}
	
	private static Boolean getInPosition(RandomAccessFile empleats, char[] cognom, Scanner scanner, int pos) {
		if (pos > 0) {
			try {
				if (empleats.length() >= (pos * 32)) {
					empleats.seek((pos - 1) * 32); // ens situem a l'inici del fitxer
					return true;
				} else {
					System.out.println("No existe un usuario con ese número.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Debes introducir un número mayor que 0.");
		}
		return false;
	}
}
