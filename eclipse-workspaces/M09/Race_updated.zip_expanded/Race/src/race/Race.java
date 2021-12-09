package race;

import java.util.ArrayList;
import java.util.List;

public class Race {
	
	private static final int MAX_LAPS = 10;
	private static final String[] RACERS_NAMES = {"Javi" , "Toni", "Sergi", "Norbert", "Rebeca", "Andrea", "Sofia", "Julia"};
	private static int racers = RACERS_NAMES.length;
	
	public static void main(String[] args) throws InterruptedException {
		List<Thread> pilotsThreads = new ArrayList<Thread>();
		RaceStatus raceStatus = new RaceStatus(new ArrayList<Pilot>(), false, racers);
		
		createPilots(pilotsThreads, raceStatus);
		
		startAndWaitThreads(pilotsThreads);
		
		System.out.println(raceStatus);
		
	}

	private static void startAndWaitThreads(List<Thread> pilotsThreads) throws InterruptedException {
		for (Thread thread : pilotsThreads) {
			thread.start();
		}
		
		for (Thread thread : pilotsThreads) {
			thread.join();
		}
	}

	private static void createPilots(List<Thread> pilotsThreads, RaceStatus raceStatus) {
		for (int i = 0; i < racers; i++) {
			pilotsThreads.add(new Thread(new Pilot(RACERS_NAMES[i], MAX_LAPS, raceStatus)));			
		}
	}
}
