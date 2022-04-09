package main;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Main {

	public static void main(String[] args) {
		byte[] codedMessage = new byte[] { (byte) 0xB3, (byte) 0xF9, (byte) 0xB2, (byte) 0x72, (byte) 0xA9, (byte) 0x07,
				(byte) 0x1F, (byte) 0xA4, (byte) 0x91, (byte) 0xA0, (byte) 0x1B, (byte) 0x48, (byte) 0x0D, (byte) 0x4F,
				(byte) 0xEC, (byte) 0xA0, (byte) 0x56, (byte) 0x54, (byte) 0x4A, (byte) 0xBB, (byte) 0x3C, (byte) 0xF1,
				(byte) 0xFF, (byte) 0x4F, (byte) 0xD0, (byte) 0x7F, (byte) 0xA6, (byte) 0x9B, (byte) 0x8F, (byte) 0x74,
				(byte) 0xF6, (byte) 0x8F };

	}

	public byte[] encryptOrDecryptData(SecretKey sKey, byte[] data, boolean encrypt) {

		byte[] dataToHandle = null;

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), sKey);
			dataToHandle = cipher.doFinal(dataToHandle);
		} catch (Exception ex) {
			System.err.println("Could not " + (encrypt ? "encrypt" : "decrypt")+ " the data: " + ex);
		}

		return dataToHandle; // Return the encrypted data
	}

}
