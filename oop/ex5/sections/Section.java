package oop.ex5.sections;

import java.util.ArrayList;

import oop.ex5.filters.Filter;
import oop.ex5.orders.Order;

/**
 * Represents a section from the commands file, keeping its filter and order.
 * @author Idan Refaeli
 */
public class Section {
	
	private Filter filter;
	private Order order;
	private ArrayList<String> warnings;
	
	/**
	 * Initiates the section with the given filter and order.
	 * @param filter The filter
	 * @param order The order
	 */
	public Section(Filter filter, Order order, ArrayList<String> warnings) {
		this.filter = filter;
		this.order = order;
		this.warnings = warnings;
	}

	/**
	 * @return The filter
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * @return The order
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * @return The warning list
	 */
	public ArrayList<String> getWarnings() {
		return warnings;
	}
}
