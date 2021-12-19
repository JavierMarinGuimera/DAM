package race;

public class Pilot implements Runnable, Comparable<Pilot> {
	private static final int MINUT = 1000;
	private static final int MINUT_DIFF = 1500;
	private static final int HALF_MINUT = 500;

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
				totalTime = MINUT + (int) (Math.random() * MINUT_DIFF);
				Thread.sleep(totalTime);

				fuelTank -= (int) (Math.random() * 7);

				if (fuelTank < 5 && !raceStatus.isFinish()) {
					System.out.println(this + " need refueling.");

					Box box = team.getBox();

					Boolean canIGetIntheBox;

					synchronized (box) {
						canIGetIntheBox = box.isFree();
						if (canIGetIntheBox) {
							System.out.println(this + " getting into the box.");
							box.setPilot(this);
						}
					}

					if (canIGetIntheBox) {

						synchronized (this) {

							synchronized (box) {
								box.notify();
							}

							while (fuelTank != MAX_TANK) {
								System.out.println(this + " is waiting for refueling...");
								this.wait();
							}

							synchronized (box) {
								box.setPilotOut();
								box.notify();
							}

							totalTime += HALF_MINUT + (int) (Math.random() * HALF_MINUT);
						}
					} else {
						System.out.println(this + " BOX BUSY!!");
					}
				}

				time += totalTime;

				raceStatus.lap(this);

				System.out.println(this + " HAS MADE A LAP. Still has " + fuelTank + " fuel left. Go go gooo!!! \n");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Pilot: [" + name + "(" + team.getName() + ") laps=" + (laps > 0 ? laps : -(laps)) + " fuel=" + fuelTank
				+ "]";
	}

	@Override
	public int compareTo(Pilot pilot) {
		    if (laps == pilot.laps) {
		      return ((Integer) time).compareTo(pilot.time);
		    } else {
		      return ((Integer) laps).compareTo(pilot.laps);
		    }
	}

}
