package carrera;

import java.util.TreeMap;

public class Race {
	
	public static Scoreboard scoreboard;
	
	public static void main(String[] args) throws InterruptedException {
		scoreboard = new Scoreboard(new TreeMap<String, String>());

		Runner corredor1 = new Runner("Javi");
		Runner corredor2 = new Runner("Toni");
		Runner corredor3 = new Runner("Sergi");

		Thread corredor1Thread = new Thread(corredor1);
		corredor1Thread.setName("Corredor 1, " + corredor1.getName() + ", ");
		Thread corredor2Thread = new Thread(corredor2);
		corredor2Thread.setName("Corredor 2, " + corredor2.getName() + ", ");
		Thread corredor3Thread = new Thread(corredor3);
		corredor3Thread.setName("Corredor 3, " + corredor3.getName() + ", ");

		corredor1Thread.start();
		corredor2Thread.start();
		corredor3Thread.start();

		corredor1Thread.join();
		corredor2Thread.join();
		corredor3Thread.join();

		scoreboard.getRunnersTimes();
		System.out.println("Final Hilo Principal");
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		Race.scoreboard = scoreboard;
	}
	
	
}
