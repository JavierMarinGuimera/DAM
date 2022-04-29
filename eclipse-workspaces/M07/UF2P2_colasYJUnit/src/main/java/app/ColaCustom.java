package app;

public class ColaCustom {
	private static int max_size;
	private static int[] elements;
	private static int mida;
	private static int primer;
	private static int darrer;

	public ColaCustom(int x) {
		max_size = x;
		elements = new int[max_size];
		mida = 0;
		primer = 0;
		darrer = 0;
	}

	public void encuar(int x) throws Exception {
		if (mida == max_size) {
			throw new Exception("La cua és plena");
		}

		elements[darrer] = x;
		mida++;
		darrer++;

		if (darrer == max_size) {
			darrer = 1;
		}

		checkRep();
	}

	public int desencuar() throws Exception {
		if (mida == 0) {
			throw new Exception("La cua és buida");
		}
		int element = elements[primer];

		mida--;
		primer++;
		if (primer == max_size) {
			primer = 0;
		}
		return element;
	}

	public boolean esBuida() {
		checkRep();
		return elements.length > 0;
	}

	private void checkRep() {
		assert 0 <= mida && mida <= max_size;
		if (darrer > primer)
			assert mida == (darrer - primer); // posar aquí un assert: assert mida == ...;
		else if (darrer < primer)
			assert mida == (max_size - (primer - darrer)); // posar aquí un assert: assert mida == ...;
		else // darrer == primer
			assert mida == 0 || mida == max_size;
	}
}
