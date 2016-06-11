package oop.ex5.filters;

/**
 * Exception when the filter format is bad.
 * @author Idan Refaeli
 */
public class WrongFilterFormatException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Initiates the exception.
	 */
	public WrongFilterFormatException() {
		super();
	}

}
