package heredaHijo;

public class HeredaHijo extends Thread {

	String strImprimir;

	public HeredaHijo(String strP) {
		strImprimir = strP;
	}

	public void run() {
		for (int x = 0; x < 5; x++) {
			System.out.println(strImprimir + " " + x);
		}

	}
}