package race;

public class Box implements Runnable {
	private String teamName;
	private RaceStatus raceStatus;
	private Pilot pilotInBox;

	public Box(String name, RaceStatus raceStatus, Pilot pilotInBox) {
		super();
		this.teamName = name;
		this.raceStatus = raceStatus;
		this.pilotInBox = pilotInBox;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public RaceStatus getRaceStatus() {
		return raceStatus;
	}

	public void setRaceStatus(RaceStatus raceStatus) {
		this.raceStatus = raceStatus;
	}

	public Pilot getPilotInBox() {
		return pilotInBox;
	}

	public void setPilotInBox(Pilot pilotInBox) {
		this.pilotInBox = pilotInBox;
	}

	public synchronized void setPilot(Pilot pilot) {
		try {
			System.out.println(this + pilot.toString() + " is in the box.");
			this.pilotInBox = pilot;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized void setPilotOut() {
		System.out.println(pilotInBox + " leaving the box.");
		this.pilotInBox = null;
	}

	public synchronized boolean isFree() {
		if (this.pilotInBox == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run() {
		System.out.println(String.format("%-" + Race.PADDING_TO_CENTER + "s %s", "", "BOX(" + teamName + ") started!"));

		while (!raceStatus.isFinish()) {
			try {
				// Waiting for the box to be occupied.
				synchronized (this) {
					while (this.isFree() && !raceStatus.isFinish()) {
						System.out.println(
								String.format("%-" + Race.PADDING_TO_CENTER + "s %s", "", this + " is free. \n"));
						wait();
					}
				}

				// If the race is finished, the thread has to finish too.
				if (raceStatus.isFinish()) {
					break;
				}

				// Refuel and notify the pilot to continue the race.
				System.out.println(this + pilotInBox.toString() + " refuel.");
				pilotInBox.refuel();

				synchronized (pilotInBox) {
					pilotInBox.notify();
				}

				// Waiting the pilot to leave the box.
				synchronized (this) {					
					while (!isFree()) {
						System.out.println(this + " waiting for the pilot: " + pilotInBox + " to leave the Box.");
						wait();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "BOX(" + this.teamName + "): ";
	}
}
