package main;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Main {
	private static final int BITS_256 = 256;

	public static void main(String[] args) {
		byte[] codedMessage = new byte[] { (byte) 0xB3, (byte) 0xF9, (byte) 0xB2, (byte) 0x72, (byte) 0xA9, (byte) 0x07,
				(byte) 0x1F, (byte) 0xA4, (byte) 0x91, (byte) 0xA0, (byte) 0x1B, (byte) 0x48, (byte) 0x0D, (byte) 0x4F,
				(byte) 0xEC, (byte) 0xA0, (byte) 0x56, (byte) 0x54, (byte) 0x4A, (byte) 0xBB, (byte) 0x3C, (byte) 0xF1,
				(byte) 0xFF, (byte) 0x4F, (byte) 0xD0, (byte) 0x7F, (byte) 0xA6, (byte) 0x9B, (byte) 0x8F, (byte) 0x74,
				(byte) 0xF6, (byte) 0x8F };

		String currentPasswd;
		byte[] currentMessageAsByteArray;
		String decodedCurrentMessage;
		
		for (int i = 0; i < 100000; i++) {
			currentPasswd = String.format("%05d", i);
			currentMessageAsByteArray = encryptOrDecryptData(passwordKeyGeneration(currentPasswd, BITS_256),
					codedMessage, false);
			if (currentMessageAsByteArray == null) {
				continue;
			}
			
			decodedCurrentMessage = new String(currentMessageAsByteArray);
			
			
			if (isPureAscii(decodedCurrentMessage)) {
				System.out.println("The cyphed message is: " + decodedCurrentMessage);
			}
		}

	}

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

	public static byte[] encryptOrDecryptData(SecretKey sKey, byte[] data, boolean encrypt) {
		byte[] dataToHandle = null;

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), sKey);
			dataToHandle = cipher.doFinal(data);
		} catch (Exception ex) {
			System.err.println("Could not " + (encrypt ? "encrypt" : "decrypt")+ " the data: " + ex);

		}

		return dataToHandle; // Return the data
	}

}
