package oop.ex5.filters;

import java.io.File;

/**
 * The File filter checks if a file name equals the given name.
 * @author Idan Refaeli
 *
 */
public class FileFilter implements Filter {
	
	private String s;
	
	/**
	 * Initiates the filter with the given name to compare.
	 * @param s The given name
	 */
	public FileFilter(String s) {
		this.s = s;
	}
	
	/* (non-Javadoc)
	 * @see filters.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File f) {
		
		if(s.equals(f.getName())) {
			return true;
		}
		
		return false;
	}

}
