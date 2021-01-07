
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mukkun
 */
public class ClientsList {

    private ArrayList<Client> Clients;

    ClientsList() {
        Clients = new ArrayList();
    }

    public Client getClient(String un) {
        for (Client client : Clients) {
            if (client.getUsername().equals(un)) {
                return client;
            }
        }
        return null;
    }

    public boolean addClient(Client c) {
        return Clients.add(c);
    }

    public boolean rmClient(Client c) {
        return Clients.remove(c);
    }

    public boolean contains(String un) {
        return Clients.contains(new Client(un));
    }

    @Override
    public String toString() {
        return Clients.toString();
    }

    public ArrayList<Client> getList() {
        return Clients;
    }

}
