package heredaHijo;

public class MainThread {
	public static void main(String[] args) {
		Thread primer = new HeredaHijo("Fil 1");
		Thread segon = new HeredaHijo("Fil 2");

		// Hem creat dos fils primer i segon, per� no s'han executat.
		// Per poder-lo executar s'ha de cridar al m�tode start()
		primer.start();
		segon.start();

		System.out.println("Final Fil Principal");
	}
}