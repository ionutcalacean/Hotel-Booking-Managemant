package com.example.demo_initializer;

public class Booking {
    private String nameOfHotel;
    private double price;
    private int nbOfNights;

    public Booking(String nameOfHotel, double price, int nbOfNights) {
        this.nameOfHotel = nameOfHotel;
        this.price = price;
        this.nbOfNights = nbOfNights;
    }

    public Booking()
    {}

    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public void setNameOfHotel(String nameOfHotel) {
        this.nameOfHotel = nameOfHotel;
    }

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

}
