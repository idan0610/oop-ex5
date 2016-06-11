package oop.ex5.filters;

import java.io.File;

/**
 * The Contains filter checks if the given string is contained in a file name.
 * @author Idan Refaeli
 */
public class ContainsFilter implements Filter {

	private String s;
	
	/**
	 * Initiates the filter with the given string to check.
	 * @param s The given string
	 */
	public ContainsFilter(String s) {
		this.s = s;
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(f.getName().contains(s)) {
			return true;
		}
		
		return false;
	}

}
