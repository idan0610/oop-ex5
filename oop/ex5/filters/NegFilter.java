package oop.ex5.filters;

import java.io.File;

/**
 * The Negative filter satisfies a file not satisfied by the given filter.
 * @author Idan Refaeli
 */
public class NegFilter implements Filter {

	private Filter filter;
	
	/**
	 * Initiates the filter with the given filter.
	 * @param filter The given filter.
	 */
	public NegFilter(Filter filter) {
		this.filter = filter;
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		if(filter.isPass(f)) {
			return false;
		}
		
		return true;
	}

}
