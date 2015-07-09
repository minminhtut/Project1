package project1;

/**
 * This file contains Theater Object for Project 1.
 * @author Legionaires
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Theater theater;
    private Client client;
    private Customer customer;
    private String name;
    private int seats;
    private long balance;
    
    private static ObjectOutputStream output;
    
	private static int REGULAR_TICKET = 1;
	private static int ADVANCE_TICKET = 2;
	private static int ADVANCE_STUDENT = 3;

    private Theater() {
        client = Client.instance();
        customer = Customer.instance();
        seats = 100;
        name = new String("Theater");
    }

    /**
     * Supports the singleton pattern
     * @return the singleton object
     */
    public static Theater instance() {
        if (theater == null) {
            return (theater = new Theater());
        }
        else {
            return theater;
        }
    }

    /**
     * Retrieves a deserialized version of the Theater from disk
     * @return a Theater object
     */
    public static Theater retrieve() {
        try {
            FileInputStream file = new FileInputStream("TheaterData");
            ObjectInputStream input = new ObjectInputStream(file);
            theater = (Theater) input.readObject();
            MemberIdServer.retrieve(input);
            TicketIdServer.retrieve(input);
            input.close();
            return theater;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
    
    /**
     * Serializes the Theater object
     * @return true if the data could be saved
     */
    public static boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("TheaterData");
            output = new ObjectOutputStream(file);
            output.writeObject(theater);
            output.writeObject(MemberIdServer.instance());
            output.writeObject(TicketIdServer.instance());
            output.close();
            return true;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    /**
     * Writes the object to the output stream
     * @param output the stream to be written to
     */
    public void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(theater);
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * Reads the object from a given stream
     * @param input the stream to be read
     */
    public void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if (theater == null) {
                theater = (Theater) input.readObject();
            }
            else {
                input.readObject();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
     * case 1: add a client
     * @param name
     * @param address
     * @param phone
     */
    public ClientData addClient(String name, String address, String phone) {
        ClientData aClient = new ClientData(name, address, phone);
        client.addClient(aClient);
        return aClient;
    }

    /**
     * case 2: remove a client
     * @param id
     */
    public boolean removeClient(String clientID) {
    	boolean flag = false;
        ClientData removeClient = client.searchClientID(clientID);
        flag = client.removeClient(removeClient);
        return flag;
    }

    /**
     * case 3: list all client
     */
    public void listAllClients() {
        client.listAllClients();
    }

    /**
     * case 4: add a customer
     */
    public CustomerData addCustomer(String name, String address, String phone, long number, Calendar expiration) {
        CustomerData newCustomer = new CustomerData(name, address, phone, number, expiration);
        customer.addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * case 5: remove a customer
     *
     * @param id
     */
    public CustomerData removeCustomer(String customerID) {
        CustomerData removeCustomer = customer.searchCustomerID(customerID);
        customer.removeCustomer(removeCustomer);
        return removeCustomer;
    }

    /**
     * case 6: add credit to a customer
     *
     * @param id
     * @param number
     * @param expiration
     */
    public Credit addCreditCard(String id, long number, Calendar expiration) {
        Credit newCredit = new Credit(number, expiration);
        boolean flag = customer.addCreditCard(id, newCredit);
        if (flag == true) {
            return newCredit;
        }
        else {
            return null;
        }
    }

    /**
     * case 7: remove credit card from a customer
     *
     * @param id
     * @param number
      * @return crditcard object 
     */
    public Credit removeCreditCard(String id, long number) {
        CustomerData aCustomer = customer.searchCustomerID(id);
        if (aCustomer == null) {
            return null;
        }
        else {
            Credit aCard = aCustomer.searchCredit(number);
            boolean flag = customer.removeCreditCard(aCustomer, aCard);
            if (flag == true) {
                return aCard;
            }
            else {
                return null;
            }
        }
    }

    /**
     * case 8: list all customers
     */
    public void listAllCustomers() {
        customer.listAllCustomers();
    }
    

    /**
     * case 9: add a play/show for a client
     *
     * @param id
     * @param name
     * @param start
     * @param end
     * @return play object if play added
     */
    public Play addPlay(String id, String name, Calendar start, Calendar end) {
        ClientData aClient = client.searchClientID(id);
        Play aPlay = new Play(name, start, end);
        if(aClient != null) {
        	client.addPlay(aClient, aPlay);
        }
        else
        	aPlay = null;
        
        return aPlay; 
    }

    /**
     * case 10: list all play/show
     */
    public void listAllPlays() {
        client.ListAllPlays();
    }
    
    /**
     * a method to list all shows
     */
    public boolean noClients() {
        return client.noClients();
    }
    
    /**
     * a method to sell a regular ticket
     */
    public boolean sellRegular(int ticket, String id, long number, Calendar date) {
    	CustomerData aCustomer = customer.searchCustomerID(id);
    	ClientData aClient = client.searchClientPlay(date);
    	Play aPlay = client.searchPlay(date);
    	boolean creditFlag = customer.searchCreditNumber(aCustomer, number);
    	Ticket aTicket;
    	
    	if((ticket > 0) & (aCustomer != null) & (aClient != null) & (creditFlag != false) &
    	   (aPlay.getTickets().size() < seats) & (aPlay != null)) {
    		
    		while(ticket > 0) {
    			aTicket = new Ticket(REGULAR_TICKET, date, number);
    			customer.addCustomerTicket(aCustomer, aTicket);
    			client.addClientTicket(aClient, aPlay, aTicket);
    			ticket--;
    		}

    		return true;
    	}
    	else {
    		if(aCustomer == null)
    			System.out.println("Invalid Customer.");
    		if(creditFlag == false)
    			System.out.println("Invalid Credit Card.");
    		if(aPlay == null)
    			System.out.println("Invalid Play.");
    		
    		return false;
    	}
    }
    
    /**
     * a method to sell an advance ticket
     */
    public boolean sellAdvance(int ticket, String id, long number, Calendar date) {
    	CustomerData aCustomer = customer.searchCustomerID(id);
    	ClientData aClient = client.searchClientPlay(date);
    	Play aPlay = client.searchPlay(date);
    	boolean creditFlag = customer.searchCreditNumber(aCustomer, number);
    	Ticket aTicket;
    	
    	if((ticket > 0) & (aCustomer != null) & (aClient != null) & (creditFlag != false) &
    	   (aPlay.getTickets().size() < seats) & (aPlay != null)) {
    		
    		while(ticket > 0) {
    			aTicket = new Ticket(ADVANCE_TICKET, date, number);
    			customer.addCustomerTicket(aCustomer, aTicket);
    			client.addClientTicket(aClient, aPlay, aTicket);
    			ticket--;
    		}

    		return true;
    	}
    	else {
    		if(aCustomer == null)
    			System.out.println("Invalid Customer.");
    		if(creditFlag == false)
    			System.out.println("Invalid Credit Card.");
    		if(aPlay == null)
    			System.out.println("Invalid Play.");
    		
    		return false;
    	}
    }
    
    /**
     * a method to sell an advance ticket
     */
    public boolean sellStudentAdvance(int ticket, String id, long number, Calendar date) {
    	CustomerData aCustomer = customer.searchCustomerID(id);
    	ClientData aClient = client.searchClientPlay(date);
    	Play aPlay = client.searchPlay(date);
    	boolean creditFlag = customer.searchCreditNumber(aCustomer, number);
    	Ticket aTicket;
    	
    	if((ticket > 0) & (aCustomer != null) & (aClient != null) & (creditFlag != false) &
    	   (aPlay.getTickets().size() < seats) & (aPlay != null)) {
    		
    		while(ticket > 0) {
    			aTicket = new Ticket(ADVANCE_TICKET, date, number);
    			customer.addCustomerTicket(aCustomer, aTicket);
    			client.addClientTicket(aClient, aPlay, aTicket);
    			ticket--;
    		}

    		return true;
    	}
    	else {
    		if(aCustomer == null)
    			System.out.println("Invalid Customer.");
    		if(creditFlag == false)
    			System.out.println("Invalid Credit Card.");
    		if(aPlay == null)
    			System.out.println("Invalid Play.");
    		
    		return false;
    	}
    }
}
