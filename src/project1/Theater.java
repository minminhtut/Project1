package project1;

/**
 *
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 *
 * Redistribution and use with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * - the use is for academic purpose only - Redistributions of source code must
 * retain the above copyright notice, this list of conditions and the following
 * disclaimer. - Neither the name of Brahma Dathan or Sarnath Ramnath may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in
 * this module and are not responsible for any loss or damage resulting from its
 * use.
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
    private static ObjectOutputStream output;

    private Theater() {
        this.client = Client.instance();
        this.customer = Customer.instance();
        this.seats = 100;
        this.name = new String("Legionaires");
    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    protected static Theater instance() {
        if (theater == null) {
            return (theater = new Theater());
        } else {
            return theater;
        }
    }

    /**
     * a getter method to get the name of theater
     *
     * @return name
     */
    protected String getName() {
        return name;
    }

    /**
     * a setter method to set the name of theater
     */
    protected void setName(String name) {
        this.name = new String(name);
    }

    /**
     * a getter method to get the numbers of seats
     *
     * @return seats
     */
    protected int getSeats() {
        return seats;
    }

    /**
     * a setter method to set the numbers of seats
     */
    protected void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Retrieves a de-serialized version of the Theater from disk
     *
     * @return a Theater object
     */
    public static Theater retrieve() {
        try {
            FileInputStream file = new FileInputStream("TheaterData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            MemberIdServer.retrieve(input);
            return theater;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    /**
     * Serializes the Theater object
     *
     * @return true iff the data could be saved
     */
    public static boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("TheaterData");
            output = new ObjectOutputStream(file);
            output.writeObject(theater);
            output.writeObject(MemberIdServer.instance());
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    /**
     * Writes the object to the output stream
     *
     * @param output, the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(theater);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * Reads the object from a given stream
     *
     * @param input the stream to be read
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if (theater == null) {
                theater = (Theater) input.readObject();
            } else {
                input.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Case 1: Add a Client
     *
     * @author
     *
     * @param name
     * @param address
     * @param phone
     */
    protected ClientData addClient(String name, String address, String phone) {
        ClientData aClient = new ClientData(name, address, phone);
        client.addClient(aClient);
        return aClient;
    }

    /**
     * Case 2: Remove a Client
     *
     * @author
     *
     * @param id
     */
    protected ClientData removeClient(String id) {
        ClientData removeClient = client.search(id);
        client.removeClient(removeClient);
        return removeClient;
    }

    /**
     * Case 3: List all Clients
     *
     * @author
     */
    protected void listAllClients() {
        client.listAllClients();
    }

    /**
     * Case 4: Add a Customer
     */
    protected CustomerData addCustomer(String name, String address, String phone, long number, Calendar expiration) {
        CustomerData newCustomer = new CustomerData(name, address, phone, number, expiration);
        customer.addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * Case 5: Remove a Customer
     *
     * @author Min Htut
     *
     * @param id, String object of a unique customer's identification.
     */
    protected CustomerData removeCustomer(String id) {
        CustomerData removeCustomer = customer.search(id);
        customer.removeCustomer(removeCustomer);
        return removeCustomer;
    }

    /**
     * Case 6: Add a Credit Card to a customer
     *
     * @author Min Htut
     *
     * @param id, String object of a unique customer's identification
     * @param number, Long variable of a credit number
     * @param expiration, Calendar Object of the expiration date
     * @return newCredit, Credit Object of the credit which is added
     */
    protected Credit addCreditCard(String id, long number, Calendar expiration) {
        Credit newCredit = new Credit(number, expiration);

        boolean flag = customer.addCreditCard(id, newCredit);
        if (flag == true) {
            return newCredit;
        } else {
            return null;
        }
    }

    /**
     * Case 7: Remove a Credit Card from a customer
     *
     * @author Min Htut
     *
     * @param id, String object of a unique customer's identification
     * @param number, Long variable of a credit number
     */
    protected Credit removeCreditCard(String id, long number) {
        CustomerData aCustomer = customer.search(id);

        if (aCustomer == null) {
            return null;
        } else {
            Credit aCard = aCustomer.search(number);
            boolean flag = customer.removeCreditCard(aCustomer, aCard);
            if (flag == true) {
                return aCard;
            } else {
                return null;
            }
        }
    }

    /**
     * Case 8: Listing all Customers
     *
     * @author Min Htut
     */
    protected void listAllCustomers() {
        customer.listAllCustomers();
    }

    /**
     * Case 9: add a play/show for a client
     *
     * @param id
     * @param name
     * @param start
     * @param end
     */
    protected Play addPlay(String id, String name, Calendar start, Calendar end) {
        ClientData aClient = client.search(id);
        if (aClient != null) {
            Play aPlay = new Play(name, start, end);
            boolean flag = client.addPlay(aClient, aPlay);
            if (flag == true) {
                return aPlay;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Case 10: list all play/show
     */
    protected void listAllPlays() {
        client.ListAllPlays();
    }
}
