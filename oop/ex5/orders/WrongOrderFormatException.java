package oop.ex5.orders;

/**
 * Exception when the order format is bad.
 * @author Idan Refaeli
 */
public class WrongOrderFormatException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Initiates the exception.
	 */
	public WrongOrderFormatException() {
		super();
	}
}
