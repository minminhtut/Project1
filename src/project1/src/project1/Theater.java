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
import java.util.List;

public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Theater theater;
    private ClientList clientList;
    private CustomerList customerList;

    public static final int REGULARTICKET = 1;
    public static final int ADVANCETICKET = 2;
    public static final int STUDENTADVANCE = 3;
    
    private String name;	// required by project 1 hand out
    private int seats;		// required by project 1 hand out
    private static ObjectOutputStream output;

    private Theater() {
        clientList = ClientList.instance();
        customerList = CustomerList.instance();
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

    /**
     * case 1: add a clientList
     * @param name
     * @param address
     * @param phone
     */
    public Client addClient(String name, String address, String phone) {
        Client aClient = new Client(name, address, phone);
        clientList.addClient(aClient);
        return aClient;
    }

    /**
     * case 2: remove a clientList
     * @param id
     */
    public boolean removeClient(String clientID) {
    	boolean flag = false;
        Client removeClient = clientList.searchClientID(clientID);
        flag = clientList.removeClient(removeClient);
        return flag;
    }

    /**
     * case 3: list all clientList
     */
    public void listAllClients() {
        clientList.listAllClients();
    }

    /**
     * case 4: add a customerList
     */
    public Customer addCustomer(String name, String address, String phone) {
        Customer newCustomer = new Customer(name, address, phone);
        customerList.addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * case 5: remove a customerList
     *
     * @param id
     */
    public Customer removeCustomer(String customerID) {
        Customer removeCustomer = customerList.searchCustomerID(customerID);
        customerList.removeCustomer(removeCustomer);
        return removeCustomer;
    }

    /**
     * case 6: add credit to a customerList
     *
     * @param id
     * @param number
     * @param expiration
     */
    public Credit addCreditCard(String id, long number, Calendar expiration) {
        Credit newCredit = new Credit(number, expiration);
        boolean flag = customerList.addCreditCard(id, newCredit);
        if (flag == true) {
            return newCredit;
        }
        else {
            return null;
        }
    }

    /**
     * case 7: remove credit card from a customerList
     *
     * @param id
     * @param number
      * @return creditcard object 
     */
    public Credit removeCreditCard(String id, long number) {
        Customer aCustomer = customerList.searchCustomerID(id);
        if (aCustomer == null) {
            return null;
        }
        else {
            Credit aCard = aCustomer.searchCredit(number);
            boolean flag = customerList.removeCreditCard(aCustomer, aCard);
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
        customerList.listAllCustomers();
    }
    

    /**
     * 
     * @param id
     * @param name
     * @param start
     * @param end
     * @param price
     * @return Play object if it was successfully added to clientList
     */
    public Play addPlay(String id, String name, Calendar start, Calendar end, long price) {
        Client aClient = clientList.searchClientID(id);
        Play aPlay = new Play(name, start, end, price);
        boolean playAdded = clientList.addPlay(aClient, aPlay);
        if (playAdded == true) {
            return aPlay;
        }
        else {
            return null;
        }
    }

    /**
     * case 10: list all play/show
     */
    public void listAllPlays() {
        clientList.ListAllPlays();
    }
    
    /**
     * a method to list all shows
     */
    public boolean noClients() {
        return clientList.noClients();
    }
    /**
     * a method to search customer ID
     */
    public Customer searchCustomerID(String customerID) {
        return customerList.searchCustomerID(customerID);
    }
    /**
     * 
     * @param startd
     * @return int value for ticket date
     */
    public int ticketDateInt (Calendar startd) {
        int year = startd.get(Calendar.YEAR);
        int month = startd.get(Calendar.MONTH);
        int day = startd.get(Calendar.DATE);
        return (year*10000) + ((month+1)*100) + day;  
    
    }
    /**
     * 
     * @param type
     * @param currentCustomer
     * @param creditCard
     * @param ticketDate
     * @param studentID
     * @return ticket object if ticket successfully created
     */
    public Ticket makeTransaction(int type, Customer currentCustomer, List<Credit> creditCard, Calendar ticketDate, String studentID) {
        
        Play aPlay = clientList.searchTicketDate(ticketDateInt(ticketDate));
        
        if (aPlay == null) {
            return null;
        }
        Ticket ticket = TicketFactory.instance().CreateTicket(
                type, currentCustomer.getId(), creditCard, ticketDate, studentID, aPlay);
        if (currentCustomer.addTransaction(ticket)) {
            return (ticket);
        }
        return null;
    }
}
