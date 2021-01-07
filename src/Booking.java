
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mukkun
 */
public class Booking {

    private String uuid;
    private String clientId;
    private Venue venue;
    private String eventName;
    private int noGuests;
    private Date startDate;
    private Date endDate;
    private Timestamp addedOn;

    public Booking(String uuid, String clientId, Venue venue, String eventName, int noGuests,
            Date startDate, Date endDate, Timestamp addedOn) {
        edit(uuid, clientId, venue, eventName, noGuests, startDate, endDate, addedOn);
    }

    public void edit(String uuid, String clientId, Venue venue, String eventName, int noGuests,
            Date startDate, Date endDate, Timestamp addedOn) {
        this.uuid = uuid;
        this.clientId = clientId;
        this.venue = venue;
        this.eventName = eventName;
        this.noGuests = noGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.addedOn = addedOn;
    }

    public String getUuid() {
        return uuid;
    }

    public String getClientId() {
        return clientId;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getEventName() {
        return eventName;
    }

    public int getNoGuests() {
        return noGuests;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "uuid=" + uuid + ", clientId=" + clientId + ", eventName=" + eventName + ", noGuests=" + noGuests + ", startDate=" + startDate + ", endDate=" + endDate + ", addedOn=" + addedOn + '}';
    }

}
