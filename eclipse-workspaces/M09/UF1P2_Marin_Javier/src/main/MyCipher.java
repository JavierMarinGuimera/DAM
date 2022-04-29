package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MyCipher {
	private static final String AES_SUFFIX = ".aes";
	public static final int BITS_128 = 128;
	public static final int BITS_192= 192;
	public static final int BITS_256 = 256;
	public static final byte[] BUFFER = new byte[1024];
	
	public static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03,
			0x04, 0x05, 0x06, 0x07,
			0x08, 0x09, 0x0A, 0x0B,
			0x0C, 0x0D, 0x0E, 0x0F};
	
	private MyCipher() {};

	/**
	 * Generates a new Secret key.
	 * @param password Password used to encrypt or decrypt.
	 * @param keySize The size of the key.
	 * @return
	 */
	public static SecretKey passwordKeyGeneration(String password, int keySize) {
		SecretKey sKey = null;

		if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			// AES only works with size keys of 128, 192 or 256 bits
			try {
				byte[] data = password.getBytes("UTF-8"); // Transform password in a byte[], codified in UTF-8

				MessageDigest md = MessageDigest.getInstance("SHA-256"); // Get a MessageDigest instance for create
																			// SHA-256 messages

				byte[] hash = md.digest(data); // Digest the data obtained from the password

				byte[] key = Arrays.copyOf(hash, keySize / 8); // Cut the hash to keySize bits (keysize/8 bytes)

				sKey = new SecretKeySpec(key, "AES"); // Create the SecretKey object based on AES encrypt method from
														// the obtained key
			} catch (Exception ex) {
				System.err.println("Could not generate the AES key:" + ex);
			}

		}

		return sKey;
	}

	/**
	 * Encrypt or decrypt the file passed on the last parameter.
	 * @param sKey Secret key to encrypt or decrypt the file.
	 * @param cypherMethod The method that will be used to encrypt or decrypt the data (AES/ECB or AES/CBC).
	 * @param inputFile The file to be processed.
	 */
	public static void encryptOrDecryptFile(SecretKey sKey, String cypherMethod, File inputFile) {
		boolean encrypt = !inputFile.getName().endsWith(AES_SUFFIX);
		
		try {					
			// Input stream from the inputFile
			FileInputStream is = new FileInputStream(inputFile);
			// Output stream to a file with or without the sufix AES_SUFFIX on the same directory of the inputFile.
			File outputFile = new File((encrypt ? inputFile.getAbsolutePath() + AES_SUFFIX : inputFile.getAbsolutePath().replace(AES_SUFFIX, "")));
            FileOutputStream os = new FileOutputStream(outputFile);
						
			int length;
			IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
			Cipher cipher = Cipher.getInstance(cypherMethod);
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), sKey, iv);
			
			System.out.println("Copying file:");
			while ((length = is.read(BUFFER)) > 0) {
				System.out.print(".");
                os.write(cipher.update(BUFFER, 0, length));
            }
			
			boolean hasToDeleteOutputFile = false;
			
			try {
				os.write(cipher.doFinal());	
			} catch (Exception e) {
				System.out.println("The password used to decrypt the file is not correct.");
				hasToDeleteOutputFile = true;
			} finally {
				os.close();
				is.close();
			}
			
			if (hasToDeleteOutputFile) {
				wipeFile(outputFile);
			} else {
				wipeFile(inputFile);
			}
			
		} catch (Exception ex) {
			System.err.println("Could not " + (encrypt ? "encrypt" : "decrypt")+ " the data: " + ex);
			ex.printStackTrace();
		}
	}

	private static void wipeFile(File file) {
		try {
			if (file.exists()) {
			    long length = file.length();
			    SecureRandom random = new SecureRandom();
			    RandomAccessFile raf = new RandomAccessFile(file, "rws");
			    raf.seek(0);
			    raf.getFilePointer();
			    byte[] data = new byte[64];
			    int pos = 0;
			    while (pos < length) {
			        random.nextBytes(data);
			        raf.write(data);
			        pos += data.length;
			    }
			    raf.close();
			    System.out.println("Deleting file");
			    file.delete();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Tried to delete a file that wasnt encountered");
		} catch (IOException e) {
			System.out.println("Error sobrescribiendo en el archivo");
		}
	}
}
