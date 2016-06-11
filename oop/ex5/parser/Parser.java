package oop.ex5.parser;

import java.io.*;
import java.util.ArrayList;

import oop.ex5.filters.*;
import oop.ex5.orders.*;
import oop.ex5.sections.Section;

/**
 * This class represents a Parser used to parse over a given commands file and divide it to Sections.
 * @author Idan Refaeli
 * @see oop.ex5.sections.Section
 */
public class Parser {
	
	private static final String FILTER = "FILTER";
	private static final String ORDER = "ORDER";
	private static final int FIRST_LINE = 1;
	private static final int MAX_NUM_OF_SECTION_LINES = 4;
	private static final String WARNING_IN_LINE = "Warning in line ";
	private static final String DEFAULT_FILTER_COMMAND = "all";
	private static final String DEFAULT_ORDER_COMMAND = "abs";
	private static final int FIRST_ITEATION = 0;
	private static final int SECOND_ITEATION = 1;
	private static final int THIRD_ITEATION = 2;
	private static final int FORTH_ITEATION = 3;
	
	private File commandFile;
	private BufferedReader bufferedReader;
	private String currentLine;
	private int line;
	private ArrayList<String> warnings;
	
	
	/**
	 * Initiates the Parse with the given command file. Also initiates a new bufferedReader object, and set
	 * currentLine as the first line, and line counter as 1.
	 * @param commandFile The command file
	 * @throws IOException 
	 */
	public Parser(File commandFile) throws IOException {
		this.commandFile = commandFile;
		bufferedReader = new BufferedReader(new FileReader(this.commandFile));
		currentLine = bufferedReader.readLine();
		line = FIRST_LINE;
		warnings = new ArrayList<>();
	}
	
	// Used to add a warning to the warning list with the corresponding line from the commands file.
	private void addWarning() {
		warnings.add(WARNING_IN_LINE + line);
	}
	
	/**
	 * Used to read 1 section from the commands file, and create its Section object with its Filter and Order.
	 * @return The Section
	 * @throws WrongFormatCommandsException
	 * @throws IOException
	 */
	private Section readSection() throws WrongFormatCommandsException, IOException {
		Filter filter = null;
		Order order = null;
		String filterCommand = DEFAULT_FILTER_COMMAND, orderCommand = DEFAULT_ORDER_COMMAND;
		
		// The for loop runs for 4 iterations.
		// On the first iteration it checks if the command "FILTER" is correct
		// On the second iteration it checks if there is a filter command, and creates the Filter object.
		// On the third iteration it checks if the command "ORDER" is correct
		// On the forth iteration it checks if there is an order command, and creates the Order object.
		for(int i = 0; i < MAX_NUM_OF_SECTION_LINES; i++) {
			switch(i) {
			case FIRST_ITEATION:
				// If the section does not contain the command "FILTER", throw an exception.
				// Otherwise, proceed to next line.
				if(!FILTER.equals(currentLine)) {
					throw new WrongFormatCommandsException();
				}
				currentLine = bufferedReader.readLine();
				line++;
				break;
				
			case SECOND_ITEATION:
				// First check there is a filter command. If not, add warning, but don't proceed to the next
				// line because the current line is the "ORDER" command.
				// Otherwise, keep the filter command and proceed to the next line.
				if(ORDER.equals(currentLine)) {
					addWarning();
				}
				else {
					filterCommand = currentLine;
				}
				
				// Try to create the filter with the current filter command.
				// If something in the command is wrong, add warning and create the default all filter.
				try {
					filter = FilterFactory.createFilter(filterCommand);
				}
				catch(WrongFilterFormatException e) {
					addWarning();
					filter = new AllFilter();
				}
				
				if(!ORDER.equals(currentLine)) {
					currentLine = bufferedReader.readLine();
					line++;
				}
				break;
				
			case THIRD_ITEATION:
				// If the section does not contain the command "ORDER", throw an exception.
				// Otherwise, proceed to the next line.
				if(!ORDER.equals(currentLine)) {
					throw new WrongFormatCommandsException();
				}
				currentLine = bufferedReader.readLine();
				line++;
				break;
				
			case FORTH_ITEATION:
				// First check there is an order command. If not, don't proceed to the next line because the
				// current line is the "FILTER" command.
				// Otherwise, keep the order command and proceed to the next line.
				if(!FILTER.equals(currentLine) && currentLine != null) {
					orderCommand = currentLine;
				}
				
				// Try to create the order with the current order command.
				// If something in the command is wrong, add warning and create the default abs order.
				try {
					order = OrderFactory.createOrder(orderCommand);
				}
				catch(WrongOrderFormatException e) {
					addWarning();
					order = new AbsOrder();
				}
				
				if(!FILTER.equals(currentLine)) {
					currentLine = bufferedReader.readLine();
					line++;
				}
				break;
			}
		}
		
		// Return the section composes the new filter and order.
		Section section = new Section(filter, order, warnings);
		resetWarnings(); // Reset the warning list for the next section
		return section;
	}
	
	/**
	 * Parse the command file and create Section objects for each section.
	 * @return ArrayList of Section, containing the sections.
	 * @throws WrongFormatCommandsException
	 * @throws IOException
	 */
	public ArrayList<Section> parseFile() throws WrongFormatCommandsException, IOException {
		ArrayList<Section> sections = new ArrayList<>();
		
		while(currentLine !=  null) {
			sections.add(readSection());
		}
		
		return sections;
	}
	
	/**
	 * Used to reset the warnings list.
	 */
	private void resetWarnings() {
		warnings = new ArrayList<>();
	}
	
	/**
	 * Used to close the Scanner object at the end.
	 * @throws IOException 
	 */
	public void closeBuffer() throws IOException {
		bufferedReader.close();
	}
	
}
