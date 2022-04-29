package app;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test3 {

	@Test
	public void test() {
		ColaCustom q = new ColaCustom(2);
		try {
			q.encuar(1);
			q.encuar(2);
			q.desencuar();
			q.encuar(3);
			
			assertEquals(3, q.desencuar());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
