package com.example.doenerwo;


import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doenerstaende")
public class DoenerBude {

    @Id
    private String name;
    private String latitude;
    private String longitude;

    private String address;


    public DoenerBude(String name, String longitude, String latitude, String address) {

        super();
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String latitude) {
        this.address = latitude;
    }
}
