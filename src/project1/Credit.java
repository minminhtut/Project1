package project1;

/**
 * This file contains Credit Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * the class Credit which holds data for a credit card
 * 
 */
public class Credit implements Serializable {

    private static final long serialVersionUID = 1L;
    private long number;
    private Calendar expiration;

    /**
     * a constructor without parameters
     */
    protected Credit() {
    }

    /**
     * create a credit card with a given number and an expiration date
     *
     * @param newNumber
     * @param newExpiration
     */
    protected Credit(long newNumber, Calendar newExpiration) {
        this.number = newNumber;
        this.expiration = newExpiration;
    }

    /**
     * a getter method for the number of a credit card
     *
     * @return number
     */
    protected long getNumber() {
        return number;
    }

    /**
     * a setter method for the number of a credit card
     *
     * @param newNumber
     */
    protected void setNumber(int newNumber) {
        this.number = newNumber;
    }

    /**
     * a getter method for the expiration date of a credit card
     *
     * @return a String object of an expiration date
     */
    protected String getExpiration() {
        SimpleDateFormat date = new SimpleDateFormat("MM-yyyy");
        return date.format(expiration.getTime());
    }

    /**
     * a setter method for the expiration date of a credit card
     *
     * @param newExpiration
     */
    protected void setExpiration(Calendar newExpiration) {
        this.expiration = newExpiration;
    }

    /**
     * a method to print a credit card
     */
    protected void printCard() {
        System.out.println("Credit Card Number: " + this.getNumber() + " " + "Credit Card Expiration: " + this.getExpiration());
    }

}
