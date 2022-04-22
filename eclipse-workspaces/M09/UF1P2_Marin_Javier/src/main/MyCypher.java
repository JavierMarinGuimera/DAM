package main;

import java.io.File;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyCypher {
	/**
	 * Cipher methods:
	 */
	public static final String AES_ECB = "AES/ECB/PKCS5Padding";
	public static final String AES_CBC = "AES/CBC/PKCS5Padding";
	
	private static final String FILES_DIR = "docs/";
	private static final String FILES_ENCRIPTED_DIR = "encripted/";
	private static final String FILES_DECRIPTED_DIR = "decripted/";
	
	private MyCypher() {};
	
	private static boolean isPureAscii(String decodedCurrentMessage) {
		return Charset.forName("US-ASCII").newEncoder().canEncode(decodedCurrentMessage);
	}

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

	public static void encryptOrDecryptFile(SecretKey sKey, byte[] data, boolean encrypt, String cypherMethod, File inputFile) {
		try {
			checkOutputDirectories(encrypt);
			
			File outputFile = new File(FILES_DIR + (encrypt ? FILES_ENCRIPTED_DIR : FILES_DECRIPTED_DIR) + (encrypt ? inputFile.getName() + ".aes" : inputFile.getName().replace(".aes", "")));
			
			byte [] dataBuffer;
			
			Cipher cipher = Cipher.getInstance(cypherMethod);
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), sKey);
			
			while (true) {
				break;
			}
			
		} catch (Exception ex) {
			System.err.println("Could not " + (encrypt ? "encrypt" : "decrypt")+ " the data: " + ex);

		}
	}

	private static void checkOutputDirectories(boolean encrypt) {
		createDirIfDoesNotExists(FILES_DIR);
		
		if (encrypt) {
			createDirIfDoesNotExists(FILES_DIR + FILES_ENCRIPTED_DIR);
		} else if (!encrypt){
			createDirIfDoesNotExists(FILES_DIR + FILES_DECRIPTED_DIR);
		}
	}

	private static void createDirIfDoesNotExists(String path) {
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
	}
}
