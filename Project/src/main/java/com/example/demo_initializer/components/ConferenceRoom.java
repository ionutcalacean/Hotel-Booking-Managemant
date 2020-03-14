package com.example.demo_initializer.components;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Conference")
public class ConferenceRoom extends Room {

    private int hours;

    public ConferenceRoom(long roomId, int pricePerNight, int floor, int roomNb, int capacity, int hours) {
        super(roomId, pricePerNight, floor, roomNb, capacity);
        this.hours = hours;
    }

    public ConferenceRoom() {
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
