package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Exercise10 {

	private static final int COGNOMS_LENGTH = 10;
	
	public static void main(String[] args) throws FileNotFoundException {
		File fitxer = new File("files/Empleats.dat");
		
		RandomAccessFile empleats = new RandomAccessFile(fitxer, "rw");
		Scanner scanner;
		scanner = new Scanner(System.in);
		try {
			positionInEndOfFile(empleats);
			writeOtherEmpleat(empleats, scanner);
			printNewEmpleat(empleats);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	private static void positionInEndOfFile(RandomAccessFile empleats) {
		try {
			System.out.println(empleats.length());
			empleats.seek(empleats.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void writeOtherEmpleat(RandomAccessFile empleats, Scanner scanner) throws IOException {
		StringBuffer buffer = null;

		System.out.println("Escrcibe el id del empleado: ");
		empleats.writeInt(Integer.parseInt(scanner.nextLine()));
		
		System.out.println("Escribe el apellido del empleado: ");
		buffer = new StringBuffer(scanner.nextLine());
		buffer.setLength(COGNOMS_LENGTH);
		empleats.writeChars(buffer.toString());
		
		System.out.println("Escribe el salario del empleado: ");
		empleats.writeDouble(scanner.nextDouble());
		
		System.out.println("Empleado creado correctamente!");
	}
	
	private static void printNewEmpleat(RandomAccessFile empleats) throws IOException {
		empleats.seek(empleats.length() - 32);
		
		int id = empleats.readInt();
		
		char[] cognom = new char[COGNOMS_LENGTH];
		
		for (int i = 0; i < COGNOMS_LENGTH; i++) {
			cognom[i] = empleats.readChar();
		}
		
		String cognoms = new String(cognom);
		
		System.out.println("Este es el nuevo usuario: ID: " + id + ", Cognom: " + cognoms.trim() + "\t Salario: " + empleats.readDouble());
		
	}

}
