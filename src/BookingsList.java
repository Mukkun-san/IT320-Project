
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
public class BookingsList {

    private ArrayList<Booking> bookings;

    BookingsList() {
        bookings = new ArrayList();
    }

    public void add(Booking b) {
        bookings.add(b);
    }

    public void remove(Booking b) {
        bookings.remove(b);
    }

    public ArrayList<Booking> getList() {
        return bookings;
    }

    public Booking get(String id) {
        for (Booking b : bookings) {
            if (id.equals(b.getUuid())) {
                return b;
            }
        }
        return null;
    }

}
