package udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Request {
	public static final byte OP_ADD = (byte) 0x00;
	public static final byte OP_SUB = (byte) 0x01;
	public static final byte OP_MUL = (byte) 0x02;
	public static final byte OP_DIV = (byte) 0x03;

	public static final byte STOP = (byte) 0xFF;

	private byte operation;
	private short op1;
	private short op2;

	private byte[] bytes;

	public Request(byte operation, short op1, short op2) throws IOException {
		setOperation(operation);
		setOp1(op1);
		setOp2(op2);
		dataToBytes();
	}

	public Request(char operation, short op1, short op2) throws IOException {
		setOperation(operation);
		setOp1(op1);
		setOp2(op2);
		dataToBytes();
	}

	public Request(byte[] requestData) throws IOException {
		this.bytes = requestData;
		bytesToData();
	}

	public byte getOperation() {
		return operation;
	}

	public void setOperation(byte operation) {
		this.operation = operation;
	}

	public void setOperation(char operation) {
		switch (operation) {
		case '+':
			this.operation = OP_ADD;
			break;
		case '-':
			this.operation = OP_SUB;
			break;
		case '*':
			this.operation = OP_MUL;
			break;
		case '/':
			this.operation = OP_DIV;
			break;
		}
	}

	public short getOp1() {
		return op1;
	}

	public void setOp1(short op1) {
		this.op1 = op1;
	}

	public short getOp2() {
		return op2;
	}

	public void setOp2(short op2) {
		this.op2 = op2;
	}

	public byte[] getBytes() {
		return bytes;
	}

	private void dataToBytes() throws IOException {
		// TODO transform data to bytes
	}

	private void bytesToData() throws IOException {
		ByteArrayInputStream arrayIn = new ByteArrayInputStream(bytes);
		DataInputStream dataIn = new DataInputStream(arrayIn);

		setOperation(dataIn.readByte());
		switch (operation) {
		case OP_ADD:
		case OP_SUB:
		case OP_MUL:
		case OP_DIV:
			setOp1(dataIn.readShort());
			setOp2(dataIn.readShort());
		}

		dataIn.close();
	}
}
