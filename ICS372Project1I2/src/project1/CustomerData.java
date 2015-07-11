package project1;

/**
 * This file contains Customer Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * the Class CustomerData contains data for a single customer
 *
 */
public class CustomerData extends Credit implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CUSTOMER_STRING = "M";
    private String id;
    private String name;
    private String address;
    private String phone;
    private List<Credit> cards = new LinkedList<Credit>();
    private List<Ticket> tickets = new LinkedList<Ticket>();

    /**
     * a constructor without parameters
     */
    public CustomerData() {
    }

    /**
     * create a customer with a given id, name, address and phone number
     * @param newName
     * @param newAddress
     * @param newPhone
     * @param newNumber
     * @param expiration
     */
    public CustomerData(String newName, String newAddress, String newPhone, long newNumber, Calendar expiration) {
        name = new String(newName);
        address = new String(newAddress);
        phone = new String(newPhone);
        id = new String(CUSTOMER_STRING + (MemberIdServer.instance()).getId());
        Credit aCard = new Credit(newNumber, expiration);
        cards = new LinkedList<Credit>();
        tickets = new LinkedList<Ticket>();
        cards.add(aCard);
    }

    /**
     * getter method to get id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * a setter method to set id
     * @param id
     */
    public void setId() {
        id = CUSTOMER_STRING + (MemberIdServer.instance()).getId();
    }

    /**
     * a getter method to get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * a setter method to set the name
     * @param newName
     */
    public void setName(String newName) {
        name = new String(newName);
    }

    /**
     * a getter method to get an address
     * @return newAddress
     */
    public String getAddress() {
        return address;
    }

    /**
     * a setter method to set an address
     * @param address
     */
    public void setAddress(String newAddress) {
        address = newAddress;
    }

    /**
     * a getter method to get a phone number
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * a setter method to get a phone number
     * @param phone
     */
    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    /**
     * a getter method to get the list of credit cards
     * @return cards
     */
    public List<Credit> getCards() {
        return cards;
    }

    /**
     * a setter method to set the list of credit cards
     * @param cards
     */
    public void setCards(List<Credit> newCards) {
        cards = newCards;
    }

    /**
     * Checks whether a card with a given number exists.
     * @param number
     * @return the card if exist
     */
    public Credit searchCredit(long number) {
        for (Iterator<Credit> iterator = cards.iterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            if (aCard.getNumber() == number) {
                return aCard;
            }
        }
        return null;
    }

    /**
     * a method to add a credit card
     * @param card, Credit Object of a credit card to be added
     * @return true, the card was added
     */
    public boolean addCreditCard(Credit card) {
        if (searchCredit(card.getNumber()) == null) {
            cards.add(card);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * a method to remove a credit card
     * @param card, Credit Object of a credit card to be added
     * @return true if the card was removed
     */
    public boolean removeCreditCard(Credit card) {
        for (ListIterator<Credit> iterator = cards.listIterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            if (aCard.getNumber() == card.getNumber()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    

    /**
     * a getter method to get the list of tickets
     * @return tickets
     */
    public List<Ticket> getTickets() {
		return tickets;
	}

    /**
     * a setter method to set the list of tickets
     * @return cards
     */
	public void setTickets(List<Ticket> newTickets) {
		tickets = newTickets;
	}
	
	/**
	 * a method to add a single ticket to the customer
	 * @param aTicket
	 */
	public void addCustomerTicket(Ticket aTicket) {
		tickets.add(aTicket);
	}

	/**
     * print a customer
     */
    public void printCustomer() {
        System.out.println("Id: " + getId() + " Name: " + getName() + " Address: " + getAddress() + " Phone: " + getPhone());
        for (ListIterator<Credit> iterator = cards.listIterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            aCard.printCard();
        }
        
        for (ListIterator<Ticket> iterator = tickets.listIterator(); iterator.hasNext();) {
        	Ticket aTicket = iterator.next();
        	aTicket.printTicket();
        }
    }

}
