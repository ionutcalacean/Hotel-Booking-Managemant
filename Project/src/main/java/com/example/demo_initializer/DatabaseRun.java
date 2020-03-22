package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.*;
//import com.example.demo_initializer.Repositories.RoomBaseRepository;
import com.example.demo_initializer.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRun implements CommandLineRunner {
    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private HotelRepository hotelRepository;
    @Autowired
    public DatabaseRun(BookingRepository bookingRepository, RoomRepository roomRepository,UserRepository userRepository,AdminRepository adminRepository,
            HotelRepository hotelRepository)
    {
        this.bookingRepository=bookingRepository;
        this.roomRepository=roomRepository;
        this.userRepository=userRepository;
        this.adminRepository=adminRepository;
        this.hotelRepository=hotelRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        List<Room> rooms=new ArrayList<>();
        List<User> users=new ArrayList<>();
        List<Admin> admins =new ArrayList<>();
        List<Hotel>  hotels = new ArrayList<>();


        bookingRepository.saveAll(bookings);
        roomRepository.saveAll(rooms);
        adminRepository.saveAll(admins);
        userRepository.saveAll(users);
        hotelRepository.saveAll(hotels);
    }
}
