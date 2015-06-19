package project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
	private static final int STORE_DATA = 11;
	private static final int RETRIEVE_DATA = 12;
	private static final int HELP = 13;
	
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
		retrieve();
		} else {
		theater = Theater.instance();
		}
	}
	

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
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
	* 
	*/
	public void help() {
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_CLIENT + " to Add Client");
		System.out.println(REMOVE_CLIENT + " to Remove Client");
		System.out.println(LIST_ALL_CLIENT + " to List all Client");
		System.out.println(ADD_CUSTOMER + " to Add Customert");
		System.out.println(REMOVE_CUSTOMER + " to Remove Customer");
		System.out.println(ADD_CREDIT_CARD + " to Add Credit Card");
		System.out.println(REMOVE_CREDIT_CARD + " to Remove Credit Card");
		System.out.println(LIST_ALL_CUSTOMERS + " to List all Customers");
		System.out.println(ADD_PLAY + " to Add Play");
		System.out.println(LIST_ALL_PLAY + " to List all Plays");
		System.out.println(STORE_DATA + " to save Data");
		System.out.println(RETRIEVE_DATA + " to Retrieve Data");
		System.out.println(HELP + " for help");
	}
	
	/**
	   * Supports the singleton pattern
	   * 
	   * @return the singleton object
	   */
	  public static UserInterface instance() {
	    if (userInterface == null) {
	      return userInterface = new UserInterface();
	    } else {
	      return userInterface;
	    }
	  }
	
	/**
	   * Gets a token after prompting
	   * 
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
	      } catch (IOException ioe) {
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
	      } catch (NumberFormatException nfe) {
	        System.out.println("Please input a number ");
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
	      } catch (NumberFormatException nfe) {
	        System.out.println("Please input a number ");
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
	      } catch (Exception fe) {
	        System.out.println("Please input a date as mm/dd/yy");
	      }
	    } while (true);
	  }
	
	/**
	   * Queries for a yes or no and returns true for yes and false for no
	   * 
	   * @param prompt The string to be prepended to the yes/no prompt
	   * @return true for yes and false for no
	   * 
	   */
	  private boolean yesOrNo(String prompt) {
	    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	      return false;
	    }
	    return true;
	  }
	  
	  /**
	   * Method to be called for retrieving saved data.
	   * Uses the appropriate Library method for retrieval.
	   *  
	   */
	  private void retrieve() {
		    try {
		      Theater tempTheater = Theater.retrieve();
		      if (tempTheater != null) {
		        System.out.println(" The theater has been successfully retrieved from the file TheaterData \n" );
		        theater = tempTheater;
		      } else {
		        System.out.println("File doesnt exist; creating new Theater" );
		        theater = Theater.instance();
		      }
		    } catch(Exception cnfe) {
		      cnfe.printStackTrace();
		    }
	  }
	 
	  /**
	   * Method to be called for adding a client
	   */
	  public void addClient() {
		  String name = getToken("Enter the name");
		  String address = getToken("Enter the address");
		  String phone = getToken("Enter the phone number");
		  ClientData aClient = theater.addClient(name, address, phone);
		  if(aClient == null)
			  System.out.println("Could not add the client");
		  else
			  System.out.println(aClient.getName() + " was successfully added.");
		  
	  }
	  
	  /**
	   * Method to be called for removing a client
	   */
	  public void removeClient() {
		  String id = getToken("Enter  ID");
		  ClientData removeClient = theater.removeClient(id);
		  if(removeClient == null)
			  System.out.println("Could not remove the client");
		  else
			  System.out.println(removeClient.getName() + " was successfully removed.");
			  
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
		  long number = getLong("Enter the Credit Card Number");
		  Calendar expiration = getDate("Enter the expiration Date in mm/dd/yy");
		  CustomerData newCustomer = theater.addCustomer(name, address, phone, number, expiration);
		  if(newCustomer == null)
			  System.out.println("Could not add the customer");
		  else
			  System.out.println(newCustomer.getName() + " was successfully added.");
	  }
	  
	/**
	  * Interface Method to Remove a Customer
	  * @author Min Htut
	  */
	public void removeCustomer() {
		String id = getToken("Enter the customer's ID");
		
		CustomerData removeCustomer = theater.removeCustomer(id);
		
		if(removeCustomer == null)
			System.out.println("Could not remove the customer");
		else
			System.out.println(removeCustomer.getName() + " was successfully removed.");
	}

	/**
	  * Interface Method to Add a Credit Card
	  * @author Min Htut
	  */
	public void addCreditCard() {
		String id = getToken("Enter the customer's ID");
		long number = getLong("Enter the Credit Card Number");
		Calendar expiration = getDate("Enter the expiration Date in mm/dd/yy");
		
		Credit result = theater.addCreditCard(id, number, expiration);
		
		if(result == null)
			System.out.println("Could not add the credit card");
		else
			System.out.println(result.getNumber() + " was successfully added.");
	}
	
	/**
	  * Interface Method to Remove a Credit Card from a Customer
	  * @author Min Htut
	  */
	public void removeCreditCard() {
		String id = getToken("Enter the customer's ID");
		long number = getLong("Enter the Credit Card Number");
		
		Credit card = theater.removeCreditCard(id, number);
		
		if(card == null)
			System.out.println("Could not remove the credit card");
		else
			System.out.println(card.getNumber() + " was successfully removed.");
	}
	
	/**
	  * Interface Method to List all Customers
	  * @author Min Htut
	  */
	public void listAllCustomers() {
		theater.listAllCustomers();
	}
	
	/**
	 * Method to be called for adding a play
	 */
	public void addPlay() {
		String id = getToken("Enter the client ID");
		String name = getToken("Enter the name of the show");
		Calendar start = getDate("Enter the starting date in mm/dd/yy");
		Calendar end = getDate("Enter the endinging date in mm/dd/yy");
		Play result = theater.addPlay(id, name, start, end);
		if(result == null)
			System.out.println("Could not add the play");
		else
			System.out.println(result.getName() + " was successfully added.");
		
	}
	
	/**
	 * a method to list plays
	 */
	public void listAllPlays() {
		theater.listAllPlays();
	}
	
	/**
	   * Method to be called for saving the theater object.
	   * Uses the appropriate Library method for saving.
	   *  
	   */
	  private void save() {
	    if (theater.save()) {
	      System.out.println("The theater has been successfully saved in the file TheaterData \n" );
	    } else {
	      System.out.println("There has been an error in saving \n" );
	    }
	  }
	  
	  /**
	   * Method to be called for retrieving saved data.
	   * Uses the appropriate Theater method for retrieval.
	   *  
	   */
	private void theaterRetrieve() {
	    try {
	      Theater temptheater = theater.retrieve();
	      if (temptheater != null) {
	        System.out.println("The theater has been successfully retrieved from the file TheaterData \n" );
	        theater = temptheater;
	      } else {
	        System.out.println("File doesnt exist; creating new library" );
	        theater = theater.instance();
	      }
	    } catch(Exception cnfe) {
	      cnfe.printStackTrace();
	    }
	  }

	/**
	   * Orchestrates the whole process.
	   * Calls the appropriate method for the different functionalties.
	   *  
	   */
	public void process() {
		int command;
		help();
		
		while ((command = getCommand()) != EXIT) {
			switch (command) {
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
		        case STORE_DATA:
		        	save();
		            break;
		        case RETRIEVE_DATA:
		        	theaterRetrieve();
		        	break;
		        case HELP:
		        	help();
		        	break;
			}
		}
	}
	 
	 public static void main(String[] args) {
		    UserInterface.instance().process();
		  }

}
