package project1;

/**
 * This file contains Customer Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * the class CustomerList holds data regarding the customers
 * 
 */
public class CustomerList extends ListHelper<Customer ,String> {

    private static final long serialVersionUID = 1L;
    private static CustomerList customer;


    public CustomerList() {
    }

    /**
     * support the singleton pattern
     *
     * @return the singleton object
     */
    public static CustomerList instance() {
        if (customer == null) {
            return (customer = new CustomerList());
        }
        else {
            return customer;
        }
    }

    /**
     * Checks whether a customer with given id exist or not.
     *
     * @param customerID
     * @return the customer if exist
     */
    public Customer searchCustomerID(String customerID) {
        return super.search(customerID);
    }

    /**
     * a method to add a customer
     *
     * @param newCustomer
     * @return null if the customer dosen't exist
     */
    public boolean addCustomer(Customer newCustomer) {
        return super.add(newCustomer);
    }

    /**
     * a method to remove a customer
     * @author Min Htut
     *
     * @param removeCustomer, Customer Object of a customer to be removed.
     * @return true if the customer is removed
     */
    public boolean removeCustomer(Customer removeCustomer) {
        return super.remove(removeCustomer);
    }

    /**
     * a method to add a credit card to a customer
     * @author Min Htut
     *
     * @param id
     * @param card, Credit Object of a credit card to be added to the customer
     * @return true if the card was added
     */
    public boolean addCreditCard(String id, Credit card) {
        for (Iterator<Customer> iterator = super.iterator(); iterator.hasNext();) {
            Customer aCustomer = iterator.next();
            if (aCustomer.getId().equals(id)) {
                aCustomer.addCreditCard(card);
                return true;
            }
        }
        return false;
    }

    /**
     * a method to remove a credit card from a customer
     * @author Min Htut
     *
     * @param aCustomer, Customer Object of a customer
     * @param aCard, Credit Object of a credit card to be removed from the customer
     * @return
     */
    public boolean removeCreditCard(Customer aCustomer, Credit aCard) {
        for (Iterator<Customer> iterator = super.iterator(); iterator.hasNext();) {
            Customer temp = iterator.next();
            if (temp.getId().equals(aCustomer.getId())) {
                if (temp.getCards().size() > 1) {
                    temp.removeCreditCard(aCard);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * a method to print all customers
     * @author Min Htut
     */
    public void listAllCustomers() {
        for (Iterator<Customer> iterator = super.iterator(); iterator.hasNext();) {
            Customer aCustomer = iterator.next();
            aCustomer.printCustomer();
        }
    }

}
