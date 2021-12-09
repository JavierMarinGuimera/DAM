package orders;

public class Restaurant {
	
	private static final int TOTAL_ORDERS = 5;

	public static void main(String[] args) throws InterruptedException {
		OrderBoard orderBoard = new OrderBoard();
		
		// Second number increase the speed of the thread. The higher number, the faster
		Waiter waiter = new Waiter("Javi", 3, orderBoard, TOTAL_ORDERS);
		Cooker cooker = new Cooker("Toni", 8, orderBoard, TOTAL_ORDERS);
		
		Thread waiterThread = new Thread(waiter);
		Thread cookerThread = new Thread(cooker);
		
		waiterThread.start();
		cookerThread.start();
		
		waiterThread.join();
		cookerThread.join();
		
		System.out.println("Program ended!");
	}

}
