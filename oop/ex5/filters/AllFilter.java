package oop.ex5.filters;

import java.io.File;

/**
 * The All filter match all files.
 * @author Idan Refaeli
 */
public class AllFilter implements Filter {

	/**
	 * Initiates the filter.
	 */
	public AllFilter() { }
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		return true;
	}

}
