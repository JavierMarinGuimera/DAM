package factorial;

public class Factorial {
	/**
	 * Method to get the recursive factorial
	 * @param n First parameter
	 * @return Double
	 * @throws IllegalArgumentException
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

	/**
	 * Method to get the recursive fact.
	 * @param n First parameter
	 * @return Double
	 */
	public double getRecursiveFact(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}
		Factorial fmenos1 = new Factorial();
		return n * fmenos1.getRecursiveFact(n - 1);
	}

	/**
	 * Method to get the iterative factorial.
	 * @param n First parameter.
	 * @return Double
	 * @throws IllegalArgumentException
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