package oop.ex5.filescript;

import java.io.File;

import oop.ex5.manager.Manager;

/**
 * This class runs the program responsible for filtering and ordering files on the files system,
 * as specified by the source directory and command file given as arguments.
 * @author Idan Refaeli
 */
public class MyFileScript {

	private final static int NUM_OF_ARGUMENTS = 2;
	private final static int SOURCE_DIR_ARGUMENT = 0;
	private final static int COMMAND_FILE_ARGUMENT = 1;
	private final static String ERROR = "ERROR";
	
	/**
	 * The main method, runs the entire program based on the given arguments.
	 * @param args The source directory and command file.
	 */
	public static void main(String[] args) {
		if(args.length != NUM_OF_ARGUMENTS) {
			// If the number of arguments supplied isn't 2, then print "ERROR".
			// Only 2 arguments allowed.
			System.err.println(ERROR);
		}
		else {
			// Keep the source directory and command file given as arguments on File objects.
			File sourceDir = new File(args[SOURCE_DIR_ARGUMENT]);
			File commandFile = new File(args[COMMAND_FILE_ARGUMENT]);

			// Try to filter and order the files based on the commands from command file using the
			// manger, if something goes wrong (I/O errors for example) print "ERROR".
			try {
				oop.ex5.manager.Manager manager = new Manager(sourceDir, commandFile);
				manager.runFileScript();
			} catch (Exception e) {
				System.err.println(ERROR);
			}
			
			return;
		}

	}

}
