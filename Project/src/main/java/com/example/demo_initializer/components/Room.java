package com.example.demo_initializer.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name= "Room")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long roomId;
    private int pricePerNight;
    private int floor;
    @Column(unique = true)
    private int roomNb;
    private int capacity;
    private boolean free;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotelId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Hotel hotel;


    public Room(long roomId, int pricePerNight, int floor, int roomNb, int capacity,boolean free,Hotel hotel) {
        this.roomId = roomId;
        this.pricePerNight = pricePerNight;
        this.floor = floor;
        this.roomNb = roomNb;
        this.capacity = capacity;
        this.free=free;
        this.hotel=hotel;
    }

    public Room() {
    }

    public long getRoomId() {
        return roomId;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoomNb() {
        return roomNb;
    }

    public void setRoomNb(int roomNb) {
        this.roomNb = roomNb;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
