package app;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test5 {

	@Test
	public void test() {
		ColaCustom q = new ColaCustom(3);
		try {
			assertThrows(AssertionError.class, () -> {
				q.esBuida();				
			});
			
//			q.encuar(1);
//
//			assertThrows(AssertionError.class, () -> {
//				q.encuar(2);				
//			});
//			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
