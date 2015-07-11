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
            if (aCustomer.getId().matches(customerID)) {
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
            if (aCustomer.getId().matches(removeCustomer.getId())) {
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
            if (aCustomer.getId().matches(id)) {
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
            if (temp.getId().matches(aCustomer.getId())) {
                if (temp.getCards().size() > 1) {
                    temp.removeCreditCard(aCard);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * a method to search a credit card by number
     * 
     * @param aCustomer
     * @param cardNumber
     * @return
     */
    public boolean searchCreditNumber(CustomerData aCustomer, long cardNumber) {
    	if(aCustomer != null) {
    		for (Iterator<CustomerData> iterator = customerList.iterator(); iterator.hasNext();) {
                CustomerData temp = iterator.next();
                if(temp.getId().matches(aCustomer.getId())) {
                	if(temp.searchCredit(cardNumber) != null)
                		return true;
                	else
                		return false;
                }
        	}
            return false;
    	}
    	else
    		return false;
    }
    
    /**
     * a method to add a ticket to the customer
     * 
     * @param ticket
     * @param aCustomer
     * @param aTicket
     */
    public void addCustomerTicket(CustomerData aCustomer, Ticket aTicket) {
    	int index = 0;
    	for(int i = 0; i < customerList.size(); i++) {
    		if(customerList.get(i).getId().matches(aCustomer.getId())) {
    			index = i;
    		}
    	}
    	
    	if(index > 0 | index < customerList.size()) {
    		customerList.get(index).addCustomerTicket(aTicket);
    	}
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
