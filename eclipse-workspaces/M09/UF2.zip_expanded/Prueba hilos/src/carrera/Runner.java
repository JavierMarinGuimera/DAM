package carrera;

public class Runner implements Runnable {
	
	private String name;

	public Runner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			int endDiff = 995 + (int) (Math.random() * 10);
			Thread.sleep(endDiff);
			Race.scoreboard.insertRunner(this.getName(), Race.scoreboard.convertTime(endDiff));
		} catch (InterruptedException e) {
		}
	}
}