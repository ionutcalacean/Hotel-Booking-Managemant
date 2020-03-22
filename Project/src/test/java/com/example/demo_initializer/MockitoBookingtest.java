package com.example.demo_initializer;



import com.example.demo_initializer.Controllers.AdminController;

import com.example.demo_initializer.Controllers.BookingController;
import com.example.demo_initializer.Repositories.AdminRepository;

import com.example.demo_initializer.Repositories.BookingRepository;
import com.example.demo_initializer.components.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;



import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;


public class MockitoBookingtest {

    @InjectMocks
    BookingController bookingController;

    @Mock
    BookingRepository bookingRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void  testGetAllBookings()
    {
        List<Booking> myList=new ArrayList<Booking>();
        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","ileanda");
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Room newRoom = new Room(250,1,6,2,true,newHotel);
        Booking newBooking= new Booking(newUser,newRoom,250,4);
        Booking newBooking1=new Booking(newUser,newRoom,255,6);
        myList.add(newBooking);
        myList.add(newBooking1);
        when(bookingRepository.findAll()).thenReturn(myList);

        List<Booking> returnedList = bookingController.getAll();
        assertEquals(myList,returnedList);

    }







}
