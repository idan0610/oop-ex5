package oop.ex5.orders;

import java.io.File;

/**
 * The Abs Order sort files by their absolute name.
 * @author Idan Refaeli
 */
public class AbsOrder implements Order {
	
	/* (non-Javadoc)
	 * @see orders.Order#compare(java.io.File, java.io.File)
	 */
	public int compare(File f1, File f2) {
		String absName1 = f1.getAbsolutePath();
		String absName2 = f2.getAbsolutePath();
		return absName1.compareTo(absName2);
	}

}
