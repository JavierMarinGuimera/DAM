package race;

import java.util.ArrayList;
import java.util.List;

public class RaceStatus {
	public static final int SEC_CONVERSION = 140;
	
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false; 
	private int racers;
	
	public RaceStatus(List<Pilot> score, boolean finish, int racers) {
		super();
		this.score = score;
		this.finish = finish;
		this.racers = racers;
	}
	
	public synchronized List<Pilot> getScore() {
		return score;
	}
	
	public synchronized void setScore(List<Pilot> score) {
		this.score = score;
	}
	
	public synchronized boolean isFinish() {
		return this.finish;
	}
	
	
	public synchronized void setFinish(boolean finish) {
		this.finish = finish;
	}

	public synchronized int getRacers() {
		return racers;
	}

	public synchronized void setRacers(int racers) {
		this.racers = racers;
	}

	public void lap(Pilot pilot) {
		pilot.setLaps(pilot.getLaps() - 1);
		
		if (pilot.getLaps() == 0) {			
			synchronized (this) {
				racers = (racers - 1);
				score.add(pilot);
				if (racers == 0) {
					setFinish(true);
				}
			}
		}
	}
	
	public String convertTime (int time) {
		time *= SEC_CONVERSION;
		int mins = (time / 1000) / 60;
		time = time - (mins * 60 * 1000);
		int sec = time / 1000;
		int milisec = (time % 1000)	;
		return String.format((mins > 0 ? mins + ":" : "") + "%02d:%03ds", sec, milisec);
	}

	@Override
	public String toString() {
		String results = "[RACE RESULTS]\n";
		int index = 1;
		
		for (Pilot pilot : score) {
			results += String.format("%02d. %-10s | %12s", index, pilot.getName(), (index == 1 ? convertTime(pilot.getTime()) + "" : "+" + convertTime(pilot.getTime() - score.get(0).getTime()))) + "\n";
			index++;
		}
		
		return results;
	}
	
}
