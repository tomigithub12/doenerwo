package com.example.doenerwo.domain;

import jakarta.persistence.Id;

public class DoenerstandAnzeigeDTO {


    private String name;

    private String address;

    public DoenerstandAnzeigeDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "DoenerstandAnzeigeDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
