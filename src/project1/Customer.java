package project1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * This class contains variables for a customer object.
 * @author Legionaires
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Customer customer;
	private List<CustomerData> customers = new LinkedList<CustomerData>();
	
	private Customer() {
	}
	
	/**
	 * support the singleton pattern
	 * @return the singleton object
	 */
	protected static Customer instance() {
		if(customer == null) {
			return (customer = new Customer());
		}
		else {
			return customer;
		}
	}
	
	/**
	 * Checks whether a customer with given id exist or not.
	 * @param id
	 * @return the customer if exist
	 */
	protected CustomerData search(String id) {
		for (Iterator<CustomerData> iterator = this.customers.iterator(); iterator.hasNext(); ) {
			CustomerData aCustomer =  iterator.next();
		    if (aCustomer.getId().equals(id)) {
		    	  return aCustomer;
		    }
		}
		return null;
	}
	
	/**
	 * a method to add a customer
	 * @param newCustomer
	 * @return null if the customer dosent exist
	 */
	protected boolean addCustomer(CustomerData newCustomer) {
		if(this.search(newCustomer.getId()) == null) {
			customers.add(newCustomer);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	  * a method for Removing a Customer
	  * @author Min Htut
	  * 
	  * @param removeCustomer, CustomerData object
	  * @return true if the customer is removed
	  */
	protected boolean removeCustomer(CustomerData removeCustomer) {
		for (ListIterator<CustomerData> iterator = this.customers.listIterator(); iterator.hasNext(); ) {
			CustomerData aCustomer = iterator.next();
			if(aCustomer.getId().equals(removeCustomer.getId())) {
				iterator.remove();
		        return true;
			}
		}
		return false;
	}
	
	/**
	 * a method for Adding a Credit Card to a Customer
	 * @author Min Htut
	 * 
	 * @param id, String object of a unique customer's identification
	 * @param card, Credit object of a credit card to be added
	 * @return true if the card was added
	 */
	protected boolean addCreditCard(String id, Credit card) {
		for (Iterator<CustomerData> iterator = this.customers.iterator(); iterator.hasNext(); ) {
			CustomerData aCustomer =  iterator.next();
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
	 * @param aCustomer, CustomerData object of a customer
	 * @param aCard, Credit Object of a credit card which needed to be removed.
	 * @return if the card was removed
	 */
	protected boolean removeCreditCard(CustomerData aCustomer, Credit aCard) {
		for (Iterator<CustomerData> iterator = this.customers.iterator(); iterator.hasNext(); ) {
			CustomerData temp =  iterator.next();
			if (temp.getId().equals(aCustomer.getId())) {
				if(temp.getCards().size() > 1) { // proceed only if the customer has more than one card.
					if(aCard != null) {
						temp.removeCreditCard(aCard);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * a method to print all customers
	 * @author Min Htut
	 */
	protected void listAllCustomers() {
		for (ListIterator<CustomerData> iterator = this.customers.listIterator(); iterator.hasNext(); ) {
			CustomerData aCustomer = iterator.next();
			aCustomer.printCustomer();
		}
	}

}
