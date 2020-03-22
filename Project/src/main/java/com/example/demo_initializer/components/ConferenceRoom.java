package com.example.demo_initializer.components;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * tip specific de care implementeaza clasa Room
 */
@Entity
@DiscriminatorValue("Conference")
public class ConferenceRoom extends Room {

    private boolean projector;
    private boolean leatherSeats;

    public ConferenceRoom( int pricePerNight, int floor, int roomNb, int capacity,boolean free, int hours,Hotel hotel,boolean projector, boolean leatherSeats) {
        super( pricePerNight, floor, roomNb, capacity,free,hotel);
        this.projector=projector;
        this.leatherSeats=leatherSeats;
    }

    public ConferenceRoom() {
    }


    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public void setLeatherSeats(boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
    }

    public boolean isProjector() {
        return projector;
    }

    public boolean isLeatherSeats() {
        return leatherSeats;
    }
}
