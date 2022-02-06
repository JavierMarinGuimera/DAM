package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aquesta classe representa la senzilla interfície d'una consola del sistema
 * operatiu. És capaç de llegir de teclat i mostrar missatges i errors per la
 * pantalla.
 */
public class ConsoleInterface {
	public static final int DO_NOT_CHANGE = 0;
	public static final int TO_UPPPER_CASE = 2;
	public static final int TO_LOWER_CASE = 1;
	BufferedReader reader = null;

	/**
	 * Constructor per defecte. Els objectes així instanciats llegiran les dades del
	 * teclat i escriuran els missatges a la pantalla
	 */
	public ConsoleInterface() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Constructor que permet introduir una sequencia de caràcters simualnt la
	 * pulsació de tecles. La sequencia de text pot ser tan llarga com sigui
	 * necessari i incorporar tantes lectures separades per retorns de línia com
	 * calgui.
	 * 
	 * @param scannerText cadena de caràcters amb pulsació de tecles predefinida.
	 */
	public ConsoleInterface(String scannerText) {
		reader = new BufferedReader(new StringReader(scannerText));
	}

	public String readLine() {
		String ret;
		try {
			ret = reader.readLine();
		} catch (IOException ex) {
			throw processError(ex);
		}
		return ret;
	}

	/**
	 * Mostrarà per pantalla el missatge rebut per paràmetre i esperarà la
	 * introducció d'un valor numèric des del teclat. Si l'usuari entre valors no
	 * numèrics, mostrarà una missatge d'error i requerirà una nova introducció.
	 * 
	 * @param message Missatge a mostrar per la pantalla abans de demanar el valor
	 *                numèric.
	 * @return el valor numèric introduït des del teclat.
	 */
	public short readShort(String message) {
		String line;
		do {
			System.out.print(message);
			try {
				line = reader.readLine();
				return Short.parseShort(line);
			} catch (NumberFormatException | IOException e) {
				System.out.println("Must enter an integer between " + Short.MIN_VALUE + " and " + Short.MAX_VALUE);
			}
		} while (true);
	}

	public char readOperation(String message) {
		String line;
		char ret;
		boolean correct = false;
		do {
			System.out.print(message);
			try {
				line = reader.readLine();
			} catch (IOException ex) {
				throw processError(ex);
			}
			correct = line.matches("[\\+\\-\\*/]");
			if (!correct) {
				System.out.println("Must enter char '+', '-', '*' or '/' ");
			}
		} while (!correct);
		ret = line.charAt(0);
		return ret;
	}

	/**
	 * Mostrarà per pantalla el missatge rebut com a primer paràmetre i esperarà la
	 * introducció d'un caràcter des del teclat. Si l'usuari entra un caràcter igual
	 * al segon paràmetre, es retornarà cert. En cas contrari es retornnrà fals. El
	 * tercer paràmetre permet indicar si es desitja fer un canvi a màjuscules o
	 * minúscules de la tecla premuda per lusuari. El valor TO_UPPER_CASE (2)
	 * canviarà sempre el valor introduït a majúscules abans de fer la comparació.
	 * El valor TO_LOWER_CASE (1)pasarà el valor a minúscules i qualsevol altre
	 * valor no realitzarà cap canvi.
	 * 
	 * @param message     Missatge a mostrar per la pantalla abans de demanar el
	 *                    valor a introduir des de teclat.
	 * @param yesChar.    És un caràcter equivalent al valor afirmatiu o cert per
	 *                    poder comparar amb el valor introduït per l'usuari.
	 * @param changeCase. És un valor numèric que indica si cal canviar el valor de
	 *                    l'usuari a majúscules o minúscules.
	 * @return el valor numèric introduït des del teclat.
	 */
	public boolean readYesNo(String missatge, char yesChar, int changeCase) {
		boolean correct = false;
		String line;
		char ret;
		do {
			System.out.print(missatge);
			try {
				line = reader.readLine();
			} catch (IOException ex) {
				throw processError(ex);
			}
			correct = line.length() > 0;
			if (!correct) {
				System.out.println("Must enter a character");
			}
		} while (!correct);

		if (changeCase == TO_UPPPER_CASE) {
			ret = line.toUpperCase().charAt(0);
		} else if (changeCase == TO_LOWER_CASE) {
			ret = line.toLowerCase().charAt(0);
		} else {
			ret = line.charAt(0);
		}
		return ret == yesChar;
	}

	/**
	 * Mostra per pantalla, el missatge d'error de l'excepció arribada per
	 * paràmetre.
	 * 
	 * @param ex. És l'excepció de la que s'ha de mostrar el missatge d'error.
	 */
	public void showError(Exception ex) {
		System.out.println("Error: " + ex.getLocalizedMessage());
		Logger.getLogger(ConsoleInterface.class.getName()).log(Level.SEVERE, null, ex);
	}

	/**
	 * Mostra el missatge arribat per paràmetre
	 * 
	 * @param message. Missatge d'error a mostrar
	 * @param ex.      excepció llançada qua s'ha produït l'error.
	 */
	public void showError(String message, Exception ex) {
		System.out.println("Error: " + message);
		Logger.getLogger(ConsoleInterface.class.getName()).log(Level.SEVERE, message, ex);
	}

	/**
	 * Mostra el missatge arribat per paràmetre
	 * 
	 * @param message. Missatge d'error a mostrar
	 */
	public void showError(String message) {
		System.out.println("Error: " + message);
	}

	/**
	 * Mostra el missatge arribat per paràmetre
	 * 
	 * @param message. Missatge a mostrar
	 */
	public void showMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Retorna cert si hi ha caracter disponible al buffer del teclat. Aquest mètode
	 * no es bloquejant, pel que pot ser molt útil per sistemes que no es puguin
	 * bloquejar esperant la resposta de l'usuari.
	 * 
	 * @return
	 */
	public boolean isKeyReady() {
		boolean ret = false;
		try {
			ret = reader.ready();
		} catch (IOException ex) {
			throw processError(ex);
		}
		return ret;

	}

	/**
	 * LLegeix un caracter del teclat quan estigui disposnible i el retorna.
	 * 
	 * @return la tecla polsada just despres de la darrera lectura.
	 */
	public char read() {
		char character = 0;
		try {
			character = (char) reader.read();
		} catch (IOException ex) {
			throw processError(ex);
		}
		return character;
	}

	private NoSuchElementException processError(Exception ex) {
		NoSuchElementException except = new NoSuchElementException(ex.getMessage());
		except.initCause(ex);
		return except;
	}
}
