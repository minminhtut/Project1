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
 * the class Customer holds data regarding the customers
 * 
 */
public class Customer extends CustomerData implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Customer customer;
    private List<CustomerData> customerList = new LinkedList<CustomerData>();

    public Customer() {
    }

    /**
     * support the singleton pattern
     *
     * @return the singleton object
     */
    public static Customer instance() {
        if (customer == null) {
            return (customer = new Customer());
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
    public CustomerData searchCustomerID(String customerID) {
        for (Iterator<CustomerData> iterator = customerList.iterator(); iterator.hasNext();) {
            CustomerData aCustomer = iterator.next();
            if (aCustomer.getId().equals(customerID)) {
                return aCustomer;
            }
        }
        return null;
    }

    /**
     * a method to add a customer
     *
     * @param newCustomer
     * @return null if the customer dosen't exist
     */
    public boolean addCustomer(CustomerData newCustomer) {
        if (searchCustomerID(newCustomer.getId()) == null) {
            customerList.add(newCustomer);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * a method to remove a customer
     * @author Min Htut
     *
     * @param removeCustomer, CustomerData Object of a customer to be removed.
     * @return true if the customer is removed
     */
    public boolean removeCustomer(CustomerData removeCustomer) {
        for (ListIterator<CustomerData> iterator = customerList.listIterator(); iterator.hasNext();) {
            CustomerData aCustomer = iterator.next();
            if (aCustomer.getId().equals(removeCustomer.getId())) {
                iterator.remove();
                return true;
            }
        }
        return false;
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
        for (Iterator<CustomerData> iterator = customerList.iterator(); iterator.hasNext();) {
            CustomerData aCustomer = iterator.next();
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
     * @param aCustomer, CustomerData Object of a customer
     * @param aCard, Credit Object of a credit card to be removed from the customer
     * @return
     */
    public boolean removeCreditCard(CustomerData aCustomer, Credit aCard) {
        for (Iterator<CustomerData> iterator = customerList.iterator(); iterator.hasNext();) {
            CustomerData temp = iterator.next();
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
        for (ListIterator<CustomerData> iterator = customerList.listIterator(); iterator.hasNext();) {
            CustomerData aCustomer = iterator.next();
            aCustomer.printCustomer();
        }
    }

}
