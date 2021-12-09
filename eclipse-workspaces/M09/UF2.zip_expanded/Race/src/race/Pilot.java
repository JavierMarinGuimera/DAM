package race;

public class Pilot implements Runnable{
	
	private String name;
	private int laps;
	private String time;
	private RaceStatus raceStatus;

	public Pilot(String name, int laps, RaceStatus raceStatus) {
		super();
		this.name = name;
		this.laps = laps;
		this.time = "0s";
		this.raceStatus = raceStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public RaceStatus getRaceStatus() {
		return raceStatus;
	}

	public void setRaceStatus(RaceStatus raceStatus) {
		this.raceStatus = raceStatus;
	}

	@Override
	public void run() {
		try {
			int endDiff = 995 + (int) (Math.random() * 10);
			Thread.sleep(endDiff);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
