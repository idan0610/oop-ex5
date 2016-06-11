package oop.ex5.filters;

import java.io.File;

/**
 * The Executable filter checks if a file's writing permissions is the same as the one given.
 * @author Idan Refaeli
 */
public class ExecutableFilter implements Filter {

	private boolean hasPermission;
	
	/**
	 * Initiates the filter with the given boolean to compare.
	 * @param hasPermission true means have writing permissions, false otherwise.
	 * @throws WrongFilterFormatException 
	 */
	public ExecutableFilter(String hasPermission) throws WrongFilterFormatException {
		// The given string hasPermission must be "YES" or "NO". Otherwise an exception will be thrown.
		if(YES.equals(hasPermission)) {
			this.hasPermission = true;
		}
		else if(NO.equals(hasPermission)) {
			this.hasPermission = false;
		}
		else {
			throw new WrongFilterFormatException();
		}
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(f.canExecute() == hasPermission) {
			return true;
		}
		
		return false;
	}

}
