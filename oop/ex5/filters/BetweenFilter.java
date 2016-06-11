package oop.ex5.filters;

import java.io.File;

/**
 * The Between Filter check if a file size is between (inclusive) the given numbers (in k-bytes).
 * @author Idan Refaeli
 */
public class BetweenFilter implements Filter {
	
	private double minSize;
	private double maxSize;
	
	/**
	 * Initiates the filter with the given numbers to compare.
	 * @param minSize The minimum size
	 * @param maxSize The maximum size
	 * @throws WrongFilterFormatException 
	 */
	public BetweenFilter(String minSize, String maxSize) throws WrongFilterFormatException {
		this.minSize = Double.parseDouble(minSize);
		this.maxSize = Double.parseDouble(maxSize);
		if(this.minSize < 0 || this.maxSize < 0 || this.maxSize < this.minSize) {
			// minSize and maxSize must be non-negative double numbers, and minSize must be smaller or equal
			// to maxSize.
			throw new WrongFilterFormatException();
		}
	}

	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		if(f.length()/BYTES_IN_KB >= minSize && f.length()/BYTES_IN_KB <= maxSize) {
			return true;
		}
		
		return false;
	}

}
