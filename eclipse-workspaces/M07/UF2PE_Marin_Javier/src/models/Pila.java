package models;

/**
 * Crea una pila que se traduce en una lista  LIFO (Last In First Out).
 * @author Javier Marín Guimerà
 * @version 1
 */
public class Pila {
	final int MIDA_MAX;
	int mida;
	public int elements[];

	/**
	 * Constructor.
	 * @param midaMax Tamaño máximo para la pila.
	 */
	public Pila(int midaMax) {
		MIDA_MAX = midaMax;
		mida = 0;
		elements = new int[MIDA_MAX];
	}

	/**
	 * Comprueba si la pila está vacia. 
	 * @return true si está vacia; false si no está vacia al completo.
	 */
	public boolean esBuida() {
		return mida == 0;
	}

	/**
	 * Comprueba si la pila está llena.
	 * @return true si está llena; false si no está llena al completo.
	 */
	public boolean esPlena() {
		return mida == MIDA_MAX;
	}

	/**
	 * Añade un elemento a la pila.
	 * @param element Elemento que se quiere añadir a la pila.
	 * @throws Exception Lanza excepción si la pila está llena.
	 */
	public void apilar(int element) throws Exception {
		if (this.mida == MIDA_MAX) {
			throw new Exception("La pila és plena");
		}
		this.elements[mida] = element;
		System.out.println(this.mida);
		this.mida++;
	}

	/**
	 * Elimina un elemento de la pila.
	 * @return Devuelve el elemento extraído de la pila.
	 * @throws Exception Lanza excepción si la pila está vacia.
	 */
	public int desapilar() throws Exception {
		if (this.mida == 0) {
			throw new Exception("La cua és buida");
		}
		int element = this.elements[this.mida - 1];
		this.mida--;
		return element;
	}
}
