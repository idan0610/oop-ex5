package oop.ex5.orders;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

/**
 * The Reverse Order gets an Order object and reverse the order of the files set by that Order.
 * @author Idan Refaeli
 */
public class ReverseOrder implements Order {

	private Order order;
	
	/**
	 * Initiates the ReverseOrder with the given Order received.
	 * @param order
	 */
	public ReverseOrder(Order order) {
		this.order = order;
	}
	
	/* (non-Javadoc)
	 * @see orders.Order#compare(java.io.File, java.io.File)
	 */
	@Override
	public int compare(File f1, File f2) {
		// Use reverseOrder() to reverse the order of the files setting the Order object.
		Comparator<File> reverse = Collections.reverseOrder(order);
		
		return reverse.compare(f1, f2);
	}

}
