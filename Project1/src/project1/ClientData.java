package project1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class contains variables for a client object.
 *
 * @author Legionaires
 *
 */
public class ClientData extends Play implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CLIENT_STRING = "C";
    private String id;
    private String name;
    private String address;
    private String phone;
    private long balance;
    private List<Play> playList = new LinkedList<Play>();

    /**
     * a constructor without parameters
     */
    protected ClientData() {
    }

    /**
     * create a client from given id, name, address and phone number
     *
     * @param id
     * @param name
     * @param address
     * @param phone
     */
    protected ClientData(String name, String address, String phone) {
        this.name = new String(name);
        this.address = new String(address);
        this.phone = new String(phone);
        this.balance = 0;
        playList = new LinkedList<Play>();
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
     * @param name
     */
    protected void setName(String name) {
        this.name = name;
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
     * a setter method to set a phone number
     *
     * @param phone
     */
    protected void setPhone(String phone) {
        this.phone = phone;
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
     * @return playList
     */
    protected List<Play> getPlays() {
        return playList;
    }

    /**
     * a setter method to set the list of play
     *
     * @param playlist
     */
    protected void setPlays(List<Play> playlist) {
        this.playList = playlist;
    }

    /**
     * Checks whether a play with a given name exists.
     *
     * @param number
     * @return the card if exist
     */
    protected Play searchPlaytName(String name) {
        for (Iterator<Play> iterator = this.playList.iterator(); iterator.hasNext();) {
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
     * @param name
     * @param start
     * @param end
     * @return
     */
    protected boolean addPlay(Play play) {
        if (this.searchPlaytName(play.getName()) == null) {
            playList.add(play);
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
        for (ListIterator<Play> iterator = this.playList.listIterator(); iterator.hasNext();) {
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
        System.out.println("Id: " + this.getId() + " Name: " + this.getName() + " Address: " + this.getAddress() + " Phone: " + this.getPhone());
        for (ListIterator<Play> iterator = this.playList.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            aPlay.printPlay();
        }

    }
}
