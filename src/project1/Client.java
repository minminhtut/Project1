package project1;

/**
 * This class contains variables for a Client object.
 * 
 * @author Legionaires
 *
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Client client;
	private List<ClientData> clients = new LinkedList<ClientData>();

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
		}
		else {
			return client;
		}
	}

	/**
	 * Checks whether a client with given id exist or not.
	 * 
	 * @param id, unique id
	 * @return aClient if the customer if exist
	 */
	protected ClientData search(String id) {
		for (Iterator<ClientData> iterator = this.clients.iterator(); iterator.hasNext();) {
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
	 * @param newClient, Client Object
	 * @return false if customer dosen't exist
	 */
	protected boolean addClient(ClientData newClient) {
		if (this.search(newClient.getId()) == null) {
			clients.add(newClient);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * a method to remove a client
	 * 
	 * @param removeClient, ClientData object
	 * @return true if the client was removed
	 */
	protected boolean removeClient(ClientData removeClient) {
		if (removeClient != null) {
			clients.remove(removeClient);
			return true;
		}
		else
			return false;
	}

	/**
	 * a method to add a play to the client
	 * 
	 * @param client, ClientData object
	 * @param play, Play object
	 * @return true if the play is added
	 */
	protected boolean addPlay(ClientData client, Play play) {
		for (Iterator<ClientData> iterator = this.clients.iterator(); iterator.hasNext();) {
			ClientData aClient = iterator.next();
			if (aClient.getId().equals(client.getId())) {
				Play result = aClient.search(play.getName());
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
		for (Iterator<ClientData> iterator = this.clients.iterator(); iterator.hasNext();) {
			ClientData aClient = iterator.next();
			aClient.printClient();
		}
	}

	/**
	 * a method to list all shows
	 */
	protected void ListAllPlays() {
		for (Iterator<ClientData> iterator = this.clients.iterator(); iterator.hasNext();) {
			ClientData aClient = iterator.next();
			aClient.printPlay();
		}
	}
}
