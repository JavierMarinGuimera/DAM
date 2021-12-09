package temperaturas;

public class Hilo implements Runnable{
	
	private String name;
	private int start;
	private int end;

	public Hilo(String name, int start, int end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		int[] temps = Temperaturas.temps;
		int maxTemp = temps[0];
		for (int i = this.start; i < this.end; i++) {
			if(maxTemp < temps[i]) {
				maxTemp = temps[i];
			}
		}
		Temperaturas.getRegister().setMaxTemp(maxTemp);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
