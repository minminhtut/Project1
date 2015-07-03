package project1;

/**
 * This file contains ClintData Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * the class Client which holds data for a single Client
 * 
 */
public class Client implements Serializable, Matchable<String>{

    private static final long serialVersionUID = 1L;
    private static final String CLIENT_STRING = "C";
    private String id;
    private String name;
    private String address;
    private String phone;
    private long balance;
    private List<Play> playList = new LinkedList<Play>();
    
    public Client() {
    }

    /**
     * create a client from given id, name, address and phone number
     *
     * @param name
     * @param address
     * @param phone
     */
    public Client(String newName, String newAddress, String newPhone) {
        this.name = newName;
        this.address = newAddress;
        this.phone = newPhone;
        this.balance = 0;
        //playList = new LinkedList<Play>();
        this.id = CLIENT_STRING + (MemberIdServer.instance()).getId();
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
    protected void setId() {
        id = CLIENT_STRING + (MemberIdServer.instance()).getId();
    }

    /**
     * a getter method to get a name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * a setter method to set a name
     *
     * @param name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * a getter method to get an address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * a setter method to set an address
     *
     * @param address
     */
    public void setAddress(String newAddress) {
        address = newAddress;
    }

    /**
     * a getter method to get a phone number
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * a setter method to set a phone number
     *
     * @param phone
     */
    public void setPhone(String newPhone) {
        phone = new String(newPhone);
    }

    /**
     * a getter method to get a balance
     *
     * @return balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * a setter method to set a balance
     *
     * @param balance
     */
    public void setBalance(long newBalance) {
        balance = newBalance;
    }

    /**
     * a getter method to get the list of play
     *
     * @return playList
     */
    public List<Play> getPlays() {
        return playList;
    }

    /**
     * a setter method to set the list of play
     *
     * @param playlist
     */
    public void setPlays(List<Play> newPlaylist) {
        playList = newPlaylist;
    }

    /**
     * Checks whether a play with a given name exists.
     *
     * @param name
     * @return the card if exist
     */
    public Play searchPlaytName(String name) {
        for (Iterator<Play> iterator = playList.iterator(); iterator.hasNext();) {
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
     * @param play, Play Object of a play
     * @return true if the play exist.
     */
    public boolean addPlay(Play play) {
        if (searchPlaytName(play.getName()) == null) {
            playList.add(play);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * a method to remove a play from the list
     *
     * @param name
     * @return true if the play was removed
     */
    public boolean removePlay(String name) {
        for (ListIterator<Play> iterator = playList.listIterator(); iterator.hasNext();) {
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
    public void printClient() {
        System.out.println("Id: " + getId() + " Name: " + getName() + " Address: " + getAddress() + " Phone: " + getPhone());
        for (ListIterator<Play> iterator = playList.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            aPlay.printPlay();
        }

    }
    /**
     * print a client
     */
    public void ListAllPlays() {
        for (ListIterator<Play> iterator = playList.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            aPlay.printPlay();
        }
    }
    
    /**
     * check client's show date
     * @return false if the show is in the future
     */
    public boolean checkAllPlay() {
    	boolean flag = true;
    	for (ListIterator<Play> iterator = playList.listIterator(); iterator.hasNext();) {
            Play aPlay = iterator.next();
            if(aPlay.checkTime() ==  false)
            	flag = false;
        }
    	return flag;
    }
    @Override
    public boolean matches(String key) {
        return id.equals(key);
    }
}
