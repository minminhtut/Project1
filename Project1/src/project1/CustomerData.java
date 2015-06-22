package project1;

import java.io.Serializable;
import java.util.Calendar;
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
public class CustomerData extends Credit implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CUSTOMER_STRING = "M";
    private String id;
    private String name;
    private String address;
    private String phone;
    private List<Credit> cards = new LinkedList<Credit>();

    /**
     * a constructor without parameters
     */
    protected CustomerData() {
    }

    /**
     * create a customer with a given id, name, address and phone number
     *
     * @param id
     * @param name
     * @param address
     * @param phone
     */
    protected CustomerData(String name, String address, String phone, long number, Calendar expiration) {
        this.name = new String(name);
        this.address = new String(address);
        this.phone = new String(phone);
        this.id = new String(CUSTOMER_STRING + (MemberIdServer.instance()).getId());
        Credit aCard = new Credit(number, expiration);
        cards.add(aCard);
    }

    /**
     * getter method to get id
     *
     * @return id
     */
    protected String getId() {
        return id;
    }

    /**
     * a setter method to set id
     *
     * @param id
     */
    protected void setId(String id) {
        this.id = CUSTOMER_STRING + (MemberIdServer.instance()).getId();
    }

    /**
     * a getter method to get the name
     *
     * @return name
     */
    protected String getName() {
        return name;
    }

    /**
     * a setter method to set the name
     *
     * @param name
     */
    protected void setName(String name) {
        this.name = new String(name);
    }

    /**
     * a getter method to get an address
     *
     * @return address
     */
    protected String getAddress() {
        return address;
    }

    /**
     * a setter method to set an address
     *
     * @param address
     */
    protected void setAddress(String address) {
        this.address = address;
    }

    /**
     * a getter method to get a phone number
     *
     * @return
     */
    protected String getPhone() {
        return phone;
    }

    /**
     * a setter method to get a phone number
     *
     * @param phone
     */
    protected void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * a getter method to get the list of credit cards
     *
     * @return
     */
    protected List<Credit> getCards() {
        return this.cards;
    }

    /**
     * a setter method to set the list of credit cards
     *
     * @param cards
     */
    protected void setCards(List<Credit> cards) {
        this.cards = cards;
    }

    /**
     * Checks whether a card with a given number exists.
     *
     * @param number
     * @return the card if exist
     */
    protected Credit searchCredit(long number) {
        for (Iterator<Credit> iterator = this.cards.iterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            if (aCard.getNumber() == number) {
                return aCard;
            }
        }
        return null;
    }

    /**
     * a method to add a credit card
     *
     * @param number
     * @param expiration
     * @return true, the card was added
     */
    protected boolean addCreditCard(Credit card) {
        if (this.searchCredit(card.getNumber()) == null) {
            cards.add(card);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a method to remove a credit card
     *
     * @param number
     * @return true if the card was removed
     */
    protected boolean removeCreditCard(Credit card) {
        for (ListIterator<Credit> iterator = this.cards.listIterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            if (aCard.getNumber() == card.getNumber()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * print a customer
     */
    protected void printCustomer() {
        System.out.println("Id: " + this.getId() + " Name: " + this.getName() + " Address: " + this.getAddress() + " Phone: " + this.getPhone());
        for (ListIterator<Credit> iterator = this.cards.listIterator(); iterator.hasNext();) {
            Credit aCard = iterator.next();
            aCard.printCard();
        }
    }

}
