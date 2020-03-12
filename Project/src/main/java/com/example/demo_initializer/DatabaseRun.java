package com.example.demo_initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRun implements CommandLineRunner {
    private BookingRepository bookingRepository;

    @Autowired
    public DatabaseRun(BookingRepository bookingRepository)
    {
        this.bookingRepository=bookingRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Booking> bookings = new ArrayList<>();

        bookings.add(new Booking("Perla",299,3));
        bookings.add(new Booking("Delfin",150,2));
        bookings.add(new Booking("International",200,5));

        bookingRepository.saveAll(bookings);
    }
}
