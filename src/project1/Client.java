package project1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Client client;
    private List<ClientData> clientList = new LinkedList<ClientData>();

    private Client() {
    }

    /**
     * support the singleton pattern
     *
     * @return the singleton object
     */
    protected static Client instance() {
        if (client == null) {
            return (client = new Client());
        } else {
            return client;
        }
    }

    /**
     * Checks whether a client with given id exist or not.
     *
     * @param id
     * @return the customer if exist
     */
    protected ClientData searchClientID(String id) {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().equals(id)) {
                return aClient;
            }
        }
        return null;
    }

    /**
     * a method to add a client
     *
     * @param newClient
     * @return false if customer dosen't exist
     */
    protected boolean addClient(ClientData newClient) {
        if (this.searchClientID(newClient.getId()) == null) {
            clientList.add(newClient);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a method to remove a client
     *
     * @param id
     * @return true if the client was removed
     */
    protected boolean removeClient(ClientData removeClient) {
        if (removeClient != null) {
            clientList.remove(removeClient);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a method to add a play to the client
     *
     * @param id
     * @param name
     * @param start
     * @param end
     * @return true if the play is added
     */
    //protected boolean addPlay(String id, String name, Date start, Date end) {
    protected boolean addPlay(ClientData client, Play play) {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().equals(client.getId())) {
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
     * a method to list all clients
     */
    protected void listAllClients() {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            aClient.printClient();
        }

    }

    /**
     * a method to list all shows
     */
    protected void ListAllPlays() {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            aClient.printPlay();
        }

    }
    
    /**
     * a method to check is client list is empty
     */
    public boolean noClients() {
        return this.clientList.isEmpty();
    }

}
