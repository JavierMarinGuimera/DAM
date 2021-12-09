package carrera;

import java.util.Map;
import java.util.TreeMap;

public class Scoreboard {
	public static final int SEC_CONVERSION = 210; 
	
	private TreeMap<String, String> scoreboard;
	
	public Scoreboard(TreeMap<String, String> scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	public String convertTime (int time) {
		time *= SEC_CONVERSION;
		int mins = (time / 1000) / 60;
		time = time - (mins * 60 * 1000);
		int sec = time / 1000;
		int milisec = (time % 1000)	;
		return (mins + "\'" + sec + "\"" + milisec);
	}

	// Getter
	public synchronized void getRunnersTimes() {
		for (Map.Entry<String, String> entry : scoreboard.entrySet()) {
		    System.out.println(entry.getKey() + " / " + entry.getValue());
		    
		}
	}

	// Setter
	public synchronized void insertRunner(String runner, String time) {
		scoreboard.put(runner, time);
	}
}
