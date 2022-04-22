package tests;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
	private static final int MAX_LOOPS = 100000;
	static final String ARRAY_LIST = "ArrayList";
	static final String LINKED_LIST = "LinkedList";
	static final String COLA_CUSTOM= "Cola custom";
	
	public static HashMap<String, Long> times = new HashMap<>();
	
	public static void main(String[] args) {
		long currentMiliseconds = System.currentTimeMillis();
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (int i = 0; i < MAX_LOOPS; i++) {
			al.add(i);
			al.remove(0);
		}
		
		for (int i = 0; i < MAX_LOOPS; i++) {
			
		}
		
		for (int i = 0; i < MAX_LOOPS; i++) {
			
		}
		
		times.put(ARRAY_LIST, 0L);
		times.put(LINKED_LIST, 0L);
		times.put(COLA_CUSTOM, 0L);
	}

}
