package com.example.demo_initializer.components;


import com.example.demo_initializer.components.Room;
import com.example.demo_initializer.components.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "username")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Room room;
    private double price;
    private int nbOfNights;

    public Booking(User user, Room room, double price, int nbOfNights) {
        this.user = user;
        this.room = room;
        this.price = price;
        this.nbOfNights = nbOfNights;
    }

    public Booking(double price, int nbOfNights) {
        this.price = price;
        this.nbOfNights = nbOfNights;
    }

    public Booking()
    {}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNbOfNights() {
        return nbOfNights;
    }

    public void setNbOfNights(int nbOfNights) {
        this.nbOfNights = nbOfNights;
    }

    public double getTotalPrice(){
        return nbOfNights * price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id)
    {this.id=id;}


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
