package race;


public class Pilot implements Runnable {
	private static final int MINUT = 1000;
	private static final int MINUT_DIFF = 300;
	
	private static final int MAX_TANK = 25;
	
	private String name;
	private int laps;
	private int time;
	private RaceStatus raceStatus;
	private Team team;
	private int fuelTank;

	public Pilot(String name, int laps, RaceStatus raceStatus) {
		super();
		this.name = name;
		this.laps = laps;
		this.time = 0;
		this.raceStatus = raceStatus;
		this.fuelTank = MAX_TANK;
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
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getFuelTank() {
		return fuelTank;
	}

	public void setFuelTank(int fuelTank) {
		this.fuelTank = fuelTank;
	}

	public synchronized void refuel() {
		fuelTank = MAX_TANK;
		System.out.println(name + " has his tank full.");
		notify();
	}

	@Override
	public void run() {
		try {
			int totalTime;
			while (laps > 0 && !raceStatus.isFinish()) {
				if (laps > 0) {					
					totalTime = MINUT + (int) (Math.random() * MINUT_DIFF);
					Thread.sleep(totalTime);
					
					fuelTank -= 4;
					
					if (fuelTank < 5) {
						System.out.println(getName() + " of the team " + team.getName().toUpperCase() + " has to refuel the car!");
						
						if (team.getBox().isFree()) {
							synchronized (this) {								
								team.getBox().setPilot(this);
								
								while (fuelTank != MAX_TANK) {	
									System.out.println(getName() + " is waiting the refuel.");
									wait();
								}
								team.getBox().setPilotOut();
							}
						} else {
							System.out.println(getName() + " tried to enter the box. Gonna do another lap. ---------------------------- \n");
						}
					}
					
					time += totalTime;
					
					raceStatus.lap(this);
				} else {
					
				}
				System.out.println(name.toUpperCase() + " HAS MADE A LAP. Still has " + fuelTank + " fuel left. Go go gooo!!! \n");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
