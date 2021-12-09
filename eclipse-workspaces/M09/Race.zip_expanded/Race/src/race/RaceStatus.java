package race;

import java.util.ArrayList;
import java.util.List;

public class RaceStatus {
	public static final int SEC_CONVERSION = 140;
	
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false; 
	private int racersThatEnded;
	
	public RaceStatus(List<Pilot> score, boolean finish, int racers) {
		super();
		this.score = score;
		this.finish = finish;
		this.setRacers(racers);
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
		return racersThatEnded;
	}

	public synchronized void setRacers(int racers) {
		this.racersThatEnded = racers;
	}

	public void lap(Pilot pilot) {
		pilot.setLaps(pilot.getLaps() - 1);
		
		System.out.println("Pilot: [" + pilot.getName() + ", laps = " + pilot.getLaps() + ", total race time: " + this.convertTime(pilot.getTime())+ "]");
		
		synchronized (this) {
			if (pilot.getLaps() == 0) {			
				this.setRacers(this.getRacers() - 1);
				if (this.getRacers() == 0) {
					this.setFinish(true);
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
		return (mins + "\'" + sec + "\"" + milisec);
	}

	@Override
	public String toString() {
		return null;
		
	}
	
}
