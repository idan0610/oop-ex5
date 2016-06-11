package oop.ex5.parser;

/**
 * Exception when the command file format is wrong.
 * @author Idan Refaeli
 */
public class WrongFormatCommandsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Initiates the Exception.
	 */
	public WrongFormatCommandsException() {
		super();
	}
}
