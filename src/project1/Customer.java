package project1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class contains variables for a customer object.
 *
 * @author Legionaires
 *
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Customer customer;
    private List<CustomerData> customerList = new LinkedList<CustomerData>();

    private Customer() {
    }

    /**
     * support the singleton pattern
     *
     * @return the singleton object
     */
    protected static Customer instance() {
        if (customer == null) {
            return (customer = new Customer());
        } else {
            return customer;
        }
    }

    /**
     * Checks whether a customer with given id exist or not.
     *
     * @param id
     * @return the customer if exist
     */
    protected CustomerData searchCustomerID(String customerID) {
        for (Iterator<CustomerData> iterator = this.customerList.iterator(); iterator.hasNext();) {
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
     * @return null if the customer dosent exist
     */
    protected boolean addCustomer(CustomerData newCustomer) {
        if (this.searchCustomerID(newCustomer.getId()) == null) {
            customerList.add(newCustomer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a method to remove a customer
     *
     * @param id
     * @return true if the customer is removed
     */
    protected boolean removeCustomer(CustomerData removeCustomer) {
        for (ListIterator<CustomerData> iterator = this.customerList.listIterator(); iterator.hasNext();) {
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
     *
     * @param id
     * @param number
     * @param expiration
     * @return true if the card was added
     */
    protected boolean addCreditCard(String id, Credit card) {
        for (Iterator<CustomerData> iterator = this.customerList.iterator(); iterator.hasNext();) {
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
     *
     * @param id
     * @param number
     * @return
     */
    protected boolean removeCreditCard(CustomerData aCustomer, Credit aCard) {
        for (Iterator<CustomerData> iterator = this.customerList.iterator(); iterator.hasNext();) {
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
     */
    protected void listAllCustomers() {
        for (ListIterator<CustomerData> iterator = this.customerList.listIterator(); iterator.hasNext();) {
            CustomerData aCustomer = iterator.next();
            aCustomer.printCustomer();
        }
    }

}
