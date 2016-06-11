package oop.ex5.orders;

import java.io.File;

/**
 * The Type Order sort files by their type.
 * @author Idan Refaeli
 */
public class TypeOrder implements Order {

	// Used to determine the order between 2 files in case they have the same type:
	private AbsOrder absOrder = new AbsOrder();
	
	private static final String DOT =  ".";
	/* (non-Javadoc)
	 * @see orders.Order#compare(java.io.File, java.io.File)
	 */
	public int compare(File f1, File f2) {
		String fileName1 = f1.getName();
		String fileName2 = f2.getName();
		
		// Find the type of each file.
		String type1 = fileName1.substring(fileName1.lastIndexOf(DOT)+1, fileName1.length());
		String type2 = fileName2.substring(fileName2.lastIndexOf(DOT)+1, fileName2.length());
		
		int compare = type1.compareTo(type2);
		
		if(compare != 0) {
			return compare;
		}
		else {
			// If the 2 files have the same type, determine their order by the AbsOrder.
			return absOrder.compare(f1, f2);
		}
	}

}
