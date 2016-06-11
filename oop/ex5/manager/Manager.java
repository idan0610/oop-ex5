package oop.ex5.manager;

import java.io.*;
import java.util.*;

import oop.ex5.filters.Filter;
import oop.ex5.orders.Order;
import oop.ex5.parser.*;
import oop.ex5.sections.Section;

/**
 * This class is the manager for the entire program. It calls the parser to parse the given command file
 * then using the Section object composes their Filter and Order objects each to traverse the files on
 * the source directory given, filter them and print them on the relevant order.
 * @author Idan Refaeli
 */
public class Manager {

	private File sourceDir;
	private File commandFile;
	private Parser parser;
	private ArrayList<Section> sections;
	
	/**
	 * Initiates the Manager with the given source directory and command file File objects given.
	 * @param sourceDir The source directory
	 * @param commandFile The command file
	 * @throws IOException
	 */
	public Manager(File sourceDir, File commandFile) throws IOException {
		this.sourceDir = sourceDir;
		this.commandFile = commandFile;
		
		// Initiate the Parser object with the given command file.
		this.parser = new Parser(this.commandFile);
	}
	
	/**
	 * Call the Parser to parse the command file, and then call filterAndOrderFiles() to filter and
	 * order the files located on the source directory according to the sections returned from the parser.
	 * @throws WrongFormatCommandsException
	 * @throws IOException
	 */
	public void runFileScript() throws WrongFormatCommandsException, IOException {
		sections = parser.parseFile();
		parser.closeBuffer();
		
		filterAndOrderFiles();
	}
	
	/**
	 * Used to filter and order the files on the source directory according to the sections's filters
	 * and orders.
	 */
	private void filterAndOrderFiles() {
		// Use listFiles to get a list of all files and directories on sourceDir.
		File[] filesList = sourceDir.listFiles();
		Filter filter = null;
		Order order = null;
		
		ArrayList<File> filteredFiles; // The files passes the filter on each section
		ArrayList<String> warnings; // The warnings of each section
		
		for(Section section : sections) {
			// For each section, reset the fileteredFiles list, and take the filter, order and warnings
			// of the section.
			filteredFiles = new ArrayList<>();
			
			filter = section.getFilter();
			order = section.getOrder();
			warnings = section.getWarnings();
			
			// First print all the warnings of the section.
			for(String warning : warnings) {
				System.err.println(warning);
			}
			
			for(File file : filesList) {
				// For each file and directory on filesList, if it is a directory, ignore it.
				// Otherwise, if it is a file and it passes the filter, add it to filteredFiles.
				if(file.isDirectory()) {
					continue;
				}
				
				if(filter.isPass(file)) {
					filteredFiles.add(file);
				}
			}
			
			// Sort the filtered files using the Order object of the section.
			Collections.sort(filteredFiles, order);
			
			// Print the filtered files on the relevant order requested.
			for(File file : filteredFiles) {
				System.out.println(file.getName());
			}
		}
	}
}
