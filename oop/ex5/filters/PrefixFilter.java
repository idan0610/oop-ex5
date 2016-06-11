package oop.ex5.filters;

import java.io.File;

/**
 * The Prefix filter checks if the given string is the prefix of a file name.
 * @author Idan Refaeli
 */
public class PrefixFilter implements Filter {

	private String s;
	
	/**
	 * Initiates the filter with the given string to check.
	 * @param s The given string.
	 */
	public PrefixFilter(String s) {
		this.s = s;
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(f.getName().startsWith(s)) {
			return true;
		}
		
		return false;
	}

}
