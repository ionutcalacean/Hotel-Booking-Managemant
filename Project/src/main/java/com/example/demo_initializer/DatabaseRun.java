package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.BookingRepository;
//import com.example.demo_initializer.Repositories.RoomBaseRepository;
import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.components.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRun implements CommandLineRunner {
    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;

    @Autowired
    public DatabaseRun(BookingRepository bookingRepository, RoomRepository roomRepository)
    {
        this.bookingRepository=bookingRepository;
        this.roomRepository=roomRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        List<Room> rooms=new ArrayList<>();


        bookingRepository.saveAll(bookings);
        roomRepository.saveAll(rooms);
    }
}
