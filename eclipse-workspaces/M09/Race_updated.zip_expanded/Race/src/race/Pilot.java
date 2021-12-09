package race;


public class Pilot implements Runnable{
	
	private static final int MINUT = 995;
	private static final int MINUT_DIFF = 30;
	private String name;
	private int laps;
	private int time;
	private RaceStatus raceStatus;

	public Pilot(String name, int laps, RaceStatus raceStatus) {
		super();
		this.name = name;
		this.laps = laps;
		this.time = 0;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
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
			int totalTime;
			while (this.laps > 0 && !this.raceStatus.isFinish()) {
				if (this.laps > 0) {					
					totalTime = MINUT + (int) (Math.random() * MINUT_DIFF);
					Thread.sleep(totalTime);
					
					this.time += totalTime;
					
					this.raceStatus.lap(this);
				} else {
					
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
