package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.AdminRepository;
import com.example.demo_initializer.Repositories.BookingRepository;
//import com.example.demo_initializer.Repositories.RoomBaseRepository;
import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.Admin;
import com.example.demo_initializer.components.Room;
import com.example.demo_initializer.components.User;
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
    private UserRepository userRepository;
    private AdminRepository adminRepository;

    @Autowired
    public DatabaseRun(BookingRepository bookingRepository, RoomRepository roomRepository,UserRepository userRepository,AdminRepository adminRepository)
    {
        this.bookingRepository=bookingRepository;
        this.roomRepository=roomRepository;
        this.userRepository=userRepository;
        this.adminRepository=adminRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        List<Room> rooms=new ArrayList<>();
        List<User> users=new ArrayList<>();
        List<Admin> admins =new ArrayList<>();



        bookingRepository.saveAll(bookings);
        roomRepository.saveAll(rooms);
        adminRepository.saveAll(admins);
        userRepository.saveAll(users);
    }
}
