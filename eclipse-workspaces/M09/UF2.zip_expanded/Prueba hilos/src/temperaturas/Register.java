package temperaturas;

public class Register {
	
	private int maxTemp;

	public synchronized int getMaxTemp() {
		return maxTemp;
	}

	public synchronized void setMaxTemp(int maxTemp) {
		if (this.maxTemp < maxTemp) {
			this.maxTemp = maxTemp;
		}
	}
	
	
}
