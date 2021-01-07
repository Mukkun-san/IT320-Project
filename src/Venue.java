
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
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
public class Venue {

    private String uuid;
    private String siteName;
    private String city;
    private String address;
    private String type;
    private int capacity;
    private String imgPath;
    private Timestamp addedOn;

    public Venue(String uuid, String siteName, String city,
            String address, String type, int capacity,
            String imgPath, Timestamp addedOn) {
        edit(uuid, siteName, city, address, type, capacity, imgPath, addedOn);
    }

    public void edit(String uuid, String siteName, String city,
            String address, String type, int capacity,
            String imgPath, Timestamp addedOn) {
        this.uuid = uuid;
        this.siteName = siteName;
        this.city = city;
        this.address = address;
        this.type = type;
        this.capacity = capacity;
        this.imgPath = imgPath;
        this.addedOn = addedOn;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public String getImgPath() {
        return imgPath;
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
        final Venue other = (Venue) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venue{" + "uuid=" + uuid + ", siteName=" + siteName + ", city=" + city + ", address=" + address + ", type=" + type + ", capacity=" + capacity + ", imgPath=" + imgPath + ", addedOn=" + addedOn + '}';
    }

}
