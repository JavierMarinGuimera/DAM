package app;

import models.Pila;

public class App {
	public static int MAX_SIZE = 5;

	public static void main(String[] args) {
		
		ejercicioE2A();
	}

	private static void ejercicioE2A() {
		Pila pila = new Pila(MAX_SIZE);
		
		try {
			pila.apilar(1);
			pila.apilar(2);
			pila.apilar(3);
			
			System.out.println(pila.desapilar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
