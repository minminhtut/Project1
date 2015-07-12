package project1;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * the Class UserInterface
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Theater theater;
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int REMOVE_CLIENT = 2;
	private static final int LIST_ALL_CLIENT = 3;
	private static final int ADD_CUSTOMER = 4;
	private static final int REMOVE_CUSTOMER = 5;
	private static final int ADD_CREDIT_CARD = 6;
	private static final int REMOVE_CREDIT_CARD = 7;
	private static final int LIST_ALL_CUSTOMERS = 8;
	private static final int ADD_PLAY = 9;
	private static final int LIST_ALL_PLAY = 10;
        private static final int GET_TRANSACTION = 11;
	private static final int STORE_DATA = 13;
	private static final int RETRIEVE_DATA = 14;
	private static final int HELP = 15;

	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		}
		else {
			theater = Theater.instance();
		}
	}
	
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	private static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		}
		else {
			return userInterface;
		}
	}
	
	/**
	 * Prompts for a command from the keyboard
	 * @return a valid command
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter a command: "));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} 
			catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 13 as explained below:");
		System.out.println(EXIT + " to Save and Exit");
		System.out.println(ADD_CLIENT + " to Add a Client");
		System.out.println(REMOVE_CLIENT + " to Remove a Client");
		System.out.println(LIST_ALL_CLIENT + " to List all Clients");
		System.out.println(ADD_CUSTOMER + " to Add a Customer");
		System.out.println(REMOVE_CUSTOMER + " to Remove a Customer");
		System.out.println(ADD_CREDIT_CARD + " to Add Credit Card");
		System.out.println(REMOVE_CREDIT_CARD + " to Remove Credit Card");
		System.out.println(LIST_ALL_CUSTOMERS + " to List all Customers");
		System.out.println(ADD_PLAY + " to Add Play");
                System.out.println(LIST_ALL_PLAY + " to List all Plays");
		System.out.println(GET_TRANSACTION + " to start/access a transaction");
		System.out.println(STORE_DATA + " to Store the Data");
		System.out.println(RETRIEVE_DATA + " to Retrieve Stored Data");
		System.out.println(HELP + " for help");
	}



	/**
	 * Gets a token after prompting
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			}
			catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
        


	/**
	 * Converts the string to a number
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please input a number.");
			}
		} while (true);
	}
        
	/**
	 * Converts the string to BigDecimal value
	 * @param prompt the string for prompting
	 * @return the BigDecimal value corresponding to the string
	 * 
	 */
	public BigDecimal getMoney(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				BigDecimal number = new BigDecimal(item);
				return number.setScale(2, RoundingMode.HALF_UP);
                                       
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please input a valid dollar amount.");
			}
		} while (true);
	}

	/**
	 * Converts the string to a number
	 * @param prompt the string for prompting
	 * @return the long corresponding to the string
	 * 
	 */
	public long getLong(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Long number = Long.valueOf(item);
				return number.longValue();
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please input a number.");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			}
			catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	public boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Method to be called for retrieving saved data.
	 * Uses the appropriate Library method for retrieval.
	 */
	public void retrieve() {

		try {
			Theater tempTheater = Theater.retrieve();
			if (tempTheater != null) {
				System.out.println("The theater has been successfully retrieved from the file TheaterData");
				theater = tempTheater;
			} else {
				System.out.println("File doesnt exist; creating new Theater");
				theater = Theater.instance();
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
		
	}

	/**
	 * Method to be called for adding a client
	 */
	public void addClient() {
		String name = getToken("Enter the Client name");
		String address = getToken("Enter the Client address");
		String phone = getToken("Enter the Client phone number");
		Client newClient = theater.addClient(name, address, phone);
		if (newClient == null) {
			System.out.println("Could not add the client");
		}
		else {
			System.out.println("Client was successfully added");
			System.out.println("Name: " + newClient.getName());
			System.out.println("ID: " + newClient.getId());
			System.out.println("Address: " + newClient.getAddress());
			System.out.println("Phone: " + newClient.getPhone() + "\n");
		}
	}

	/**
	 * Method to be called for removing a client
	 */
	public void removeClient() {
		String id = getToken("Enter  ID");
		boolean flag = theater.removeClient(id);
		if(flag == false)
			System.out.println("Could not remove the client");
		else
			System.out.println("The client was successfully removed.");

	}

	/**
	 * Method to be called for listing all client
	 */
	public void listAllClients() {
		theater.listAllClients();
	}

	/**
	 * Method to be called for adding customer
	 */
	public void addCustomer() {
		String name = getToken("Enter the name");
		String address = getToken("Enter the address");
		String phone = getToken("Enter the phone number");
                
		Customer newCustomer = theater.addCustomer(name, address, phone);
		if (newCustomer == null) {
			System.out.println("Could not add the customer");
		}
		else {
			System.out.println(newCustomer.getName() + "Customer was successfully added");
			System.out.println("Name: " + newCustomer.getName());
			System.out.println("ID: " + newCustomer.getId());
			System.out.println("Address: " + newCustomer.getAddress());
			System.out.println("Phone: " + newCustomer.getPhone() + "\n");
		}
                addCreditCard();
	}

	/**
	 * Method to be called for removing customer
	 */
	public void removeCustomer() {
		String id = getToken("Enter the ID number of the Customer");
		Customer removeCustomer = theater.removeCustomer(id);
		if(removeCustomer == null)
			System.out.println("Could not remove the customer");
		else
			System.out.println(removeCustomer.getName() + " was successfully removed.");

	}

	/**
	 * Method to be called for adding credit card
	 */
	public void addCreditCard() {
		String id = getToken("Enter the ID number of the Customer");
		long number = getLong("Enter the Credit Card Number");
		Calendar expiration = getDate("Enter the expiration Date");
		Credit newCreditCard = theater.addCreditCard(id, number, expiration);
		if (newCreditCard == null) {
			System.out.println("Could not add the credit card");
		}
		else {
			System.out.println("Credit Card was successfully added");
			System.out.println("Credit Card Number: "
					+ newCreditCard.getNumber());
			System.out.println("Credit Card Expiration: " + newCreditCard.getExpiration());
		}		
	}

	/**
	 * Method to be called for removing the credit card
	 */
	public void removeCreditCard() {
		String id = getToken("Enter the ID number of the Customer");
		long number = getLong("Enter the Credit Card Number");
		Credit card = theater.removeCreditCard(id, number);
		if(card == null)
			System.out.println("Could not remove the credit card");
		else
			System.out.println(card.getNumber() + " was successfully removed.");
	}

	/**
	 * Method to be called for listing all customers
	 */
	public void listAllCustomers() {
		theater.listAllCustomers();
	}

	/**
	 * Method to be called for adding a play
	 */
	public void addPlay() {
		if (theater.noClients()) {
			System.out.println("You currently have no clients. Please add one first.");
			addClient();
			System.out.println("Now add the play:");
		}
		String id = getToken("Enter the client ID");
		String name = getToken("Enter the name of the show");
		Calendar start = getDate("Enter the starting date mm/dd/yy");
		Calendar end = getDate("Enter the endinging date mm/dd/yy");
                BigDecimal price = getMoney("ENter the cost of the ticket price");
		Play newPlay = theater.addPlay(id, name, start, end, price);
		if(newPlay == null) {
			System.out.println("Could not add the play");
		}
		else {
			System.out.println("Play was successfully added:");
			System.out.println("Play Name: " + newPlay.getName());
			System.out.println("Start Date: " + newPlay.getStartDate());
			System.out.println("End Date: " + newPlay.getEndDate());
                        System.out.println("Play Cost:" + newPlay.getTicketPrice());
		}
	}
        


	/**
	 * Method to be called for issuing tickets. Prompts the user for the
	 * appropriate values and uses the appropriate Theater method for issuing
	 * tickets.
	 * 
	 */
        public void handleTransaction() {
            Ticket result;
            Customer currentCustomer;
            String customerID = getToken("Enter customer id");
            currentCustomer = theater.searchCustomerID(customerID);
            if (currentCustomer == null) {
                System.out.println("No such customer");
                return;
            }
            do {
                int type;
                do {
                    type = getNumber("Enter " + Theater.REGULARTICKET + " a regular ticket, "
                            + Theater.ADVANCETICKET + " for an advance ticket, or "
                            + Theater.STUDENTADVANCE + " fir student advance");
                } while (type != Theater.REGULARTICKET && type != Theater.ADVANCETICKET && type != Theater.STUDENTADVANCE);
                int quanity = Integer.parseInt(getToken("Enter quanity"));
                Calendar playDate = getDate("Enter the play date mm/dd/yy");
                
                for (int i = 0; i < quanity; i++) {
                String studentID = "";
                if (type == Theater.STUDENTADVANCE) {
                    studentID = getToken("Enter valid student id");
                }
                
                result = theater.makeTransaction(type, currentCustomer, currentCustomer.getCards(), playDate, studentID);
                if (result == null) {
                    System.out.println("Could not add the ticket");
                } else {
                    System.out.println("Ticket was successfully added");
                    System.out.println("Serial Number: " + result.getSerialNumber ()+ "\n");
                    System.out.println("Ticket Price: $" + result.getPrice ()+ "\n");
                }
                }
                if (!yesOrNo("Add more tickets?")) {
                    break;
                }
                
            } while (true);
        }
	
	/**
	 * Lists all plays for the current theater
	 * @param none
	 * @return none, returns a printout of current plays
	 */
	public void listAllPlays() {
		theater.listAllPlays();
	}

	/**
	 * Saves the current theater objects
	 */
	public void storeData() {
		boolean successStore = Theater.save();
		if (successStore) {
			System.out.println("Data Saved.");
		}
		else {
			System.out.println("No Data Saved.");
		}

	}
        
	
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case EXIT:
				System.exit(0);
				break;
			case ADD_CLIENT:
				addClient();
				break;
			case REMOVE_CLIENT:
				removeClient();
				break;
			case LIST_ALL_CLIENT:
				listAllClients();
				break;
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case REMOVE_CUSTOMER:
				removeCustomer();
				break;
			case ADD_CREDIT_CARD:
				addCreditCard();
				break;
			case REMOVE_CREDIT_CARD:
				removeCreditCard();
				break;
			case LIST_ALL_CUSTOMERS:
				listAllCustomers();
				break;
			case ADD_PLAY:
				addPlay();
				break;
			case LIST_ALL_PLAY:
				listAllPlays();
				break;
			case GET_TRANSACTION:
				handleTransaction();
				break;
			case STORE_DATA:
				storeData();
				break;
			case RETRIEVE_DATA:
				retrieve();
				break;
			case HELP:
				help();
				break;
			}
		}
		storeData();
	}

	public static void main(String[] args) {
		UserInterface.instance().process();
	}

}
