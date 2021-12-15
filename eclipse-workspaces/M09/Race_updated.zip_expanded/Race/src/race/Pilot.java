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
		System.out.println(this + " refuelled!!!");
	}

	@Override
	public void run() {
		try {
			int totalTime;
			while (laps > 0 && !raceStatus.isFinish()) {
				if (laps > 0) {					
					totalTime = MINUT + (int) (Math.random() * MINUT_DIFF);
					Thread.sleep(totalTime);
					
					fuelTank -= (int) (Math.random() * 7);
					
					if (fuelTank < 5) {
						System.out.println(this + " need refueling.");
						
						Box box = team.getBox();
						
						if (box.isFree()) {
							System.out.println(this + " getting into the box.");
							
							synchronized (this) {							
								box.setPilot(this);
								
								while (fuelTank != MAX_TANK) {	
									System.out.println(this + " is waiting for refueling...");
									
									synchronized (box) {										
										box.notify();
									}
									
									this.wait();
								}
								
								box.setPilotOut();
								
								synchronized (box) {										
									box.notify();
								}
								
								totalTime += 500;
							}
						} else {
							System.out.println(this + " BOX BUSY!!");
						}
					}
					
					time += totalTime;
					
					raceStatus.lap(this);
					
					System.out.println(this + " HAS MADE A LAP. Still has " + fuelTank + " fuel left. Go go gooo!!! \n");
				} else {
					laps--;
					
					System.out.println("Finished the race but its still racing. Has made " + -(laps) + " extra laps!");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Pilot: [" + name + "(" + team.getName() + ") laps=" + (laps > 0 ? laps : -(laps)) + " fuel=" + fuelTank + "]";
	}

}
