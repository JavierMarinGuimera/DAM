package race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Race {
	
	private static final int MAX_LAPS = 10;
	private static String[] TEAM_NAMES = {"Mercedes", "Red Bull", "McLaren", "Renault"};
	private static String[] RACERS_NAMES = {"Javi" , "Toni", "Sergi", "Norbert", "Rebeca", "Andrea", "Sofia", "Julia"};
	private static int racers = RACERS_NAMES.length;
	
	public static void main(String[] args) throws InterruptedException {
		if (TEAM_NAMES.length != racers / 2 || racers % 2 == 1) {
			System.out.println("¡ATENCIÓN! ¡Deben haber 2 jugadores por equipo mínimo!");
		} else {
			List<Pilot> pilots = new ArrayList<>();
			List<Thread> pilotsThreads = new ArrayList<Thread>();
			Map<String, Team> teamsPilots = new HashMap<>();
			RaceStatus raceStatus = new RaceStatus(new ArrayList<Pilot>(), false, racers);
			
			createPilots(pilots, pilotsThreads, raceStatus);
			createBoxesAndTeams(pilots, raceStatus, teamsPilots);
			
			startAndWaitThreads(pilotsThreads);
			
			System.out.println(raceStatus);
		}
		
	}
	
	private static void createPilots(List<Pilot> pilots, List<Thread> pilotsThreads, RaceStatus raceStatus) {
		for (int i = 0; i < racers; i++) {
			Pilot pilot = new Pilot(RACERS_NAMES[i], MAX_LAPS, raceStatus);
			pilots.add(pilot);
			Thread currentPilot = new Thread(pilot);
			currentPilot.setName(pilot.getName());
			pilotsThreads.add(currentPilot);
		}
	}
	
	private static void createBoxesAndTeams(List<Pilot> pilots, RaceStatus raceStatus, Map<String, Team> teamsPilots) {
		for (int i = 0; i < racers / 2; i++) {
			List<Pilot> teamPilots = new ArrayList<>();
			
			teamPilots.add(pilots.get(i));
			teamPilots.add(pilots.get(racers - 1 - i));
			
			teamsPilots.put(TEAM_NAMES[i], new Team(TEAM_NAMES[i], teamPilots, raceStatus));
		}
	}

	private static void startAndWaitThreads(List<Thread> pilotsThreads) throws InterruptedException {
		System.out.println(String.format("%-20s %s %20s", "", "The race just started!", ""));
		System.out.println("-------------------------------------------------------------------------");
		for (Thread thread : pilotsThreads) {
			thread.start();
		}
		
		for (Thread thread : pilotsThreads) {
			thread.join();
		}
	}
}
