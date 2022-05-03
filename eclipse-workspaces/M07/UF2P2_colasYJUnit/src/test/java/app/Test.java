package app;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		Pila pila = new Pila(5);
		
		assertEquals(true, pila.esBuida());
		assertEquals(false, pila.esPlena());
		
		assertThrows(Exception.class, () -> {
			pila.desapilar();
		});
		
		try {
			pila.apilar(0);
			pila.apilar(1);
			pila.apilar(2);
			pila.apilar(3);
			pila.apilar(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThrows(Exception.class, () -> {
			pila.apilar(0);
		});
		
		try {
			assertEquals(4, pila.desapilar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
