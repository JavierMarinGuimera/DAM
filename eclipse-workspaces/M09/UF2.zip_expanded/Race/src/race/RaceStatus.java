package race;

import java.util.ArrayList;
import java.util.List;

public class RaceStatus {
	
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false;
	
	public RaceStatus(List<Pilot> score, boolean finish) {
		super();
		this.score = score;
		this.finish = finish;
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

	public void lap(Pilot pilot) {
		
	}

	@Override
	public String toString() {
		return null;
		
	}
	
}
