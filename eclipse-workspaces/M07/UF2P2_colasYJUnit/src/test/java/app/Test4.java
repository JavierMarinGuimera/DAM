package app;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test4 {

	@Test
	public void test() {
		ColaCustom q = new ColaCustom(2);
		try {
			q.encuar(1);
			
			assertThrows(Exception.class, () -> {
				q.encuar(0);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
