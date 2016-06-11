package oop.ex5.filters;

import java.io.File;

/**
 * This interface describe the general API of a filter.
 * @author Idan Refaeli
 */
public interface Filter {
	
	public static final double BYTES_IN_KB = 1024.0;
	public static final String YES = "YES";
	public static final String NO = "NO";
	
	/**
	 * Check if the given file passes the filter.
	 * @param f The given file
	 * @return True if the given file passes the filter.
	 */
	public boolean isPass(File f);
	
}
