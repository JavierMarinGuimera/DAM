package models;

/**
 * Crea una pila que se traduce en una lista  LIFO (Last In First Out).
 * @author Javier Mar�n Guimer�
 * @version 1
 */
public class Pila {
	final int MIDA_MAX;
	int mida;
	public int elements[];

	/**
	 * Constructor.
	 * @param midaMax Tama�o m�ximo para la pila.
	 */
	public Pila(int midaMax) {
		MIDA_MAX = midaMax;
		mida = 0;
		elements = new int[MIDA_MAX];
	}

	/**
	 * Comprueba si la pila est� vacia. 
	 * @return true si est� vacia; false si no est� vacia al completo.
	 */
	public boolean esBuida() {
		return mida == 0;
	}

	/**
	 * Comprueba si la pila est� llena.
	 * @return true si est� llena; false si no est� llena al completo.
	 */
	public boolean esPlena() {
		return mida == MIDA_MAX;
	}

	/**
	 * A�ade un elemento a la pila.
	 * @param element Elemento que se quiere a�adir a la pila.
	 * @throws Exception Lanza excepci�n si la pila est� llena.
	 */
	public void apilar(int element) throws Exception {
		if (this.mida == MIDA_MAX) {
			throw new Exception("La pila �s plena");
		}
		this.elements[mida] = element;
		System.out.println(this.mida);
		this.mida++;
	}

	/**
	 * Elimina un elemento de la pila.
	 * @return Devuelve el elemento extra�do de la pila.
	 * @throws Exception Lanza excepci�n si la pila est� vacia.
	 */
	public int desapilar() throws Exception {
		if (this.mida == 0) {
			throw new Exception("La cua �s buida");
		}
		int element = this.elements[this.mida - 1];
		this.mida--;
		return element;
	}
}
