package race;

import java.util.ArrayList;
import java.util.List;

public class Race {
	public static final int PADDING_TO_CENTER = 15;
	public static final int LAP_INFO_PADDING = 3;
	
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
			List<Team> teams = new ArrayList<>();
			List<Thread> teamsThreads = new ArrayList<Thread>();
			RaceStatus raceStatus = new RaceStatus(new ArrayList<Pilot>(), false, racers);
			
			createPilots(pilots, pilotsThreads, raceStatus);
			
			createBoxesAndTeams(pilots, teams, raceStatus, teamsThreads);
			
			startAndWaitThreads(pilotsThreads, teams, teamsThreads);
			
			System.out.println(raceStatus);
		}
	}
	
	private static void createPilots(List<Pilot> pilots, List<Thread> pilotsThreads, RaceStatus raceStatus) {
		System.out.println(String.format("%-" + PADDING_TO_CENTER + "s %s", "", "Preparing the " + racers + " pilots! \n"));
		
		for (int i = 0; i < racers; i++) {
			Pilot pilot = new Pilot(RACERS_NAMES[i], MAX_LAPS, raceStatus);
			pilots.add(pilot);
			Thread currentPilot = new Thread(pilot);
			currentPilot.setName(pilot.getName());
			pilotsThreads.add(currentPilot);
		}
	}
	
	private static void createBoxesAndTeams(List<Pilot> pilots, List<Team> teams, RaceStatus raceStatus, List<Thread> boxesThreads) {
		System.out.println(String.format("%-" + PADDING_TO_CENTER + "s %s", "", "Preparing the teams and their boxes! \n"));
		
		for (int i = 0; i < racers / 2; i++) {
			List<Pilot> teamPilots = new ArrayList<>();
			
			teamPilots.add(pilots.get(i));
			teamPilots.add(pilots.get(racers - 1 - i));
			
			Team team = new Team(TEAM_NAMES[i], teamPilots, raceStatus);
			teams.add(team);
			Thread currentTeam = new Thread(team.getBox());
			currentTeam.setName(team.getName());
			boxesThreads.add(currentTeam);
		}
	}

	private static void startAndWaitThreads(List<Thread> pilotsThreads, List<Team> teams, List<Thread> boxesThreads) throws InterruptedException {
		System.out.println(String.format("%-" + PADDING_TO_CENTER + "s %s %20s", "", "The race just started!", ""));
		System.out.println("----------------------------------------------------------- \n");
		
		for (Thread box : boxesThreads) {
			box.start();
		}

		for (Thread pilot : pilotsThreads) {
			pilot.start();
		}
		
		for (Thread pilot : pilotsThreads) {
			pilot.join();
		}

		
		
		for (Team team : teams) {
			synchronized (team.getBox()) {
				team.getBox().notify();
			}
		}
		
		for (Thread box : boxesThreads) {
			box.join();
		}
		
		System.out.println(String.format("%-" + PADDING_TO_CENTER + "s %s", "", "RACE ENDED!"));
	}
}
