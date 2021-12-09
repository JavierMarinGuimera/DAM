package race;

import java.util.ArrayList;
import java.util.List;

public class Race {
	
	private static final int MAX_LAPS = 10;
	
	public static void main(String[] args) throws InterruptedException {
		List<Thread> pilotsThreads = new ArrayList<Thread>();
		RaceStatus raceStatus = new RaceStatus(new ArrayList<Pilot>(), false);
		
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
		pilotsThreads.add(new Thread(new Pilot("Javi", MAX_LAPS, raceStatus)));
		pilotsThreads.add(new Thread(new Pilot("Toni", MAX_LAPS, raceStatus)));
		pilotsThreads.add(new Thread(new Pilot("Sergi", MAX_LAPS, raceStatus)));
		pilotsThreads.add(new Thread(new Pilot("Norbert", MAX_LAPS, raceStatus)));
	}
}
