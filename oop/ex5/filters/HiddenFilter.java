package oop.ex5.filters;

import java.io.File;

/**
 * The Hidden filter check if a file hidden status is the same as the one given.
 * @author Idan Refaeli
 */
public class HiddenFilter implements Filter {

	private boolean isHidden;
	
	/**
	 * Initiates the filter with the given boolean to compare.
	 * @param isHidden true means hidden, false otherwise.
	 * @throws WrongFilterFormatException 
	 */
	public HiddenFilter(String isHidden) throws WrongFilterFormatException {
		// The given string isHidden must be "YES" or "NO". Otherwise an exception will be thrown.
		if(YES.equals(isHidden)) {
			this.isHidden = true;
		} 
		else if(NO.equals(isHidden)) {
			this.isHidden = false;
		}
		else {
			throw new WrongFilterFormatException();
		}
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(f.isHidden() == isHidden) {
			return true;
		}
		
		return false;
	}

}
