package com.example.demo_initializer.components;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * tip specific de care implementeaza clasa Room
 */
@Entity
@DiscriminatorValue("Premium")
public class PremiumRoom extends Room {

    private boolean balcony;
    private boolean roomService;
    private boolean spa;

    public PremiumRoom(int pricePerNight, int floor, int roomNb, int capacity,boolean free, boolean balcony, boolean roomService, boolean spa,Hotel hotel) {
        super( pricePerNight, floor, roomNb, capacity,free,hotel);
        this.balcony = balcony;
        this.roomService = roomService;
        this.spa = spa;
    }

    public PremiumRoom() {
    }

    public void callRoomService()
    {
        roomService=true;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }
}
