package factorial;

/**
 * <h1>Factorial</h1> The Factorial program implements an application that
 * calculates the Factorial of a number in different ways.
 * 
 * <a href="https://ca.wikipedia.org/wiki/Factorial">Definició de factorial a la
 * Viquipèdia.</a>
 * 
 * @author Sergi Garcia Tocados
 * @version 1.0
 */

public class Factorial {
	/**
	 * This method calculates the factorial of a given number.
	 * 
	 * @param n This is the parameter to do the calculation.
	 * @return int This returns the factorial of the number.
	 * @exception IllegalArgumentException On input error.
	 */
	public double getRecursiveFactorial(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 1 || n == 0) {
			return 1;
		}
		Factorial fmenos1 = new Factorial();
		return n * fmenos1.getRecursiveFactorial(n - 1);
	}

	/** @deprecated */
	public double getRecursiveFact(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}
		Factorial fmenos1 = new Factorial();
		return n * fmenos1.getRecursiveFact(n - 1);
	}

	/**
	 * This method calculates the factorial of a given number.
	 * 
	 * @param n This is the parameter to do the calculation.
	 * @return int This returns the factorial of the number.
	 * @exception IllegalArgumentException On input error.
	 */
	public double getIterativeFactorial(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 1 || n == 0) {
			return 1;
		}
		int aux = 1;
		for (int i = 2; i <= n; i++) {
			aux *= i;
		}
		return aux;
	}
}
