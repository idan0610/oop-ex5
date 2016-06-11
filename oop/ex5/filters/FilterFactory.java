package oop.ex5.filters;

/**
 * The factory is used to initiate a Filter object according to given argument.
 * @author Idan Refaeli
 */
public class FilterFactory {

	private static final String ARGUMENT_SEPERATOR = "#";
	private static final int MAX_NUM_OF_ARGUMENTS = 4;
	private static final int NO_SEPERATOR = -1;
	private static final int FIRST_INDEX = 0;
	private static final int FIRST_ARGUMENT = 0;
	private static final int SECOND_ARGUMENT = 1;
	private static final int THIRD_ARGUMENT = 2;
	private static final int FORTH_ARGUMENT = 3;
	
	private static final String GREATER_THAN = "greater_than";
	private static final String BETWEEN = "between";
	private static final String SMALLER_THAN = "smaller_than";
	private static final String FILE = "file";
	private static final String CONTAINS= "contains";
	private static final String PREFIX = "prefix";
	private static final String SUFFIX = "suffix";
	private static final String WRITABLE = "writable";
	private static final String EXECUTABLE = "executable";
	private static final String HIDDEN = "hidden";
	private static final String ALL = "all";
	private static final String NOT = "NOT";
	
	private static String[] findArguments(String filterCommand) {
		// Used to extract all arguments from the filter command received in createFilter().
		
		int seperatorIndex;
		int firstIndexArgumant = FIRST_INDEX;
		String[] arguments = new String[MAX_NUM_OF_ARGUMENTS];
		for(int i = 0; i < MAX_NUM_OF_ARGUMENTS; i++) {	
			// Find each '#' symbol and keep the substring of the command from the beginning until '#'
			// in arguments. If no '#' was found, only 1 argument remained, keep the whole in arguments
			// and break.
			seperatorIndex = filterCommand.indexOf(ARGUMENT_SEPERATOR);
			if(seperatorIndex != NO_SEPERATOR) {
				arguments[i] = filterCommand.substring(firstIndexArgumant, seperatorIndex);
				filterCommand = filterCommand.substring(seperatorIndex+1, filterCommand.length());
			}
			else {
				arguments[i] = filterCommand;
				break;
			}
		}
		
		// After all the arguments are in place, move the "NOT" argument to the forth index on arguments[].
		switch(arguments[0]) {
		case BETWEEN:
			// The "NOT" argument in case of "BETWEEN" is already on the forth index, so do nothing.
			break;
		case ALL:
			arguments[FORTH_ARGUMENT] = arguments[SECOND_ARGUMENT];
			break;
		default:
			arguments[FORTH_ARGUMENT] = arguments[THIRD_ARGUMENT];
		}
		
		return arguments;
	}
	
	/**
	 * Initiates a new Filter object as the one requested by the given argument.
	 * @param filterCommand The filter command, containing which filter to initiate and its arguments.
	 * @return The filter
	 * @throws WrongFilterFormatException
	 */
	public static Filter createFilter(String filterCommand) throws WrongFilterFormatException {
		
		// Put all the arguments from filterCommand and put them in array.
		String[] arguments = findArguments(filterCommand);
		
		String filterName = arguments[FIRST_ARGUMENT];
		Filter filter = null;
		
		// Initiate a new filter depends on the given filter name.  If one of the arguments is wrong, an
		// exception will be thrown and the process will stop.
		switch(filterName) {
		case GREATER_THAN:
			filter = new GreaterThenFilter(arguments[SECOND_ARGUMENT]);
			break;
		case BETWEEN:
			filter = new BetweenFilter(arguments[SECOND_ARGUMENT], arguments[THIRD_ARGUMENT]);
			break;
		case SMALLER_THAN:
			filter = new SmallerThenFilter(arguments[SECOND_ARGUMENT]);
			break;
		case FILE:
			filter = new FileFilter(arguments[SECOND_ARGUMENT]);
			break;
		case CONTAINS:
			filter = new ContainsFilter(arguments[SECOND_ARGUMENT]);
			break;
		case PREFIX:
			filter = new PrefixFilter(arguments[SECOND_ARGUMENT]);
			break;
		case SUFFIX:
			filter = new SuffixFilter(arguments[SECOND_ARGUMENT]);
			break;
		case WRITABLE:
			filter = new WritableFilter(arguments[SECOND_ARGUMENT]);
			break;
		case EXECUTABLE:
			filter = new ExecutableFilter(arguments[SECOND_ARGUMENT]);
			break;
		case HIDDEN:
			filter = new HiddenFilter(arguments[SECOND_ARGUMENT]);
			break;
		case ALL:
			filter = new AllFilter();
			break;
		}
		
		if(filter == null) {
			// If the filter name is wrong, as no filter was created, throw an exception.
			throw new WrongFilterFormatException();
		}
		
		// If the forth argument (supposed to be NOT if exist) is not null, and if it used correctly,
		// use the NegFilter, otherwise throw an exception.
		if(arguments[FORTH_ARGUMENT] != null) {
			if(arguments[FORTH_ARGUMENT].equals(NOT)) {
				// If the NOT argument received, use NegFilter to initiate the negative filter.
				filter = new NegFilter(filter);
			}
			else {
				throw new WrongFilterFormatException();
			}
		}
		
		return filter;
		
	}
	
}
