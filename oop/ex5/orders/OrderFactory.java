package oop.ex5.orders;

/**
 * The factory is used to initiate an Order object according to given argument.
 * @author Idan Refaeli
 */
public class OrderFactory {

	private final static String ARGUMENT_SEPERATOR = "#";
	private final static int FIRST_INDEX = 0;
	private final static int NO_ARGUMENT = -1;
	
	private final static String ABS = "abs";
	private final static String TYPE = "type";
	private final static String SIZE = "size";
	private final static String REVERSE = "REVERSE";
	
	/**
	 * Initiates a new Order object as the one requested by the given argument.
	 * @param orderCommand The order command, containing which order to initiate.
	 * @return The order.
	 * @throws WrongOrderFormatException
	 */
	public static Order createOrder(String orderCommand) throws WrongOrderFormatException {
		
		// Put the arguments of the order command on variables.
		int seperatorIndex = orderCommand.indexOf(ARGUMENT_SEPERATOR);
		String orderName = null;
		String orderArgument = null;
		
		if(seperatorIndex != NO_ARGUMENT) {
			// Only if there is a second argument (REVERSE)
			orderName = orderCommand.substring(FIRST_INDEX, seperatorIndex);
			orderArgument = orderCommand.substring(seperatorIndex+1, orderCommand.length());
		}
		else {
			// Only one argument exist (the name).
			orderName = orderCommand;
		}
		
		Order order = null;
		
		// Initiate a new order depends on the given order name.  If one of the arguments is wrong, an
		// exception will be thrown and the process will stop.
		switch(orderName) {
		case ABS:
			order = new AbsOrder();
			break;
		case TYPE:
			order = new TypeOrder();
			break;
		case SIZE:
			order = new SizeOrder();
			break;
		}
		
		if(order == null) {
			throw new WrongOrderFormatException();
		}
		
		// If the second argument (supposed to be REVERSE if exist) is not null, and if it used correctly,
		// use the ReverseOrder, otherwise throw an exception.
		if(orderArgument != null) {
			if(orderArgument.equals(REVERSE)) {
				// If the REVERSE argument received, use ReverseOrder to initiate the reverse order.
				order = new ReverseOrder(order);
			}
			else {
				throw new WrongOrderFormatException();
			}
		}
		
		return order;
	}
	
}
