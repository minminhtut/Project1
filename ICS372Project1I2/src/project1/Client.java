package project1;

/**
 * This file contains Client Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * the class Client
 *
 */
public class Client extends ClientData implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Client client;
    private List<ClientData> clientList = new LinkedList<ClientData>();

    public Client() {
    }

    /**
     * Support the singleton pattern
     *
     * @return the singleton object
     */
    public static Client instance() {
        if (client == null) {
            return (client = new Client());
        }
        else {
            return client;
        }
    }

    /**
     * Checks whether a client with given id exist or not.
     *
     * @param id
     * @return the customer if exist
     */
    public ClientData searchClientID(String id) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().matches(id)) {
                return aClient;
            }
        }
        return null;
    }
    
   
    /**
     * A method to add a client
     *
     * @param newClient
     * @return false if customer dosen't exist
     */
    public boolean addClient(ClientData newClient) {
        if (searchClientID(newClient.getId()) == null) {
            clientList.add(newClient);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A method to remove a client
     *
     * @param id, ClientData Object which contains the client to be removed.
     * @return true if the client was removed
     */
    public boolean removeClient(ClientData removeClient) {
        if (removeClient != null) {
        	if(removeClient.checkAllPlay() == true) {
        		clientList.remove(removeClient);
        		return true;
        	}
        	else {
        		return false;
        	}
        }
        else {
            return false;
        }
    }

    /**
     * A method to add a play to the client
     *
     * @param client, ClientData Object which contains the client.
     * @param play, Play Object which contains a play to be added to the client.
     * @return true if the play is added
     */
    public boolean addPlay(ClientData client, Play play) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if ((aClient.getId().compareTo(client.getId())) == 0) {
                Play result = aClient.searchPlaytName(play.getName());
                if (result == null) {
                    aClient.addPlay(play);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * a method to search a play of clients
     * 
     * @param date
     * @return
     */
    public ClientData searchClientPlay(Calendar date) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if(aClient.searchPlayDate(date) != null) {
                return aClient;
            }
        }
        return null;
    }
    
    /**
     * a method to search a play of clients by date
     * 
     * @param date
     * @return aPlay
     */
    public Play searchPlay(Calendar date) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            Play aPlay = aClient.searchPlayDate(date);
            return aPlay;
        }
        return null;
    }

    /**
     * a method to add ticket to a play
     * 
     * @param ticket
     * @param aClient
     * @param aTicket
     */
    public boolean addClientTicket(ClientData aClient, Play aPlay, Ticket aTicket) {
    	int index = 0;
    	boolean flag = false;
    	for(int i = 0; i < clientList.size(); i++) {
    		if(clientList.get(i).getId().matches(aClient.getId())) {
    			index = i;
    		}
    	}
    	
    	flag = clientList.get(index).addClientTicket(aPlay, aTicket);
    	
    	return flag;
    }
    
    /**
     * A method to get numbers of sold tickets
     * 
     * @param play
     * @param aTicket
     */
    public int numberOfTickets(Play aPlay) {
    	int sold = 0;
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            List<Play> plays = aClient.getPlays();
            for(Play p : plays) {
            	System.out.println(p.getName());
            	System.out.println(aPlay.getName());
            }
        }
        return sold;
    }
    
    /**
     * A method to add a ticket to the play
     * 
     * @param play
     * @param aTicket
     */
    public void addTicket(Play play, Ticket aTicket) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            List<Play> plays = aClient.getPlays();
            for(Play p : plays) {
            	if(p.getName().matches(play.getName())) {
            		p.setTickets(aTicket);
            	}
            }
        }

    }

    /**
     * A method to list all clients
     */
    public void listAllClients() {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            aClient.printClient();
        }

    }

    /**
     * a method to list all shows
     */
    public void ListAllPlays() {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData cliendata = iterator.next();
            cliendata.ListAllPlays();
        }

    }
    
    /**
     * a method to list all shows for the specific day
     */
    public void ListPlaysForDay(Calendar date) {
        for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData cliendata = iterator.next();
            cliendata.ListPlayForDay(date);
        }

    }
    
    /**
     * a method to update client's balance
     * @param theClient
     * @param pay
     */
    public void updateBalance(ClientData theClient, double pay) {
    	for (Iterator<ClientData> iterator = clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().matches(theClient.getId())) {
            	aClient.updateBalance(pay);
            }
    	}
    }
    
    /**
     * a method to check is client list is empty
     *  @return true if no clients
     */
    public boolean noClients() {
        return clientList.isEmpty();
    }
}
