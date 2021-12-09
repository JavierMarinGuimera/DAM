package orders;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OrderBoard {
	
	private static final String SEPARATOR = "------------------------------";
	
	private Queue<String> orders;

	public OrderBoard() {
		orders = new LinkedList<>();
	}

	public boolean hasOrders() {
		return !orders.isEmpty();
	}

	public synchronized String dequeueOrder() throws InterruptedException {
		
		while(!hasOrders()) {
			wait();
		}
		
		String order = orders.poll();
		System.out.println("\n" + SEPARATOR + "The COOKER completed an order." + SEPARATOR);
		System.out.println("Orders \"" + order + "\" dequeued. Queue contains " + Arrays.toString(orders.toArray()));
		return order;
	}

	public synchronized void enqueueOrder(String order) {
		orders.offer(order);
		System.out.println("\n" + SEPARATOR + "The WAITER received an order!" + SEPARATOR);
		System.out.println("Orders \"" + order + "\" enqueued. Queue contains " + Arrays.toString(orders.toArray()));
		notify();
	}
}