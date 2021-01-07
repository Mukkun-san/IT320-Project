
import java.util.ArrayList;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mukkun
 */
public class VenuesList {

    private ArrayList<Venue> venues;

    VenuesList() {
        venues = new ArrayList();
    }

    public boolean add(Venue v) {
        return venues.add(v);
    }

    public boolean remove(Venue v) {
        return venues.remove(v);
    }

    public Venue get(String uuid) {
        for (Venue v : venues) {
            if (uuid.equals(v.getUuid())) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Venue> getList() {
        return venues;
    }

}
