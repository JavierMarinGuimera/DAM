package orders;

public class Waiter implements Runnable {

	private String name;
	private int speed;
	private OrderBoard orderBoard;
	private int totalOrders;

	public Waiter(String name, int speed, OrderBoard orderBoard, int totalOrders) {
		super();
		this.name = name;
		this.speed = speed;
		this.orderBoard = orderBoard;
		this.totalOrders = totalOrders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public OrderBoard getOrderBoard() {
		return orderBoard;
	}

	public void setOrderBoard(OrderBoard orderBoard) {
		this.orderBoard = orderBoard;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < totalOrders; i++) {
				orderBoard.enqueueOrder("Order " + (i + 1));
				Thread.sleep(5000 - (speed * 500));
			}
		} catch (InterruptedException e) {
			
		}
	}
}
