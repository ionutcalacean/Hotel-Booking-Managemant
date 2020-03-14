package com.example.demo_initializer.components;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Regular")
public class RegularRoom extends Room {

    private boolean matrimonialBad;

    public RegularRoom(long roomId, int pricePerNight, int floor, int roomNb, int capacity, boolean matrimonialBad) {
        super(roomId, pricePerNight, floor, roomNb, capacity);
        this.matrimonialBad = matrimonialBad;
    }

    public RegularRoom() {

    }

    public boolean isMatrimonialBad() {
        return matrimonialBad;
    }

    public void setMatrimonialBad(boolean matrimonialBad) {
        this.matrimonialBad = matrimonialBad;
    }
}
