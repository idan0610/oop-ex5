package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;

/**
 * This interface describes the general API of Order objects.
 * Each order is actually a comparator.
 * @author Maayan
 */
public interface Order extends Comparator<File> {
	
	/**
	 * Used to compare between 2 files by the requested order.
	 */
	public int compare(File f1, File f2);
	
}
