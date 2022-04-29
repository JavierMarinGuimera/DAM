package app;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class App {
	public static final int MAX_LOOPS = 100000;
	
	static final String ARRAY_LIST = "ArrayList";
	static final String LINKED_LIST = "LinkedList";
	static final String COLA_CUSTOM = "Cola custom";

	public static TreeMap<String, Long> times = new TreeMap<String, Long>();

	public static void main(String[] args) {
		checkList(ARRAY_LIST, new ArrayList<Integer>());
		checkList(LINKED_LIST, new LinkedList<Integer>());
		checkCustomList(COLA_CUSTOM, new ColaCustom(MAX_LOOPS));

		showResults();
	}

	private static void checkCustomList(String typeList, ColaCustom list) {
		long currentMiliseconds = System.currentTimeMillis();

		try {
			for (int i = 0; i < MAX_LOOPS; i++) {
				list.encuar(0);
			}

			for (int i = MAX_LOOPS - 1; i <= 0; i--) {
				list.desencuar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		times.put(typeList, System.currentTimeMillis() - currentMiliseconds);
	}

	private static void checkList(String typeList, List<Integer> list) {
		long currentMiliseconds = System.currentTimeMillis();

		for (int i = 0; i < MAX_LOOPS; i++) {
			list.add(i);
		}

		for (int i = MAX_LOOPS - 1; i <= 0; i--) {
			list.remove(i);
		}

		times.put(typeList, System.currentTimeMillis() - currentMiliseconds);
	}

	private static void showResults() {
		times.forEach((k, v) -> {
			System.out.println(k + " ha tardado " + v + " milisegundos.");
		});
	}
}