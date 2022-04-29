package app;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test2 {

	@Test
	public void test() {
		ColaCustom q = new ColaCustom(1);
		assertThrows(Exception.class, () -> {
			q.desencuar();
		});
		
		try {
			q.encuar(0);
			assertThrows(Exception.class, () -> {
				q.encuar(0);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
