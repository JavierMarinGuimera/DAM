package race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceStatus {
	public static final int SEC_CONVERSION = 140;

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
		pilot.setLaps(pilot.getLaps() - 1);

		if (pilot.getLaps() == 0 || isFinish()) {
			if (pilot.getLaps() == 0) {
				synchronized (this) {
					setFinish(true);
					System.out.println(pilot + " ended the race!");
				}
			}
			
			score.add(pilot);
		}
	}

	public String convertTime(int time) {
		time *= SEC_CONVERSION;
		int mins = (time / 1000) / 60;
		time = time - (mins * 60 * 1000);
		int sec = time / 1000;
		int milisec = (time % 1000);
		return String.format((mins > 0 ? mins + ":" : "") + "%02d:%03ds", sec, milisec);
	}

	@Override
	public String toString() {
		Collections.sort(score);
		
		String results = "[RACE RESULTS]\n";
		int index = 1;

		for (Pilot pilot : score) {
			results += String.format("%02d. %-10s | ", index, pilot.getName());

			if (pilot.getLaps() == 0) {
				results += String.format("%10s", index == 1 ? convertTime(pilot.getTime()) + ""
						: "+" + convertTime(pilot.getTime() - score.get(0).getTime()));
			} else {
				results += String.format("%10s", "+" + pilot.getLaps() + (pilot.getLaps() > 1 ? " laps" : " lap"));
			}

			results += "\n";

			index++;
		}

		return results;
	}

}
