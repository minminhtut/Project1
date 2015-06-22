package project1;

/**
 * This class contains variables for a ClientData object.
 *
 * @author Legionaires
 *
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ClientData extends Play implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CLIENT_STRING = "C";
    private String id;
    private String name;
    private String address;
    private String phone;
    private long balance;
    private List<Play> plays = new LinkedList<Play>();

    /**
     * a constructor without parameters
     */
    protected ClientData() {
    }

    /**
     * create a client from given id, name, address and phone number
     *
     * @param newName
     * @param newAddress
     * @param newPhone
     */
    protected ClientData(String newName, String newAddress, String newPhone) {
        this.name = new String(newName);
        this.address = new String(newAddress);
        this.phone = new String(newPhone);
        this.balance = 0;
        this.id = new String(CLIENT_STRING + (MemberIdServer.instance()).getId());
    }

    /**
     * a getter method to get id
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
        this.id = CLIENT_STRING + (MemberIdServer.instance()).getId();
    }

    /**
     * a getter method to get a name
     *
     * @return name
     */
    protected String getName() {
        return name;
    }

    /**
     * a setter method to set a name
     *
     * @param newName
     */
    protected void setName(String newName) {
        this.name = new String(newName);
    }

    /**
     * a getter method to get an address
     *
     * @return
     */
    protected String getAddress() {
        return address;
    }

    /**
     * a setter method to set an address
     *
     * @param address
     */
    protected void setAddress(String newAddress) {
        this.address = new String(newAddress);
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
     * a setter method to set a phone number
     *
     * @param phone
     */
    protected void setPhone(String newPhone) {
        this.phone = new String(newPhone);
    }

    /**
     * a getter method to get a balance
     *
     * @return balance
     */
    protected long getBalance() {
        return balance;
    }

    /**
     * a setter method to set a balance
     *
     * @param balance
     */
    protected void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * a getter method to get the list of play
     *
     * @return plays
     */
    protected List<Play> getPlays() {
        return plays;
    }

    /**
     * a setter method to set the list of play
     *
     * @param plays
     */
    protected void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    /**
     * Checks whether a play with a given name exists.
     *
     * @param number
     * @return the card if exist
     */
    protected Play search(String name) {
        for (Iterator<Play> iterator = this.plays.iterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            if (aPlay.getName().equals(name)) {
                return aPlay;
            }
        }
        return null;
    }

    /**
     * a method to add a play to the list
     *
     * @param play, Play object
     * @return true if the play is added
     */
    protected boolean addPlay(Play play) {
        if (this.search(play.getName()) == null) {
            plays.add(play);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a method to remove a play from the list
     *
     * @param name
     * @return true if the play was removed
     */
    protected boolean removePlay(String name) {
        for (ListIterator<Play> iterator = this.plays.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            if (aPlay.getName().equals(name)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * print a client
     */
    protected void printClient() {
        System.out.println("Id: " + this.getId() + " Name: " + this.getName()
                + " Address: " + this.getAddress() + " Phone: " + this.getPhone());
        for (ListIterator<Play> iterator = this.plays.listIterator(); iterator
                .hasNext();) {
            Play aPlay = iterator.next();
            aPlay.printPlay();
        }

    }

    /**
     * print shows
     */
    protected void printPlay() {
        for (ListIterator<Play> iterator = this.plays.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            aPlay.printPlay();
        }
    }
}
