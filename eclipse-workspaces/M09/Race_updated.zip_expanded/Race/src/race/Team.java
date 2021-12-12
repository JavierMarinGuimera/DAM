package race;

import java.util.List;

public class Team {
	private String name;
	private Box box;
	private List<Pilot> pilots;
	
	public Team(String name, List<Pilot> teamPilots, RaceStatus raceStatus) {
		super();
		this.name = name;
		
		Box box = new Box(raceStatus, null);
		
		this.box = box;
		this.pilots = teamPilots;
		
		for (int i = 0; i < teamPilots.size(); i++) {			
			teamPilots.get(i).setTeam(this);
		}
		
		Thread boxThread = new Thread(this.box);
		boxThread.setName(name + " box");
		boxThread.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}
	
	
}
