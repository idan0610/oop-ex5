package oop.ex5.filters;

import java.io.File;

/**
 * The Greater Than filter checks if the file size is strictly greater than the given number of k-bytes.
 * @author Idan Refaeli
 */
public class GreaterThenFilter implements Filter {
	
	private double size;
	
	/**
	 * Initiates the filter with the given size to compare
	 * @param size The given size
	 * @throws WrongFilterFormatException
	 */
	public GreaterThenFilter(String size) throws WrongFilterFormatException {
		this.size = Double.parseDouble(size);
		if(this.size < 0) {
			// size must be non-negative double number.
			throw new WrongFilterFormatException();
		}
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(f.length()/BYTES_IN_KB > size) {
			return true;
		}
		
		return false;
	}

}
