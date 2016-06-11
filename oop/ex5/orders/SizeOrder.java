package oop.ex5.orders;

import java.io.File;

/**
 * The Size Order sort files by their size.
 * @author Idan Refaeli
 */
public class SizeOrder implements Order {
	
	// Used to determine the order between 2 files in case they have the same size:
	private AbsOrder absOrder = new AbsOrder();

	/* (non-Javadoc)
	 * @see orders.Order#compare(java.io.File, java.io.File)
	 */
	@Override
	public int compare(File f1, File f2) {
		long size1 = f1.length();
		long size2 = f2.length();
		
		if(size1 < size2) {
			return -1;
		}
		else if(size1 > size2) {
			return 1;
		}
		else {
			// If the 2 files have the same size, determine their order by the AbsOrder.
			return absOrder.compare(f1, f2);
		}
	}

}
