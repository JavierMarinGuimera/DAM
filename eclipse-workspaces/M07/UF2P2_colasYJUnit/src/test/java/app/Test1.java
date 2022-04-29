package app;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test1 {

	@Test
	public void test1() throws Exception {
		ColaCustom q = new ColaCustom(3);
		assertEquals(true, q.esBuida());
		q.encuar(10);
		q.encuar(11);
		assertEquals(10, q.desencuar());
		assertEquals(11, q.desencuar());
		assertEquals(true, q.esBuida());
		assertThrows(Exception.class, () -> {
			q.desencuar();
		});
	}

}
