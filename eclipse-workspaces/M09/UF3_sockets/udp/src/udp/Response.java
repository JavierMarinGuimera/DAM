package udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Response {
	public static final byte OK = (byte) 0x00;
	public static final byte ERROR_DIV_0 = (byte) 0xF0;
	public static final byte ERROR = (byte) 0xFF;

	private byte errorCode;
	private long result;

	private byte[] bytes;

	public Response(byte errorCode, long result) throws IOException {
		setErrorCode(errorCode);
		setResult(result);
		dataToBytes();
	}

	public Response(byte[] responseData) throws IOException {
		this.bytes = responseData;
		bytesToData();
	}

	public byte getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(byte errorCode) {
		this.errorCode = errorCode;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * This method is USED BY THE SERVER to mount the bytes array that WILL BE SEND
	 * TO THE CLIENT.
	 * 
	 * @throws IOException
	 */
	private void dataToBytes() throws IOException {
		ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(arrayOut);

		dataOut.writeByte(errorCode);
		dataOut.writeLong(result);
		bytes = arrayOut.toByteArray();

		dataOut.close();
	}

	/**
	 * This method is USED BY THE SERVER to get the data RECEIVED FROM THE CLIENT.
	 * 
	 * @throws IOException
	 */
	private void bytesToData() throws IOException {
		ByteArrayInputStream arrayIn = new ByteArrayInputStream(bytes);
		DataInputStream dataIn = new DataInputStream(arrayIn);

		this.setErrorCode(dataIn.readByte());
		this.setResult(dataIn.readLong());

		dataIn.close();
	}
}
