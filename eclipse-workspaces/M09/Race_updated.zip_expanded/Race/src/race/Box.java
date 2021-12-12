package race;

public class Box implements Runnable {
	private RaceStatus raceStatus;
	private Pilot pilotInBox;

	public Box(RaceStatus raceStatus, Pilot pilotInBox) {
		super();
		this.raceStatus = raceStatus;
		this.pilotInBox = pilotInBox;
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
			System.out.println(pilot.getName() + " entered the box.");
			this.pilotInBox = pilot;
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public synchronized void setPilotOut() {
		System.out.println(pilotInBox.getName() + " just left the box. Go go goooooo!!!");
		this.pilotInBox = null;
		notify();
	}

	public boolean isFree() {
		if (this.pilotInBox == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			while (!raceStatus.isFinish()) {
				try {
					// Waiting for the box to be occupied.
					while (this.isFree() && !raceStatus.isFinish()) {
						wait();
					}
					
					// If the race is finished, the thread has to finish too.
					if (raceStatus.isFinish()) {
						break;
					}
					
					// Refuel the pilot to continue the race.
					pilotInBox.refuel();
					
					// Waiting the pilot to leave the box.
					while (!isFree()) {
						wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
