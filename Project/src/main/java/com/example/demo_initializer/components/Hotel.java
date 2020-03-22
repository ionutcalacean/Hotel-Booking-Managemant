package com.example.demo_initializer.components;

import javax.persistence.*;

/**
 * Clasa pentru date in persistenta logica cu tabela Hotel
 */
@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long hotelId;
    private String hotelName;
    private String street;
    private String city;
    private String phone;

    public Hotel(String hotelName, String street, String city, String phone) {
        this.hotelName = hotelName;
        this.street = street;
        this.city = city;
        this.phone = phone;
    }


    public Hotel() {
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
