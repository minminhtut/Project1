package project1;

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
        this.seats = 0;
        this.name = new String();
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
     * Retrieves a deserialized version of the Theater from disk
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
     * @return true if the data could be saved
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
     * @param output the stream to be written to
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
     * case 1: add a client
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
     * case 2: remove a client
     *
     * @param id
     */
    protected ClientData removeClient(String clientID) {
        ClientData removeClient = client.searchClientID(clientID);
        client.removeClient(removeClient);
        return removeClient;
    }

    /**
     * case 3: list all client
     */
    protected void listAllClients() {
        client.listAllClients();
    }

    /**
     * case 4: add a customer
     */
    protected CustomerData addCustomer(String name, String address, String phone, long number, Calendar expiration) {
        CustomerData newCustomer = new CustomerData(name, address, phone, number, expiration);
        customer.addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * case 5: remove a customer
     *
     * @param id
     */
    protected CustomerData removeCustomer(String customerID) {
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
     * case 7: remove credit card from a customer
     *
     * @param id
     * @param number
     */
    protected Credit removeCreditCard(String id, long number) {
        CustomerData aCustomer = customer.searchCustomerID(id);
        if (aCustomer == null) {
            return null;
        } else {
            Credit aCard = aCustomer.searchCredit(number);
            boolean flag = customer.removeCreditCard(aCustomer, aCard);
            if (flag == true) {
                return aCard;
            } else {
                return null;
            }
        }
    }

    /**
     * case 8: list all customers
     */
    protected void listAllCustomers() {
        customer.listAllCustomers();
    }
    

    /**
     * case 9: add a play/show for a client
     *
     * @param id
     * @param name
     * @param start
     * @param end
     */
    protected Play addPlay(String id, String name, Calendar start, Calendar end) {
        ClientData aClient = client.searchClientID(id);
        Play aPlay = new Play(name, start, end);
        boolean playAdded = client.addPlay(aClient, aPlay);
        if (playAdded == true) {
            return aPlay;
        } else {
            return null;
        }
    }

    /**
     * case 10: list all play/show
     */
    protected void listAllPlays() {
        client.ListAllPlays();
    }
    
    /**
     * a method to list all shows
     */
    public boolean noClients() {
        return client.noClients();
    }
}
