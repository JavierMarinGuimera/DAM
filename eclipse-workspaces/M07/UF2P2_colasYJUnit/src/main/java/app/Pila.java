package app;

public class Pila {
	final int MIDA_MAX;
	int mida;
	public int elements[];

	public Pila(int midaMax) {
		MIDA_MAX = midaMax;
		mida = 0;
		elements = new int[MIDA_MAX];
	}

	public boolean esBuida() {
		return mida == 0;
	}

	public boolean esPlena() {
		return mida == MIDA_MAX;
	}

	public void apilar(int element) throws Exception {
		if (this.mida == MIDA_MAX) {
			throw new Exception("La pila és plena");
		}
		this.elements[mida] = element;
		System.out.println(this.mida);
		this.mida++;
	}

	public int desapilar() throws Exception {
		if (this.mida == 0) {
			throw new Exception("La cua és buida");
		}
		int element = this.elements[this.mida - 1];
		this.mida--;
		return element;
	}
}
