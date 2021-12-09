package temperaturas;

public class Temperaturas {
	
	public static final int YEAR_DAYS = 365;
	public static final int MAX_YEARS = 10;
	public static final int THREADS = 10;
	
	public static final int MIN_WINTER = -5;
	public static final int MAX_WINTER = 15;
	public static final int MIN_SPRING_FALL = 10;
	public static final int MAX_SPRING_FALL = 25;
	public static final int MIN_SUMMER = 20;
	public static final int MAX_SUMMER = 40;
	
	public static int[] temps = new int[YEAR_DAYS * MAX_YEARS];
	private static Register register;
	
	public static void main(String[] args) throws InterruptedException {
		register = new Register();
		
		Thread[] hilos = new Thread[THREADS];
		
		for (int i = 0; i < THREADS; i++) {
			for (int j = 0; j < YEAR_DAYS; j++) {
				if (j <= 80 || j >= 355) {
					// INVIERNO
					temps[(i + 1) * j] = getRandom(MIN_WINTER, MAX_WINTER);
				} else if ((j >= 81 && j <= 172) || (j >= 264 && j <= 354)) {
					// PRIMAVERA U OTOÑO
					temps[(i + 1) * j] = getRandom(MIN_SPRING_FALL, MAX_SPRING_FALL);
				} else {
					// VERANO
					temps[(i + 1) * j] = getRandom(MIN_SUMMER, MAX_SUMMER);
				}
			}
			
			int start = YEAR_DAYS * i;
			int end = YEAR_DAYS * (i + 1);
			
			Hilo hilo = new Hilo("Hilo " + i, start, end);
			hilos[i] = new Thread(hilo);
			hilos[i].setName(hilo.getName() + ", ");
		}
		
//		temps[1] = 45;
		
		for (Thread thread : hilos) {
			thread.start();
		}
		
		for (Thread thread : hilos) {
			thread.join();
		}
		
		System.out.println("La temperatura máxima es de " + register.getMaxTemp() + "Cº.");
		
	}

	public static Register getRegister() {
		return register;
	}

	public static void setRegister(Register register) {
		Temperaturas.register = register;
	}
	
	public static int getRandom(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
